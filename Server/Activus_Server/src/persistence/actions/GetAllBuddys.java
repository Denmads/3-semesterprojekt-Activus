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
import persistence.IDatabaseAction;

/**
 * @author $Terpen
 */
public class GetAllBuddys extends IDatabaseAction<List<Profile>>{

    private int profileId;
    
    private List result = null;

    public GetAllBuddys(int profileId){
        this.profileId = profileId;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {
        result = new ArrayList<Profile>();
        
        
    }

    @Override
    public List getResult() {
        return result;
    }

    @Override
    public boolean hasResult() {
        return (result != null);
    }
    
}
