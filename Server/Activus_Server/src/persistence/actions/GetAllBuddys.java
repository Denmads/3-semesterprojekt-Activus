/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Profile;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.BUDDYS;
import static persistence.database.generated.Tables.LOGIN;
import static persistence.database.generated.Tables.PROFILE;


/**
 * @author $Terpen
 */
public class GetAllBuddys extends IDatabaseAction<List<Profile>>{

    private int profileId;
    
    private List resultList = null;

    public GetAllBuddys(int profileId){
        this.profileId = profileId;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {
        Result<Record> result = database.select().from(BUDDYS).where(BUDDYS.PROFILEID.equal(profileId)).or(BUDDYS.PROFILEID2.equal(profileId)).fetch();
        resultList = new ArrayList<>();
        
        result.forEach((rec) -> {
            if (profileId == rec.getValue(BUDDYS.PROFILEID)){
                int id = rec.getValue(BUDDYS.PROFILEID2);
                Profile profile = new Profile (id);
                Result<Record> bud = database.select(PROFILE.asterisk()).from(PROFILE).where(PROFILE.ID.eq(id)).fetch();
                
                for (Record r : bud) {
                    profile.setAge(r.getValue(PROFILE.AGE));
                    profile.setCity(r.getValue(PROFILE.CITY));
                    profile.setCountry(r.getValue(PROFILE.COUNTRY));
                    profile.setFirstName(r.getValue(PROFILE.FIRST_NAME));
                    profile.setLastName(r.getValue(PROFILE.LAST_NAME));
                    profile.setGender(r.getValue(PROFILE.GENDER));
                    profile.setGym(r.getValue(PROFILE.GYM));
                    profile.setActiveBuddy(r.getValue(PROFILE.ACTIVEBUDDY));

                    resultList.add(profile);
                }
                
                
            } else {
                int id = rec.getValue(BUDDYS.PROFILEID);
                Profile profile = new Profile (id);
                Result<Record> bud = database.select(PROFILE.asterisk()).from(PROFILE).where(PROFILE.ID.eq(id)).fetch();
                
                for (Record r : bud) {
                    profile.setAge(r.getValue(PROFILE.AGE));
                    profile.setCity(r.getValue(PROFILE.CITY));
                    profile.setCountry(r.getValue(PROFILE.COUNTRY));
                    profile.setFirstName(r.getValue(PROFILE.FIRST_NAME));
                    profile.setLastName(r.getValue(PROFILE.LAST_NAME));
                    profile.setGender(r.getValue(PROFILE.GENDER));
                    profile.setGym(r.getValue(PROFILE.GYM));
                    profile.setActiveBuddy(r.getValue(PROFILE.ACTIVEBUDDY));

                    resultList.add(profile);
                }
                
            }
        });
        
    }

    @Override
    public List getResult() {
        return resultList;
    }

    @Override
    public boolean hasResult() {
        return (resultList != null);
    }
    
}
