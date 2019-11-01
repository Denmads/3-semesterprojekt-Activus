package Domain.authentication;


import Domain.chat.ChatService;
import Domain.trainingScheme.TrainingSchemeService;
import Models.Profile;
import Domain.profile.ProfileService;
import Domain.serviceInterfaces.IAuthenticationService;
import Models.Request;
import Enums.RequestArgumentName;
import Enums.RequestType;
import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import Enums.ServiceType;
import Exceptions.ServiceNotFoundException;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import Models.CredentialsContainer;
import Models.Response;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author madsh
 */
public class AuthenticationService extends IAuthenticationService {

    private CredentialsContainer credentials;

    public AuthenticationService(ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
    }

    @Override
    public CredentialsContainer getCredentials() {
        return credentials;
    }

    @Override
    public boolean login(String username, String password) {
        try {
            //Creating a request object, which is an object that holds the different information
            //we want to ask the server for.
            Request loginRQ = createRequest(RequestType.LOGIN);
            //Adding the username to the request.
            loginRQ.addArgument(RequestArgumentName.USERNAME, username);
            //Adding the password to the request.
            loginRQ.addArgument(RequestArgumentName.PASSWORD, password);
            //Sending the request to the server.
            Response response = communicationLayer.sendRequest(loginRQ);
            
            //We verify the users login in a try-catch statement.
            //If the server receives an argument that doesn't exist we will get an exception.
            //Therefore if the logininformation is incorrect the method will return false.
            try {
                //Casting the information received from the server to the CredentialsContainer. The Container is used to hold the received information.
                credentials = (CredentialsContainer) response.getArgument(ResponseArgumentName.CREDENTIALS);
                //Casting the received information into a Profile object for the user.
                Profile p = (Profile) response.getArgument(ResponseArgumentName.PROFILE);
                //Creating a profileservice with the current users information.
                ProfileService PS = new ProfileService(p, communicationLayer, domainFacade);
                //Adding ProfileService to the domainFacade.
                domainFacade.addService(ServiceType.PROFILE, PS);
                
                createServices();
                
                return true;
                
            } catch (ArgumentNotFoundException e) {
                System.out.println(e);
                System.out.println("----------Failed Login----------");
            }
            return false;
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null,ex);
        }
        
        return false;
    }
    
    private void createServices () {
        TrainingSchemeService trainingSchemeService = new TrainingSchemeService(communicationLayer, domainFacade);
        domainFacade.addService(ServiceType.TRAININGSCHEME, trainingSchemeService);

        ChatService chatService = new ChatService(communicationLayer, domainFacade);
        domainFacade.addService(ServiceType.CHAT, chatService);
    }

    @Override
    public void logout() {
        try {
            Request request = createRequest(RequestType.LOGOUT);
            request.addArgument(RequestArgumentName.USER_ID, credentials.getUserId());
            communicationLayer.sendRequest(request);
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean createAccount(NewAccountInfo accountInfo) {
        try {
            Request request = createRequest(RequestType.CREATE_NEW_USER);
            request.addArgument(RequestArgumentName.FIRST_NAME, accountInfo.getFirstName());
            request.addArgument(RequestArgumentName.LAST_NAME, accountInfo.getLastName());
            request.addArgument(RequestArgumentName.USERNAME, accountInfo.getUsername());
            request.addArgument(RequestArgumentName.PASSWORD, accountInfo.getPassword());
            
            Response response = communicationLayer.sendRequest(request);
            
            try {
                return (boolean)response.getArgument(ResponseArgumentName.SUCCESS);
            } catch (ArgumentNotFoundException ex) {
                Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return false;
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public Request createServerRequest(ServiceType serviceType, RequestType type) {
        if (credentials != null) {
            return new Request(type, credentials, serviceType);        
        }
        else {
            return new Request(type, null, ServiceType.AUTHENTICATION);
        }
        
    }
    
    
    
}
