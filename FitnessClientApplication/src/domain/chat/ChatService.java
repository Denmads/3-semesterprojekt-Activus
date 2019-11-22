/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.chat;

import Domain.profile.ProfileService;
import Domain.serviceInterfaces.IAuthenticationService;
import Domain.serviceInterfaces.IChatService;
import Enums.RequestArgumentName;
import Enums.RequestType;
import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import Exceptions.ServiceNotFoundException;
import models.Request;
import models.Response;
import domain.serviceInterfaces.IChatService;
import domain.serviceInterfaces.IProfileService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick
 */
public class ChatService extends IChatService {
    
    
    public ChatService(ICommunicationFacade communication, IDomainFacade domainFacade){
        super(communication, domainFacade);
    }

    @Override
    public void sendMessage(int buddyProfileId, Message message) {
        
        try {
            Request request = createRequest(RequestType.SEND_MESSAGE);
            request.addArgument(RequestArgumentName.PROFILE_ID, buddyProfileId);
            request.addArgument(RequestArgumentName.MESSAGE, message);
            communicationLayer.sendRequest(request);
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Message[] getChatHistory(int buddyProfileId) {
       return (Message[]) returnResponseObject(RequestType.RECEIVE_MESSAGE_HISTORY, RequestArgumentName.PROFILE_ID, ResponseArgumentName.CHAT_HISTORY, buddyProfileId);
    }
    
    private Object returnResponseObject(RequestType requestType, RequestArgumentName requestArguementName,ResponseArgumentName responseArguementName, Object o){
        Object object=null;
        try {
            Request request = createRequest(requestType);
            request.addArgument(requestArguementName, o);
            Response response = communicationLayer.sendRequest(request);
            object= response.getArgument(responseArguementName);
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassCastException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArgumentNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return object;
    }
}
