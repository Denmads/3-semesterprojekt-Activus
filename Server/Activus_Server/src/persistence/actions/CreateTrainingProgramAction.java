/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import Models.Request;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jooq.DSLContext;
import persistence.IDatabaseAction;
import persistence.database.generated.tables.Exercise;
import persistence.database.generated.tables.TrainingProgram;

/**
 *
 * @author steff
 */
public class CreateTrainingProgramAction extends  IDatabaseAction<TrainingProgram>{
    
    private List<Exercise> TraninggsProgam;
    private Request reguest;
    
    

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

    @Override
    public void setResult(Request request) {
        this.reguest = request;
    }
    
}
