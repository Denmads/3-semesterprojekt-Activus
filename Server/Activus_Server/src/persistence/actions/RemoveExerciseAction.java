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
import static persistence.database.generated.Tables.SET_TRAINING_PROGRAM_EXERCISE;
import static persistence.database.generated.Tables.TRAINING_PROGRAM_EXERCISE;

/**
 * Action that removes an exercise from the DB
 * @author Patrick
 */
public class RemoveExerciseAction extends IDatabaseAction<Boolean> {
    
    private boolean executed;
    
    private int exerciseId;
    private int programId;

    public RemoveExerciseAction(int exerciseId, int programId) {
        this.exerciseId = exerciseId;
        this.programId = programId;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        Condition setIsRelated = SET_TRAINING_PROGRAM_EXERCISE.TRAINING_PROGRAM_EXERCISEEXERCISEID.eq(exerciseId).and(SET_TRAINING_PROGRAM_EXERCISE.TRAINING_PROGRAM_EXERCISETRAINING_PROGRAMID.eq(programId));
        Result<Record1<Integer>> res = database.select(SET_TRAINING_PROGRAM_EXERCISE.SETID).from(SET_TRAINING_PROGRAM_EXERCISE).where(setIsRelated).fetch();
        
        
        for (Record1<Integer> r : res) {
            RemoveSetAction removeAction = new RemoveSetAction(r.component1());
            removeAction.execute(database);
        }
        
        Condition whereCondition = TRAINING_PROGRAM_EXERCISE.EXERCISEID.eq(exerciseId).and(TRAINING_PROGRAM_EXERCISE.TRAINING_PROGRAMID.eq(programId));
        database.deleteFrom(TRAINING_PROGRAM_EXERCISE).where(whereCondition).execute();
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
