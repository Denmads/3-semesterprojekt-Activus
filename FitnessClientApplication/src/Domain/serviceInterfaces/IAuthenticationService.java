package Domain.serviceInterfaces;

import Domain.Authentication.NewAccountInfo;
import Enums.RequestType;
import LayerInterfaces.IService;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import Models.CredentialsContainer;
import Models.Request;

/**
 *
 * @author madsh
 */
public abstract class IAuthenticationService extends IService {

    public IAuthenticationService(ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
    }

    public abstract CredentialsContainer getCredentials();

    public abstract boolean login(String username, String password);

    public abstract void logout();

    public abstract boolean createAccount(NewAccountInfo accountInfo);

    public abstract Request createServerRequest(RequestType type);
}