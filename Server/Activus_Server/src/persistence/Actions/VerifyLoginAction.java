package persistence.Actions;

import Persistence.IDatabaseAction;
import java.sql.SQLException;
import org.jooq.DSLContext;

/**
 *
 * @author Victor
 */
public class VerifyLoginAction extends IDatabaseAction<Boolean> {

    
    
    
    
    
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
