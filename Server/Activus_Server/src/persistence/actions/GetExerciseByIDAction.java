/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import Models.Exercise;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record6;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.EXERCISE;

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
        Result<Record6<String, String, Long, Integer, Integer, Integer>> res = database.select(EXERCISE.NAME,EXERCISE.DESCRIPTION,EXERCISE.TIME_TAKEN,EXERCISE.REBS,EXERCISE.SETS,EXERCISE.WEIGHT).from(EXERCISE.where(EXERCISE.ID.eq(id))).fetch();
        result = new Exercise();
        
        Record rec = res.get(0);
        result.setDescription(rec.get(EXERCISE.DESCRIPTION));
        result.setName(rec.get(EXERCISE.NAME));
        result.setReps(rec.get(EXERCISE.REBS));
        result.setSets(rec.get(EXERCISE.SETS));
        result.setTimeTaken(rec.get(EXERCISE.TIME_TAKEN));
        result.setWeight(rec.get(EXERCISE.WEIGHT));
        
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
