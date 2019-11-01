/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.TRAINING_PROGRAM_EXERCISE;

/**
 * Action that connects an exercise to a training program in the DB
 * @author Patrick
 */
public class AddExerciseToTrainingProgramAction extends IDatabaseAction<Boolean> {

    private boolean result;
    private boolean executed;
    
    private int exerciseID;
    private int trainingProgramID;

    public AddExerciseToTrainingProgramAction(int exerciseID, int trainingProgramID) {
        this.exerciseID = exerciseID;
        this.trainingProgramID = trainingProgramID;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        database.insertInto(TRAINING_PROGRAM_EXERCISE).columns(TRAINING_PROGRAM_EXERCISE.EXERCISEID, TRAINING_PROGRAM_EXERCISE.TRAINING_PROGRAMID).values(exerciseID, trainingProgramID).execute();
        executed = true;
        
        Result<Record1<Integer>> res = database.select(TRAINING_PROGRAM_EXERCISE.TRAINING_PROGRAMID).from(TRAINING_PROGRAM_EXERCISE).where(TRAINING_PROGRAM_EXERCISE.EXERCISEID.eq(exerciseID)).fetch();
        for(Record rec : res){
            if(rec.equals(trainingProgramID)){
                result = true;
                break;
            }
        }
        
    }

    @Override
    public Boolean getResult() {
        return executed ? result : false;
    }

    @Override
    public boolean hasResult() {
        return executed;
    }
    
}
