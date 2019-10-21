/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.serviceInterfaces;

import Domain.Chat.Message;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import LayerInterfaces.IService;

/**
 *
 * @author Patrick
 */
public abstract class IChatService extends IService {
    
    public IChatService(ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
    }
    
    public abstract void sendMessage(int buddyProfileId, Message message);
    
    public abstract Message[] getChatHistory(int buddyProfileId);
    
}
