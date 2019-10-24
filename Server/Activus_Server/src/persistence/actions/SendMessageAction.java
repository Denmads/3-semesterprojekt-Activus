/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence.actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.MESSAGE;

/**
 * @author $Terpen
 */
public class SendMessageAction extends IDatabaseAction<Boolean>{
    
    private boolean result = false;
    private boolean executed = false;
    private int senderId;
    private int reciverId;
    private String message;
    
    public SendMessageAction(int senderId, int reciverId, String message){
        this.senderId = senderId;
        this.reciverId = reciverId;
        this.message = message;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {
        database.insertInto(MESSAGE).columns(MESSAGE.SENDER, MESSAGE.RECEIVER, MESSAGE.MESSAGE_).values(senderId, reciverId, message);
        
        executed = true;
    }

    @Override
    public Boolean getResult() {
        return (executed ? result : false);
    }

    @Override
    public boolean hasResult() {
        return executed;
    }

}
