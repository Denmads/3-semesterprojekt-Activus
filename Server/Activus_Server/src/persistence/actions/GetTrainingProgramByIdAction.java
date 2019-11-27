/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import models.TrainingProgram;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.TRAINING_PROGRAM;
import static persistence.database.generated.Tables.TRAINING_PROGRAM_EXERCISE;

/**
 *
 * @author Denmads
 */
public class GetTrainingProgramByIdAction extends IDatabaseAction<TrainingProgram>{

    private TrainingProgram result = null;
    
    private int programId;

    public GetTrainingProgramByIdAction(int programId) {
        this.programId = programId;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        Record program = database.select().from(TRAINING_PROGRAM).where(TRAINING_PROGRAM.ID.eq(programId)).fetchOne();
        
        result = new TrainingProgram();
        result.setId(programId);
        result.setName(program.getValue(TRAINING_PROGRAM.NAME));
        result.setDescription(program.getValue(TRAINING_PROGRAM.DESCRIPTION));
        
        //Add exercises
        Result<Record1<Integer>> exercises = database.select(TRAINING_PROGRAM_EXERCISE.EXERCISEID).from(TRAINING_PROGRAM_EXERCISE).where(TRAINING_PROGRAM_EXERCISE.TRAINING_PROGRAMID.eq(programId)).orderBy(TRAINING_PROGRAM_EXERCISE.INDEX).fetch();
        
        for (Record1<Integer> r : exercises) {
            GetProgramExerciseByIdAction getAction = new GetProgramExerciseByIdAction(programId, r.component1());
            getAction.execute(database);
            
            if (getAction.hasResult()) {
                result.addExercise(getAction.getResult());
            }
        }
    }

    @Override
    public TrainingProgram getResult() {
        return result;
    }

    @Override
    public boolean hasResult() {
        return result != null;
    }
    
}
