package persistence.actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.PROFILE;

/**
 *
 * @author Yourk
 */
//This class is supposed to go in the database and delete on profile ID and check if deleted and return true if deleted.
public class DeleteAccountAction extends IDatabaseAction<Boolean> {

    private boolean accountDeleted = false;
    private boolean executed = false;
            
    private int profileId;

    public DeleteAccountAction(int profileId) {
        this.profileId = profileId;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        database.deleteFrom(PROFILE).where(PROFILE.ID.eq(profileId)).execute();
        
        Result<Record> res = database.select().from(PROFILE).where(PROFILE.ID.eq(profileId)).fetch();
        accountDeleted = res.isEmpty();
        
        executed = true;
    }

    @Override
    public Boolean getResult() {
        return (executed ? accountDeleted : false);
    }

    @Override
    public boolean hasResult() {
        return executed;
    }
    
}
