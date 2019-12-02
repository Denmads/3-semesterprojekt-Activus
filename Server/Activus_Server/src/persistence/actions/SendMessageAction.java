/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence.actions;

import models.Message;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
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
    private Message message;
    
    public SendMessageAction(Message message){
        this.senderId = message.getSenderId();
        this.reciverId = message.getReciverId();
        this.message = message;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {
        int id = -1;
        id = database.insertInto(MESSAGE).columns(MESSAGE.SENDER, MESSAGE.RECEIVER, MESSAGE.MESSAGE_).values(senderId, reciverId, message.getMessage()).returning(MESSAGE.ID).execute();
        result = id != -1;
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
