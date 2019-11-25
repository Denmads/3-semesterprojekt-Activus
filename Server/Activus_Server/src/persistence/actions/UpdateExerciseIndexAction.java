/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import org.jooq.Condition;
import org.jooq.DSLContext;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.TRAINING_PROGRAM_EXERCISE;
import persistence.database.generated.tables.TrainingProgramExercise;

/**
 *
 * @author Denmads
 */
public class UpdateExerciseIndexAction extends IDatabaseAction<Boolean>{

    private boolean executed = false;
    
    private int exerciseId;
    private int programId;
    private int newIndex;

    public UpdateExerciseIndexAction(int exerciseId, int programId, int newIndex) {
        this.exerciseId = exerciseId;
        this.programId = programId;
        this.newIndex = newIndex;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        Condition whereCondition = TRAINING_PROGRAM_EXERCISE.EXERCISEID.eq(exerciseId).and(TRAINING_PROGRAM_EXERCISE.TRAINING_PROGRAMID.eq(programId));
        database.update(TRAINING_PROGRAM_EXERCISE).set(TRAINING_PROGRAM_EXERCISE.INDEX, newIndex).where(whereCondition).execute();
        
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
