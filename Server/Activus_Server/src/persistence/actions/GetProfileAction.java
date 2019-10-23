package persistence.actions;

import Models.Profile;
import static persistence.database.generated.Tables.LOGIN;
import static persistence.database.generated.Tables.PROFILE;
import persistence.database.generated.tables.Login;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import persistence.IDatabaseAction;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

/**
 * An action for getting an arbitrary number of profiles
 *
 * @author madsh
 */
public class GetProfileAction extends IDatabaseAction<ArrayList<Profile>>{
    
    private ArrayList<Profile> resultList = null;
    
    private ArrayList<Integer> profileIds;
    
    public GetProfileAction (ArrayList<Integer> profileIds) {
        this.profileIds = profileIds;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException{
        Result<Record> result = database.select(PROFILE.asterisk(), LOGIN.USERNAME).from(PROFILE.join(LOGIN).on(PROFILE.LOGIN_ID.eq(LOGIN.ID))).where(PROFILE.ID.in(profileIds)).fetch();
        
        resultList = new ArrayList<>();
        
        for (Record r : result) {
            Profile profile = new Profile(r.getValue(PROFILE.ID));
            profile.setAge(r.getValue(PROFILE.AGE));
            profile.setCity(r.getValue(PROFILE.CITY));
            profile.setCountry(r.getValue(PROFILE.COUNTRY));
            profile.setFirstName(r.getValue(PROFILE.FIRST_NAME));
            profile.setLastName(r.getValue(PROFILE.LAST_NAME));
            profile.setGender(r.getValue(PROFILE.GENDER));
            profile.setGym(r.getValue(PROFILE.GYM));
            profile.setUsername(r.getValue(LOGIN.USERNAME));
            
            resultList.add(profile);
        }
    }
    
    @Override
    public ArrayList<Profile> getResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasResult() {
        return resultList != null;
    }
    
}
