package Models;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * @author $Terpen
 */
public class Message implements Serializable {
    
    private int senderId;
    private int reciverId;
    private String message;
    private Time time;
    private Date date;
    private boolean isRead;

    public Message(int senderId) {
        this.senderId = senderId;
    }

    public Message(int senderId, int reciverId) {
        this.senderId = senderId;
        this.reciverId = reciverId;
    }

    public Message(int senderId, int reciverId, String message) {
        this.senderId = senderId;
        this.reciverId = reciverId;
        this.message = message;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReciverId() {
        return reciverId;
    }

    public void setReciverId(int reciverId) {
        this.reciverId = reciverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
        public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
    
}
