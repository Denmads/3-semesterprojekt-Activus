package persistence.actions;

import models.Message;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.MESSAGE;

/**
 * @author $Terpen
 */
public class ReciveMessageHistoryAction extends IDatabaseAction<ArrayList<Message>>{

    private int reciverId;
    private int senderId;
    
    private ArrayList<Message> resultList = null;
        

    public ReciveMessageHistoryAction(int reciverId, int senderId){
        this.reciverId = reciverId;
        this.senderId = senderId;
    }
    
    
    
    
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        
        Result<Record> result = database.select(MESSAGE.asterisk()).from(MESSAGE).where(MESSAGE.RECEIVER.in(reciverId).and(MESSAGE.SENDER.in(senderId))).fetch();
        
        resultList = new ArrayList<>();
        
        for (Record r : result) {
            Message message = new Message(r.get(MESSAGE.SENDER));
            message.setReciverId(r.get(MESSAGE.RECEIVER));
            message.setMessage(r.get(MESSAGE.MESSAGE_));
            message.setDate(r.get(MESSAGE.DATE));
            //TODO Update JOOQ
            //message.setIsRead(r.get(MESSAGE.SEEN));
            
            resultList.add(message);
        }
        
    }

    @Override
    public ArrayList<Message> getResult() {
        return resultList;
    }

    @Override
    public boolean hasResult() {
        return resultList != null;
    }

}
