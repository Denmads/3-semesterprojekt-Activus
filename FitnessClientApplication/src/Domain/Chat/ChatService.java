/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Chat;

import Domain.User.ProfileService;
import Domain.serviceInterfaces.IAuthenticationService;
import Domain.serviceInterfaces.IChatService;
import Enums.RequestArgumentName;
import Enums.RequestType;
import Enums.ResponseArgumentName;
import Exceptions.ArgumentNotFoundException;
import Exceptions.ServiceNotFoundException;
import LayerInterfaces.Enums.ServiceType;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import Models.Request;
import Models.Response;
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
    public void sendMessage(int buddyProfileId, String message) {
        Request request = 
    }

    @Override
    public Message[] getChatHistory(int buddyProfileId) {
       return (Message[]) returnResponsObject(RequestType.RECEIVE_MESSAGE_HISTORY, RequestArgumentName.PROFILE_ID, ResponseArgumentName.CHAT_HISTORY, buddyProfileId);
    }
     private IAuthenticationService getAuthenticationService() throws ServiceNotFoundException{
        return  domainFacade.<IAuthenticationService>getService(ServiceType.AUTHENTICATION);
    }
    private Object returnResponsObject(RequestType requestType, RequestArgumentName requestArguementName,ResponseArgumentName responseArguementName, Object o){
        Object object=null;
        try {
            Request request = getAuthenticationService().createRequest(requestType);
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
