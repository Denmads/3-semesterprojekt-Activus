/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import models.Exercise;
import java.sql.SQLException;
import org.jooq.DSLContext;
import persistence.IDatabaseAction;

/**
 *
 * @author Patrick
 */
public class GetExerciseByIDAction extends IDatabaseAction<Exercise>{

    private Exercise result;
    private boolean executed;
    
    private int id;

    public GetExerciseByIDAction(int id) {
        this.id = id;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
//        Result<Record6<String, String, Long, Integer, Integer, Integer>> res = database.select(EXERCISE.NAME,EXERCISE.DESCRIPTION,EXERCISE.TIME_TAKEN,EXERCISE.REBS,EXERCISE.SETS,EXERCISE.WEIGHT).from(EXERCISE.where(EXERCISE.ID.eq(id))).fetch();
//        result = new Exercise();
//        
//        //TODO change database for getting sets for etch ecerxise
//        Record rec = res.get(0);
//        result.setDescription(rec.get(EXERCISE.DESCRIPTION));
//        result.setName(rec.get(EXERCISE.NAME));
//        SetInfo set = new SetInfo(id, id);
//        
//        set.setReps(rec.get(EXERCISE.REBS));
//        set.setWeight(rec.get(EXERCISE.WEIGHT));
//        result.setTime(rec.get(EXERCISE.TIME_TAKEN));
//        //TODO add set info?
        
        executed = true;
    }

    @Override
    public Exercise getResult() {
        return executed ? result : null;
    }

    @Override
    public boolean hasResult() {
        return executed;
    }
    
}
