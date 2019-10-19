/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Authentication;

import Domain.serviceInterfaces.IAuthenticationService;
import LayerInterfaces.ICommunication;

/**
 *
 * @author madsh
 */
public class AuthenticationService extends IAuthenticationService{

    private CredentialsContainer credentials;
    
    public AuthenticationService (ICommunication communication) {
        super(communication);
    }
    
    @Override
    public CredentialsContainer getCredentials() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Login(String username, String password) {
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
