package Persistence.Actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.Record;
import org.jooq.TableField;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.PROFILE;

/**
 * This class is meant to update the chosen profile information in the database.
 * THIS MIGHT NOT BE DONE!!!
 *
 * @author Victor
 */
public class UpdateProfileAction extends IDatabaseAction<Boolean> {

    private boolean updated = false;
    private boolean executed = false;

    private int profileID;
    private TableField TF;
    private Object changeObj;

    public UpdateProfileAction(int profileID, TableField TF, Object changeObj) {
        this.profileID = profileID;
        this.TF = TF;
        this.changeObj = changeObj;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {
        //Updating chosen profile information:
        database.update(PROFILE).set(TF, changeObj).where(PROFILE.ID.eq(profileID));

        //Verify that the data has been updated:
        Result<Record> res = database.select().from(PROFILE).where(PROFILE.ID.eq(profileID)).fetch();
        Record record = res.get(0);

        updated = record.getValue(TF).equals(changeObj);

        executed = true;

    }

    @Override
    public Boolean getResult() {
        return (executed ? updated : false);
    }

    @Override
    public boolean hasResult() {
        return executed;
    }

}