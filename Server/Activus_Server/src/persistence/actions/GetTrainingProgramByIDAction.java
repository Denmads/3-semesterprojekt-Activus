/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;


import Models.TrainingProgram;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record3;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.TRAINING_PROGRAM;


/**
 *
 * @author Patrick
 */
public class GetTrainingProgramByIDAction extends IDatabaseAction<TrainingProgram>{

    private TrainingProgram result;
    private boolean executed;
    
    private int id;

    public GetTrainingProgramByIDAction(int id) {
        this.id = id;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        //Result<Record3<Integer, String, String>> res = database.select(TRAINING_PROGRAM.OWNERID,TRAINING_PROGRAM.NAME,TRAINING_PROGRAM.DESCRIPTION).from(TRAINING_PROGRAM).where(TRAINING_PROGRAM.ID.eq(id)).fetch();
        
        //Record rec = res.get(0);
        
        //TODO Fix missing constructor args
        //result = new TrainingProgram(rec.getValue(TRAINING_PROGRAM.OWNERID),rec.getValue(TRAINING_PROGRAM.NAME),rec.getValue(TRAINING_PROGRAM.DESCRIPTION));
        
    }

    @Override
    public TrainingProgram getResult() {
        return executed ? result : null;
    }

    @Override
    public boolean hasResult() {
        return executed;
    }
    
}
