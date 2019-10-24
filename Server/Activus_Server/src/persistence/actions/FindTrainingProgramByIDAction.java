/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.TRAINING_PROGRAM;
import persistence.database.generated.tables.TrainingProgram;

/**
 *
 * @author Patrick
 */
public class FindTrainingProgramByIDAction extends IDatabaseAction<TrainingProgram>{
    
    private TrainingProgram result;
    private boolean executed;
    private int id;
    
    
    public FindTrainingProgramByIDAction(int id){
        this.id = id;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        Result<Record> res = database.select().from(TRAINING_PROGRAM).where(TRAINING_PROGRAM.ID.eq(id));
        
    }

    @Override
    public TrainingProgram getResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasResult() {
        return executed;
    }
    
}
