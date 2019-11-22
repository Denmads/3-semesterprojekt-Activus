/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.chat;

import java.util.Date;

/**
 * Contains info aout one message
 * A model class
 *
 * @author Patrick
 */
public class Message {
    private final String message;
    private final String senderUsername;
    private final Date timeStamp;
    private int ID;

    public Message(String message, String senderUsername, Date timeStamp) {
        this.message = message;
        this.senderUsername = senderUsername;
        this.timeStamp = timeStamp;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    
    
    
    
    

    
    
    
    
 
    
    
}
