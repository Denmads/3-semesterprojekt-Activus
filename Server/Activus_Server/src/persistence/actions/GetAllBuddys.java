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
        resultList = new ArrayList<Profile>();
        resultList.add(new Profile(0));
        for (Record r: result){
            if (profileId == r.getValue(BUDDYS.PROFILEID)){
                Profile p = new Profile (r.getValue(BUDDYS.PROFILEID2));
                resultList.add(p);
            } else {
                Profile p = new Profile (r.getValue(BUDDYS.PROFILEID));
                resultList.add(p);
            }
        }
        
        
        
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
