/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Models.Request;
import Models.Response;
import Persistence.DatabaseFacade;

/**
 *
 * @author madsh
 */
public class ProfileRequestHandler extends IRequestHandler{

    public ProfileRequestHandler(DatabaseFacade dbFacade) {
        super(dbFacade);
    }

    @Override
    public Response handleRequest(Request request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
