/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Enums.ResponseArgumentName;
import Models.Request;
import Models.Response;

/**
 *
 * @author madsh
 */
public class AuthenticationRequestHandler implements IRequestHandler{
    
    @Override
    public Response handleRequest(Request request) {
        Response response = new Response();
        response.addArgument(ResponseArgumentName.SUCCESS, "It worked!");
        
        return response;
    }
}
