/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import persistence.IDatabaseAction;

/**
 *
 * @author Patrick
 */
public class RemoveExerciseAction extends IDatabaseAction<Boolean> {

    private boolean result;
    private boolean executed;
    
    public RemoveExerciseAction(){
        
    }
    
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
