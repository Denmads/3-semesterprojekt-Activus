package persistence.actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.TRAINING_PROGRAM;

/**
 * Action to create a new training program in the DB
 * @author Patrick
 */
public class CreateTrainingProgramAction extends IDatabaseAction<Boolean> {

    private boolean result;
    private boolean executed;
    
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
        //database.insertInto(TRAINING_PROGRAM).columns(TRAINING_PROGRAM.OWNERID,TRAINING_PROGRAM.NAME,TRAINING_PROGRAM.DESCRIPTION).values(ownerID,name,description).execute();
        executed = true;
        
//        Record1<Integer> res = database.select(TRAINING_PROGRAM.ID).from(TRAINING_PROGRAM).where(TRAINING_PROGRAM.OWNERID.eq(ownerID)).and(TRAINING_PROGRAM.NAME.eq(name)).and(TRAINING_PROGRAM.DESCRIPTION.eq(description)).fetchAny();
//        if(res.get(TRAINING_PROGRAM.ID) > -1){
//            result = true;
//        }
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
