/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import Models.Request;
import Models.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.DatabaseFacade;

/**
 *
 * @author madsh
 */
public class AuthenticationRequestHandler extends IRequestHandler{

    public AuthenticationRequestHandler(DatabaseFacade dbFacade) {
        super(dbFacade);
    }
    
    @Override
    public Response handleRequest(Request request) {
        Response response = new Response();
        response.addArgument(ResponseArgumentName.SUCCESS, "It worked!");
        try {
            System.out.println(response.getArgument(ResponseArgumentName.PROFILE).toString());
        } catch (ArgumentNotFoundException ex) {
            Logger.getLogger(AuthenticationRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
    }
}
