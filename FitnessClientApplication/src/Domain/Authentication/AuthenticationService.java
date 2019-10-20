package Domain.Authentication;


import Domain.User.Profile;
import Domain.User.ProfileService;
import Domain.serviceInterfaces.IAuthenticationService;
import Models.Request;
import Enums.RequestArguementName;
import Enums.RequestType;
import Enums.ResponseArguementName;
import Exceptions.ArguementNotFoundException;
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
    public boolean Login(String username, String password) {
        //Creating a request object, which is an object that holds the different information
        //we want to ask the server for.
        Request loginRQ = new Request(RequestType.LOGIN, null, -1);
        //Adding the username to the request.
        loginRQ.AddArgument(RequestArguementName.USERNAME, username);
        //Adding the password to the request.
        loginRQ.AddArgument(RequestArguementName.PASSWORD, password);
        //Sending the request to the server.
        Response response = communicationLayer.sendRequest(loginRQ);
        
        //We verify the users login in a try-catch statement. 
        //If the server receives an argument that doesn't exist we will get an exception.
        //Therefore if the logininformation is incorrect the method will return false. 
        try {
            //Casting the information received from the server to the CredentialsContainer. The Container is used to hold the received information.
            credentials = (CredentialsContainer) response.getArguement(ResponseArguementName.CREDENTIALS);
            //Casting the received information into a Profile object for the user.
            Profile p = (Profile) response.getArguement(ResponseArguementName.PROFILE);
            //Creating a profileservice with the current users information. 
            ProfileService PS = new ProfileService(p, communicationLayer, domainFacade);
            //Adding ProfileService to the domainFacade.
            domainFacade.addService(ServiceType.PROFILE, PS);

            return true;

        } catch (ArguementNotFoundException e) {
            System.out.println(e);
            System.out.println("----------Failed Login----------");
        }
        return false;
    }

    @Override
    public void Logout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean CreateAccount(NewAccountInfo accountInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
