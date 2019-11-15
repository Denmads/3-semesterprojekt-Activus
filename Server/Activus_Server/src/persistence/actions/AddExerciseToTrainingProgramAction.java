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

    private boolean executed;
    
    private int exerciseID;
    private int trainingProgramID;
    private int index;
    
    public AddExerciseToTrainingProgramAction(int exerciseID, int trainingProgramID, int index) {
        this.exerciseID = exerciseID;
        this.trainingProgramID = trainingProgramID;
        this.index = index;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        database.insertInto(TRAINING_PROGRAM_EXERCISE).columns(TRAINING_PROGRAM_EXERCISE.EXERCISEID, TRAINING_PROGRAM_EXERCISE.TRAINING_PROGRAMID, TRAINING_PROGRAM_EXERCISE.INDEX).values(exerciseID, trainingProgramID, index).execute();
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
