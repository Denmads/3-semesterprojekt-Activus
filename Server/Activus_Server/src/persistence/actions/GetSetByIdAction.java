/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import models.SetInfo;
import org.jooq.DSLContext;
import org.jooq.Record;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.SET;

/**
 *
 * @author Denmads
 */
public class GetSetByIdAction extends IDatabaseAction<SetInfo>{

    private SetInfo result = null;
    
    private int setId;

    public GetSetByIdAction(int setId) {
        this.setId = setId;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        Record setInfo = database.select().from(SET).where(SET.ID.eq(setId)).fetchOne();
        
        result = new SetInfo();
        result.setId(setId);
        result.setSetIndex(setInfo.getValue(SET.SET_INDEX));
        result.setReps(setInfo.getValue(SET.REBS));
        result.setWeight(setInfo.getValue(SET.WEIGHT));
    }

    @Override
    public SetInfo getResult() {
        return result;
    }

    @Override
    public boolean hasResult() {
        return result != null;
    }
    
}
