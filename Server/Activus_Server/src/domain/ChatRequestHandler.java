/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import Enums.RequestArgumentName;
import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import models.Request;
import models.Response;
import models.Message;
import java.util.logging.Level;
import java.util.logging.Logger;
import layerInterfaces.IDatabaseFacade;
import persistence.DatabaseFacade;
import persistence.actions.ReciveMessageHistoryAction;
import persistence.actions.SendMessageAction;

/**
 *
 * @author madsh
 */
public class ChatRequestHandler extends IRequestHandler {

    public ChatRequestHandler(IDatabaseFacade dbFacade) {
        super(dbFacade);
    }

    @Override
    public Response handleRequest(Request request) {

        Response response = new Response();

        switch (request.getRequestType()) {
            case SEND_MESSAGE:
                try {
                    SendMessageAction sma = new SendMessageAction(request.getArgument(RequestArgumentName.MESSAGE));
                    databaseFacade.execute(sma);
                    response.addArgument(ResponseArgumentName.SUCCESS, sma.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ChatRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break; 
            case RECEIVE_MESSAGE_HISTORY:
                try {
                    ReciveMessageHistoryAction rmha = new ReciveMessageHistoryAction(request.getArgument(RequestArgumentName.SENDER_ID), request.getArgument(RequestArgumentName.RECIVER_ID));
                    databaseFacade.execute(rmha);
                    response.addArgument(ResponseArgumentName.MESSAGE, rmha.getResult());
                } catch (ArgumentNotFoundException | ClassCastException ex) {
                    Logger.getLogger(ChatRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
