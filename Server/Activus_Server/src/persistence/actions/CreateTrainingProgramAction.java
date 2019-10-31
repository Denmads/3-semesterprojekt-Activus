/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import org.jooq.DSLContext;
import persistence.IDatabaseAction;
import persistence.database.generated.tables.TrainingProgram;

/**
 *
 * @author steff
 */
public class CreateTrainingProgramAction extends  IDatabaseAction<TrainingProgram>{
    
    private ArrayList<TrainingProgram> resultList = null;
    
    

    @Override
    protected void execute(DSLContext database) throws SQLException {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TrainingProgram getResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
