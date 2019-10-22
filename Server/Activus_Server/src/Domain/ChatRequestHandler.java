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
public class ChatRequestHandler extends IRequestHandler{

    public ChatRequestHandler(DatabaseFacade dbFacade) {
        super(dbFacade);
    }

    @Override
    public Response handleRequest(Request request) {
        switch(request.getRequestType()){
            case SEND_MESSAGE: /*do something*/ break; //Missing implementation
            case RECEIVE_MESSAGE_HISTORY: break;
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
