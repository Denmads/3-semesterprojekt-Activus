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
public class DennyBuddyRequest extends IDatabaseAction<Boolean>{
    
    private int buddyId;
    private int profileId;
    
    private boolean result = false;
    private boolean executed = false;

    public DennyBuddyRequest(int profileId, int buddyId){
        this.buddyId = buddyId;
        this.profileId = profileId;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {
        int done = -5;
        done = database.deleteFrom(BUDDYS).where(BUDDYS.PROFILEID.eq(profileId).and(BUDDYS.PROFILEID2.eq(buddyId))).or(BUDDYS.PROFILEID.eq(buddyId).and(BUDDYS.PROFILEID2.eq(profileId))).execute();
        result = done != -5;
        executed = true;
    }

    @Override
    public Boolean getResult() {
        return (executed ? result : false);
    }

    @Override
    public boolean hasResult() {
        return result;
    }

}
