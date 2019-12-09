/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence.actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.BUDDYS;

/**
 * @author $Terpen
 */
public class SendBuddyRequest extends IDatabaseAction<Boolean>{
    
    private boolean result = false;
    private boolean executed = false;
    private int senderId;
    private int reciverId;
    
    public SendBuddyRequest(int senderId, int reciverId){
        this.senderId = senderId;
        this.reciverId = reciverId;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {
        int id = -1;
        id = database.insertInto(BUDDYS).columns(BUDDYS.PROFILEID, BUDDYS.PROFILEID2, BUDDYS.ACTIVE_BUDDY).values(senderId, reciverId, false).returning(BUDDYS.PROFILEID).execute();
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