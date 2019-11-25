/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.TRAINING_PROGRAM;

/**
 *
 * @author Denmads
 */
public class UpdateProgramInfoAction extends IDatabaseAction<Boolean>{

    private boolean executed = false;
    
    private int programID;
    private String name;
    private String description;

    public UpdateProgramInfoAction(int programId, String name, String description) {
        this.programID = programId;
        this.name = name;
        this.description = description;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        database.update(TRAINING_PROGRAM).set(TRAINING_PROGRAM.NAME, name).set(TRAINING_PROGRAM.DESCRIPTION, description).where(TRAINING_PROGRAM.ID.eq(programID)).execute();
        
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
