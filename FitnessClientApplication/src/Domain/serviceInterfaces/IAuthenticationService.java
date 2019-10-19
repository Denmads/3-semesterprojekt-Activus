/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.serviceInterfaces;

import Domain.Authentication.CredentialsContainer;
import Domain.Authentication.NewAccountInfo;
import LayerInterfaces.ICommunication;
import LayerInterfaces.IService;

/**
 *
 * @author madsh
 */
public abstract class IAuthenticationService extends IService {

    public IAuthenticationService(ICommunication communication) {
        super(communication);
    }
    public abstract CredentialsContainer getCredentials();
    public abstract boolean Login (String username, String password);
    public abstract void Logout ();
    public abstract boolean CreateAccount(NewAccountInfo accountInfo);
}
