/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.TrainingProgram;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.TRAINING_PROGRAM;

/**
 *
 * @author Denmads
 */
public class GetAllProgramsForUser extends IDatabaseAction<List<TrainingProgram>>{

    private List<TrainingProgram> programs = new ArrayList<>();
    private boolean executed = false;
    
    private int profileId;

    public GetAllProgramsForUser(int profileId) {
        this.profileId = profileId;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        Result<Record1<Integer>> programIds = database.select(TRAINING_PROGRAM.ID).from(TRAINING_PROGRAM).where(TRAINING_PROGRAM.PROFILE_ID.eq(profileId)).fetch();
        
        for (Record1<Integer> r : programIds) {
            GetTrainingProgramByIdAction getAction = new GetTrainingProgramByIdAction(r.component1());
            getAction.execute(database);
            
            if (getAction.hasResult()) {
                programs.add(getAction.getResult());
            }
        }
        
        executed = true;
    }

    @Override
    public List<TrainingProgram> getResult() {
        return (executed ? programs : null);
    }

    @Override
    public boolean hasResult() {
        return executed;
    }
    
}
