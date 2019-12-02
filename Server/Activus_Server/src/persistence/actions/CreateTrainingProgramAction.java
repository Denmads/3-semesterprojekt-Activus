package persistence.actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.TRAINING_PROGRAM;

/**
 * Action to create a new training program in the DB
 * @author Patrick
 */
public class CreateTrainingProgramAction extends IDatabaseAction<Integer> {

    private int result = -1;
    
    private int ownerID;
    private String name;
    private String description;

    public CreateTrainingProgramAction(int ownerID, String name, String description) {
        this.ownerID = ownerID;
        this.name = name;
        this.description = description;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        result = database.insertInto(TRAINING_PROGRAM).columns(TRAINING_PROGRAM.PROFILE_ID,TRAINING_PROGRAM.NAME,TRAINING_PROGRAM.DESCRIPTION).values(ownerID,name,description).returning(TRAINING_PROGRAM.ID).fetchOne().getValue(TRAINING_PROGRAM.ID);
    }

    @Override
    public Integer getResult() {
        return result;
    }

    @Override
    public boolean hasResult() {
        return result != -1;
    }
    
}
