/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.SET;
import static persistence.database.generated.Tables.SET_TRAINING_PROGRAM_EXERCISE;

/**
 *
 * @author Denmads
 */
public class RemoveSetAction extends IDatabaseAction<Boolean>{

    private boolean executed = false;
    
    private int setId;

    public RemoveSetAction(int setId) {
        this.setId = setId;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        database.deleteFrom(SET_TRAINING_PROGRAM_EXERCISE).where(SET_TRAINING_PROGRAM_EXERCISE.SETID.eq(setId)).execute();
        database.deleteFrom(SET).where(SET.ID.eq(setId)).execute();
        
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
