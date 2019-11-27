/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.chat;

import domain.profile.ProfileService;
import Enums.RequestArgumentName;
import Enums.RequestType;
import Enums.ResponseArgumentName;
import Enums.ServiceType;
import Exceptions.ArgumentNotFoundException;
import Exceptions.ServiceNotFoundException;
import models.Message;
import models.Request;
import models.Response;
import domain.serviceInterfaces.IChatService;
import domain.serviceInterfaces.IProfileService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import layerInterfaces.ICommunicationFacade;
import layerInterfaces.IDomainFacade;

/**
 *
 * @author Patrick
 */
public class ChatService extends IChatService {
    
    
    public ChatService(ICommunicationFacade communication, IDomainFacade domainFacade){
        super(communication, domainFacade);
    }

    @Override
    public void sendMessage(Message message) {
        
        try {
            //All of this is in message if someone got the time make it so we do not sender more data then needed/ data twice.
            Request request = createRequest(RequestType.SEND_MESSAGE);
            request.addArgument(RequestArgumentName.SENDER_ID, domainFacade.<IProfileService>getService(ServiceType.PROFILE).getCurrentProfile().getProfileId());
            request.addArgument(RequestArgumentName.RECIVER_ID, message.getReciverId());
            request.addArgument(RequestArgumentName.MESSAGE, message);
            communicationLayer.sendRequest(request);
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(ChatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Message> getChatHistory(int profileId, int buddyProfileId) {
        return (List<Message>) returnResponseObject(RequestType.RECEIVE_MESSAGE_HISTORY, RequestArgumentName.PROFILE_ID, ResponseArgumentName.CHAT_HISTORY, buddyProfileId);
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
