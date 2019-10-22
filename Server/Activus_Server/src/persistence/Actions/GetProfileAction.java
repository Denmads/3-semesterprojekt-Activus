/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.Actions;

import Models.Profile;
import static Persistence.Database.generated.Tables.LOGIN;
import static Persistence.Database.generated.Tables.PROFILE;
import Persistence.Database.generated.tables.Login;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import Persistence.IDatabaseAction;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

/**
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
