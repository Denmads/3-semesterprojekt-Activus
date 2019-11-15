package domain.serviceInterfaces;

import domain.authentication.NewAccountInfo;
import Enums.RequestType;
import Enums.ServiceType;
import layerInterfaces.IService;
import layerInterfaces.ICommunicationFacade;
import layerInterfaces.IDomainFacade;
import models.CredentialsContainer;
import models.Request;

/**
 * The service provinding methods related to authentication and login
 * 
 * @author madsh
 */
public abstract class IAuthenticationService extends IService {

    public IAuthenticationService(ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
        type = ServiceType.AUTHENTICATION;
    }

    public abstract CredentialsContainer getCredentials();

    public abstract boolean login(String username, String password);

    public abstract void logout();

    public abstract String createAccount(NewAccountInfo accountInfo);
    
    public abstract boolean deleteAccount(String profileUsername, int profileID);

    public abstract Request createServerRequest(ServiceType serviceType, RequestType type);
}