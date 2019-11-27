/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.TRAINING_PROGRAM;
import static persistence.database.generated.Tables.TRAINING_PROGRAM_EXERCISE;

/**
 *
 * @author Denmads
 */
public class DeleteTrainingProgramAction extends IDatabaseAction<Boolean>{
    
    private boolean executed;
    
    private int programId;

    public DeleteTrainingProgramAction(int programId) {
        this.programId = programId;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {  
        Condition programIsRelated = TRAINING_PROGRAM_EXERCISE.TRAINING_PROGRAMID.eq(programId);
        Result<Record1<Integer>> res = database.select(TRAINING_PROGRAM_EXERCISE.EXERCISEID).from(TRAINING_PROGRAM_EXERCISE).where(programIsRelated).fetch();
        
        for (Record1<Integer> r : res) {
            RemoveExerciseAction removeAction = new RemoveExerciseAction(r.component1(),programId);
            removeAction.execute(database);
        }
        
        Condition whereCondition = TRAINING_PROGRAM.ID.eq(programId);
        database.deleteFrom(TRAINING_PROGRAM).where(whereCondition).execute();
        executed = true;
    }

    @Override
    public Boolean getResult() {
        return executed;
    }

    @Override
    public boolean hasResult() {
        return executed;
    }
}
