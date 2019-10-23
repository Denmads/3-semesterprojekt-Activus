package persistence.actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.TOKEN;

/**
 * Remove the authenticateToken from the database
 * 
 * @author $Terpen
 */
public class LogoutAction extends IDatabaseAction<Boolean>{
    
    private boolean result = false;
    private boolean executed = false;
    private int id;
    
    public LogoutAction(int id){
        this.id = id;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {
        
        database.delete(TOKEN).where(TOKEN.LOGINID.eq(id));
        
        Result<Record> res = database.select().from(TOKEN).where(TOKEN.LOGINID.eq(id));
        if (res.isEmpty()){
            result = true;
        }
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
