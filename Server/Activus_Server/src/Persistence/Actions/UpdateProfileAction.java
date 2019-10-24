package Persistence.Actions;

import java.sql.SQLException;
import java.util.ArrayList;
import org.jooq.DSLContext;
import persistence.IDatabaseAction;

/**
 * This class is meant to update the chosen profile information in the database.
 * 
 * @author Victor
 */
public class UpdateProfileAction extends IDatabaseAction<Boolean> {

    private ArrayList<> info;
    
    
    
    
    
    
    
    
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean getResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
