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
import static persistence.database.generated.Tables.SET;
import static persistence.database.generated.Tables.TRAINING_PROGRAM_EXERCISE;

/**
 *
 * @author Denmads
 */
public class UpdateSetIndexAction extends IDatabaseAction<Boolean>{

    private boolean executed = false;
    
    private int setId;
    private int newIndex;

    public UpdateSetIndexAction(int setId, int newIndex) {
        this.setId = setId;
        this.newIndex = newIndex;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        database.update(SET).set(SET.SET_INDEX, newIndex).where(SET.ID.eq(setId)).execute();
        
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
