/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import Models.Profile;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.PROFILE;

/**
 * Gets a profile based on the loginID
 *
 * @author madsh
 */
public class GetProfileByLoginIdAction extends IDatabaseAction<Profile> {

    private Profile profile = null;
    
    private int loginID;
    
    public GetProfileByLoginIdAction (int loginId) {
        this.loginID = loginId;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        int id = database.select(PROFILE.ID).from(PROFILE).where(PROFILE.LOGINID.eq(loginID)).fetchOne().getValue(PROFILE.ID);
        
        GetProfileAction getProfileAction = new GetProfileAction(new ArrayList<>(id));
        getProfileAction.execute(database);
        
        if (getProfileAction.hasResult()) {
            profile = getProfileAction.getResult().get(0);
        }
    }

    @Override
    public Profile getResult() {
        return profile;
    }

    @Override
    public boolean hasResult() {
        return profile != null;
    }
    
}
