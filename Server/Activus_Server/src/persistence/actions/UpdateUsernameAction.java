package persistence.actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.Record;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.LOGIN;

/**
 *
 * @author Victor
 */
public class UpdateUsernameAction extends IDatabaseAction<Boolean> {

    private boolean updated = false;
    private boolean executed = false;

    private String username;
    private int loginID;

    public UpdateUsernameAction(String username, int loginID) {
        this.username = username;
        this.loginID = loginID;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {
        //Updating the username in the LOGIN table:
        //.execute() because we don't want it to return anything.
        database.update(LOGIN).set(LOGIN.USERNAME, username).where(LOGIN.ID.eq(loginID)).execute();

        //Verify that the username is updated:
        Result<Record> res = database.select().from(LOGIN).where(LOGIN.ID.eq(loginID)).fetch();
        Record record = res.get(0);

        updated = record.getValue(LOGIN.USERNAME).equals(username);

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