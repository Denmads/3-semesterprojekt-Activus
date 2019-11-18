package persistence.actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.LOGIN;

/**
 *
 * @author Yourk
 */
//This class is supposed to go in the database and delete on profile ID and check if deleted and return true if deleted.
public class DeleteAccountAction extends IDatabaseAction<Boolean> {

    private boolean accountDeleted = false;
    private boolean executed = false;

    private String profileName;
    private int profileID;

    public DeleteAccountAction(String profileName, int profileID) {
        this.profileName = profileName;
        this.profileID = profileID;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {

        database.update(LOGIN).set(LOGIN.FLAG, true).where(LOGIN.ID.eq(profileID)).execute();

        accountDeleted = database.select(LOGIN.FLAG).from(LOGIN).where(LOGIN.ID.eq(profileID)).fetchOne().getValue(LOGIN.FLAG);
        executed = true;
    }

    @Override
    public Boolean getResult() {
        return (executed ? accountDeleted : false);
    }

    @Override
    public boolean hasResult() {
        return executed;
    }

}
