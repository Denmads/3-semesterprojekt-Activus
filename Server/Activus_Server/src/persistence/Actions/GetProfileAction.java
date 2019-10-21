/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.Actions;

import Enums.DatabaseTableName;
import Models.Profile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import persistence.IDatabaseAction;

/**
 *
 * @author madsh
 */
public class GetProfileAction extends IDatabaseAction<ArrayList<Profile>>{
    
    private ArrayList<Profile> result = null;
    
    private ArrayList<Integer> profileIds;
    
    public GetProfileAction (ArrayList<Integer> profileIds) {
        this.profileIds = profileIds;
    }
    
    @Override
    protected void execute(Connection connection) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT profile.*, login.username FROM " + getTableName(DatabaseTableName.PROFILE) + " WHERE id IN " + generateIdSet()
                + " INNER JOIN " + getTableName(DatabaseTableName.LOGIN) + " ON profile.login_id = login.id");
        
        for (int i = 0; i < profileIds.size(); i++) {
            preparedStatement.setInt(1+i, profileIds.get(i));
        }
        
        ResultSet res = preparedStatement.executeQuery();
        result = new ArrayList<>();
        
        while (res.next()) {
            Profile profile = new Profile(res.getInt("id"));
            profile.setAge(res.getInt("age"));
            profile.setCity(res.getString("city"));
            profile.setCountry(res.getString("country"));
            profile.setFirstName(res.getString("first_name"));
            profile.setLastName(res.getString("last_name"));
            profile.setGender(res.getString("gender"));
            profile.setGym(res.getString("gym"));
            profile.setUsername(res.getString("username"));
            
            result.add(profile);
        }
    }
    
    private String generateIdSet () {
        String questionString = String.join("", Collections.nCopies(profileIds.size(), "?,"));
        return "(" + questionString.substring(0, questionString.length()-1) + ")";
    }
    
    @Override
    public ArrayList<Profile> getResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasResult() {
        return result != null;
    }
    
}
