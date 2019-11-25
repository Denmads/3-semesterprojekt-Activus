/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import models.Exercise;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.JoinType;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.EXERCISE;
import static persistence.database.generated.Tables.SET;
import static persistence.database.generated.Tables.SET_TRAINING_PROGRAM_EXERCISE;
import static persistence.database.generated.Tables.TRAINING_PROGRAM_EXERCISE;

/**
 *
 * @author Denmads
 */
public class GetProgramExerciseByIdAction extends IDatabaseAction<Exercise>{

    private Exercise result = null;
    
    private int programId;
    private int exerciseId;

    public GetProgramExerciseByIdAction(int programId, int exerciseId) {
        this.programId = programId;
        this.exerciseId = exerciseId;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        Record exerciseInfo = database.select().from(EXERCISE).where(EXERCISE.ID.eq(exerciseId)).fetchOne();
        
        result = new Exercise();
        result.setID(exerciseInfo.getValue(EXERCISE.ID));
        result.setName(exerciseInfo.getValue(EXERCISE.NAME));
        result.setDescription(exerciseInfo.getValue(EXERCISE.DESCRIPTION));
        result.setType(exerciseInfo.getValue(EXERCISE.TYPE));
        
        //Index
        Condition whereCondition = TRAINING_PROGRAM_EXERCISE.EXERCISEID.eq(exerciseId).and(TRAINING_PROGRAM_EXERCISE.TRAINING_PROGRAMID.eq(programId));
        int index = database.select(TRAINING_PROGRAM_EXERCISE.INDEX).from(TRAINING_PROGRAM_EXERCISE).where(whereCondition).fetchOne().component1();
        result.setIndexInProgram(index);
        
        //Sets
        Condition whereCondition2 = SET_TRAINING_PROGRAM_EXERCISE.TRAINING_PROGRAM_EXERCISEEXERCISEID.eq(exerciseId).and(SET_TRAINING_PROGRAM_EXERCISE.TRAINING_PROGRAM_EXERCISETRAINING_PROGRAMID.eq(programId));
        Result<Record1<Integer>> setIds = database.select(SET_TRAINING_PROGRAM_EXERCISE.SETID).from(SET_TRAINING_PROGRAM_EXERCISE.join(SET).on(SET_TRAINING_PROGRAM_EXERCISE.SETID.eq(SET.ID))).where(whereCondition2).orderBy(SET.SET_INDEX).fetch();
        
        for (Record1<Integer> r : setIds) {
            GetSetByIdAction getAction = new GetSetByIdAction(r.component1());
            getAction.execute(database);
            
            if (getAction.hasResult()) {
                result.addSetInfo(getAction.getResult());
            }
        }
    }

    @Override
    public Exercise getResult() {
        return result;
    }

    @Override
    public boolean hasResult() {
        return result != null;
    }
    
}
