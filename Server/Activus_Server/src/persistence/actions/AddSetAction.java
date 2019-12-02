/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import models.SetInfo;
import org.jooq.Condition;
import org.jooq.DSLContext;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.SET;
import static persistence.database.generated.Tables.SET_TRAINING_PROGRAM_EXERCISE;

/**
 *
 * @author Denmads
 */
public class AddSetAction extends IDatabaseAction<Integer>{

    private int newId = -1;
    
    private int exerciseId;
    private int programId;
    private SetInfo set;

    public AddSetAction(int exerciseId, int programId, SetInfo set) {
        this.exerciseId = exerciseId;
        this.programId = programId;
        this.set = set;
    }
    
    
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        newId = database.insertInto(SET).columns(SET.REBS, SET.WEIGHT, SET.SET_INDEX).values(set.getReps(), (int)set.getWeight(), set.getSetIndex()).returning(SET.ID).fetchOne().getValue(SET.ID);
        
        database.insertInto(SET_TRAINING_PROGRAM_EXERCISE).values(newId, exerciseId, programId).execute();
    }

    @Override
    public Integer getResult() {
        return newId;
    }

    @Override
    public boolean hasResult() {
        return newId != -1;
    }
    
}
