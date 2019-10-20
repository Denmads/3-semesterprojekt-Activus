/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Authentication;

import Domain.serviceInterfaces.IAuthenticationService;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import Models.CredentialsContainer;

/**
 *
 * @author madsh
 */
public class AuthenticationService extends IAuthenticationService{

    private CredentialsContainer credentials;
    
    public AuthenticationService (ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
    }
    
    @Override
    public CredentialsContainer getCredentials() {
        return credentials;
    }

    @Override
    public boolean Login(String username, String password) {
        //Credentials get set here
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Logout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean CreateAccount(NewAccountInfo accountInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
