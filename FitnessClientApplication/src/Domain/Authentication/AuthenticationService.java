package Domain.Authentication;

import Domain.TrainingScheme.TrainingSchemeService;
import Domain.serviceInterfaces.IProfileService;
import Models.Response;
import Domain.User.Profile;
import Domain.User.ProfileService;
import Domain.serviceInterfaces.IAuthenticationService;
import Models.Request;
import Enums.RequestArgumentName;
import Enums.RequestType;
import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import LayerInterfaces.Enums.ServiceType;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import Models.CredentialsContainer;
import Models.Response;

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
        //Creating a request object, which is an object that holds the different information
        //we want to ask the server for.
        Request loginRQ = new Request(RequestType.LOGIN, null, -1);
        //Adding the username to the request.
        loginRQ.AddArgument(RequestArgumentName.USERNAME, username);
        //Adding the password to the request.
        loginRQ.AddArgument(RequestArgumentName.PASSWORD, password);
        //Sending the request to the server.
        Response response = communicationLayer.sendRequest(loginRQ);
        
        //We verify the users login in a try-catch statement. 
        //If the server receives an argument that doesn't exist we will get an exception.
        //Therefore if the logininformation is incorrect the method will return false. 
        try {
            //Casting the information received from the server to the CredentialsContainer. The Container is used to hold the received information.
            credentials = (CredentialsContainer) response.getArguement(ResponseArgumentName.CREDENTIALS);
            //Casting the received information into a Profile object for the user.
            Profile p = (Profile) response.getArguement(ResponseArgumentName.PROFILE);
            //Creating a profileservice with the current users information. 
            ProfileService PS = new ProfileService(p, communicationLayer, domainFacade);
            //Adding ProfileService to the domainFacade.
            domainFacade.addService(ServiceType.PROFILE, PS);
            
            TrainingSchemeService trainingSchemeService = new TrainingSchemeService(communicationLayer, domainFacade);
            domainFacade.addService(ServiceType.TRAININGSCHEME, trainingSchemeService);

            return true;

        } catch (ArgumentNotFoundException e) {
            System.out.println(e);
            System.out.println("----------Failed Login----------");
        }
        return false;
    }

    @Override
    public void logout() {
        Request request = createRequest(RequestType.LOGOUT);
        communicationLayer.sendRequest(request);
        credentials = null;
        
        domainFacade.removeAllServices();
    }

    @Override
    public boolean createAccount(NewAccountInfo accountInfo) {
        Request request = createRequest(RequestType.CREATE_NEW_USER);
        request.AddArgument(RequestArgumentName.FIRST_NAME, accountInfo.firstName);
        request.AddArgument(RequestArgumentName.LAST_NAME, accountInfo.lastName);
        request.AddArgument(RequestArgumentName.USERNAME, accountInfo.username);
        request.AddArgument(RequestArgumentName.PASSWORD, accountInfo.password);
        
        Response response = communicationLayer.sendRequest(request);
        
        try {
            response.getArguement(ResponseArgumentName.SUCCESS);
            
            return true;
        }
        catch (ArgumentNotFoundException ex) {
            return false;
        }
    }

    @Override
    public Request createRequest(RequestType type) {
        if (credentials != null) {
            return new Request(type, credentials.getAuthenticationToken(), credentials.getUserId());        
        }
        else {
            return new Request(type, null, -1);
        }
        
    }
    
    
    
}
