/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Chat;

import Models.Profile;
import java.util.Date;

/**
 *
 * @author Patrick
 */
public class Message {
    private final String message;
    private final String senderUsername;
    private final String receiverUsername;
    private final Date timeStamp;
    private int ID;

    public Message(String message, String senderUsername, String receiverUsername, Date timeStamp) {
        this.message = message;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.timeStamp = timeStamp;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    
    
    
    
    

    
    
    
    
 
    
    
}
