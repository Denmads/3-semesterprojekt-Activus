/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.EXERCISE;

/**
 * Action that removes an exercise from the DB
 * @author Patrick
 */
public class RemoveExerciseByIDAction extends IDatabaseAction<Boolean> {

    private boolean result;
    private boolean executed;
    private int id;
    
    public RemoveExerciseByIDAction(int id){
        this.id = id;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        database.deleteFrom(EXERCISE).where(EXERCISE.ID.eq(id)).execute();
        executed = true;
        
        Result<Record> res = database.select().from(EXERCISE).where(EXERCISE.ID.eq(id)).fetch();
        if(res.isEmpty()){
            result = true;
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
