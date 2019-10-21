package Domain;

import Enums.RequestType;
import Enums.ServiceType;
import Models.Request;
import Models.Response;
import java.util.HashMap;

public class RequestDelegater {
    
    HashMap<ServiceType, IRequestHandler> requestHandlers;
    
    public RequestDelegater () {
        requestHandlers = new HashMap<>();
        requestHandlers.put(ServiceType.AUTHENTICATION, new AuthenticationRequestHandler());
        requestHandlers.put(ServiceType.PROFILE, new ProfileRequestHandler());
        requestHandlers.put(ServiceType.TRAININGSCHEME, new TrainingSchemeRequestHandler());
        requestHandlers.put(ServiceType.CHAT, new ChatRequestHandler()); 
    }

    private boolean contains (RequestType[] typeArray, Request request) {
        for (int i = 0; i < typeArray.length; i++) {
            if (typeArray[i] == request.getRequestType()) {
                return true;
            }
        }
        
        return false;
    }
    
    public Response delegate (Request request) {
        
        Response response = null;
        
        if (request.getServiceType() == ServiceType.AUTHENTICATION) {
            response = requestHandlers.get(request.getServiceType()).handleRequest(request);
        }
        else 
        {
            if (authenticateRequest(request)) {
                response = requestHandlers.get(request.getServiceType()).handleRequest(request);
            }
            else {
                //TODO: Create response object with Authentiation Error
            }
        }
        
        return response;
    }
    
    private boolean authenticateRequest (Request request) {
        return false;
    }
}
