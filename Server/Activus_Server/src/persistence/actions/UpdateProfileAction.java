package persistence.actions;

import java.sql.SQLException;
import java.util.List;
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

    private boolean result = false;
    private boolean executed = false;

    private int profileID;
    private List<Object> changes;
    private List<TableField> tableFields;

    public UpdateProfileAction(int profileID, List<Object> changes, List<TableField> tableFields) {
        this.profileID = profileID;
        this.changes = changes;
        this.tableFields = tableFields;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {
        //Updating chosen profile information:
        for (int i = 0; i < tableFields.size(); i++) {
            if (changes.get(i) instanceof String) {
                database.update(PROFILE).set(tableFields.get(i), (String) changes.get(i)).where(PROFILE.ID.eq(profileID));
            } else if (changes.get(i) instanceof Integer) {
                database.update(PROFILE).set(tableFields.get(i), (int) changes.get(i)).where(PROFILE.ID.eq(profileID));
            }
        }
        executed = true;
        //The data will always be changed if the method has been executed since there would otherwise be thrown exceptions from the library.
        result = true;
        System.out.println(result);
        
        

    }

    @Override
    public Boolean getResult() {
        return (executed ? result : false);
    }

    @Override
    public boolean hasResult() {
        return executed;
    }

}
