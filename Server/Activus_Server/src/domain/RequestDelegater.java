package domain;

import Enums.ServiceType;
import layerInterfaces.IRequestDelegater;
import Models.Request;
import Models.Response;
import persistence.DatabaseFacade;
import java.util.HashMap;
import layerInterfaces.IDatabaseFacade;
import persistence.actions.AuthenticateTokenAction;


/**
 * Delegates the different request to the handlers based on the service type
 * 
 * @author madsh
 */
public class RequestDelegater implements IRequestDelegater{

    private HashMap<ServiceType, IRequestHandler> requestHandlers;
    
    private IDatabaseFacade databaseFacade;

    public RequestDelegater() {
        databaseFacade = new DatabaseFacade();
        
        requestHandlers = new HashMap<>();
        requestHandlers.put(ServiceType.AUTHENTICATION, new AuthenticationRequestHandler(databaseFacade));
        requestHandlers.put(ServiceType.PROFILE, new ProfileRequestHandler(databaseFacade));
        requestHandlers.put(ServiceType.TRAININGSCHEME, new TrainingSchemeRequestHandler(databaseFacade));
        requestHandlers.put(ServiceType.CHAT, new ChatRequestHandler(databaseFacade));
    }

    public Response delegate(Request request) {

        Response response = new Response();

        if (request.getServiceType() == ServiceType.AUTHENTICATION) {
            response = requestHandlers.get(request.getServiceType()).handleRequest(request);
        } else {
            if (authenticateRequest(request)) {
                response = requestHandlers.get(request.getServiceType()).handleRequest(request);
            } else {
                //TODO
                System.out.println("authentication Failed");
            }
        }

        return response;
    }

    private boolean authenticateRequest(Request request) {
        AuthenticateTokenAction authenticationAction = new AuthenticateTokenAction(request.getCredentials());
        databaseFacade.execute(authenticationAction);
        
        return (authenticationAction.hasResult() ? authenticationAction.getResult() : false);
    }
    
    public IDatabaseFacade getDatabaseFacade () {
        return databaseFacade;
    }
}