/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Chat;

import Domain.serviceInterfaces.IChatService;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message[] getChatHistory(int buddyProfileId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
