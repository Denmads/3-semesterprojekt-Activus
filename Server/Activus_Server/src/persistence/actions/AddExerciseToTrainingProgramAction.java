/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.SetInfo;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.EXERCISE_SET;
import static persistence.database.generated.Tables.SET;
import static persistence.database.generated.Tables.TRAINING_PROGRAM_EXERCISE;

/**
 * Action that connects an exercise to a training program in the DB
 * @author Patrick
 */
public class AddExerciseToTrainingProgramAction extends IDatabaseAction<List<Integer>> {

    private boolean executed;
    private List<Integer> setIds = new ArrayList<>();
    
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
        
        Result<Record> resSet = database.select(SET.asterisk()).from(EXERCISE_SET.join(SET).on(EXERCISE_SET.SETID.eq(SET.ID))).where(EXERCISE_SET.EXERCISEID.eq(exerciseID)).fetch();
        
        for (Record r : resSet) {
            SetInfo info = new SetInfo();
            info.setReps(r.getValue(SET.REBS));
            info.setWeight(r.getValue(SET.WEIGHT));
            info.setSetIndex(r.getValue(SET.SET_INDEX));
            
            AddSetAction addAction = new AddSetAction(exerciseID, trainingProgramID, info);
            addAction.execute(database);
            
            setIds.add(addAction.getResult());
        }
        
        executed = true;
    }

    @Override
    public List<Integer> getResult() {
        return (executed ? setIds : null);
    }

    @Override
    public boolean hasResult() {
        return executed;
    }
    
}
