package persistence.actions;

import Models.CredentialsContainer;
import java.sql.SQLException;
import persistence.IDatabaseAction;
import org.jooq.DSLContext;
import static persistence.database.generated.Tables.TOKEN;

/**
 * An action for verifying the authenticationToken
 *
 * @author madsh
 */
public class AuthenticateTokenAction extends IDatabaseAction<Boolean> {

    private boolean result = false;
    private boolean executed = false;

    CredentialsContainer credentials;

    public AuthenticateTokenAction(CredentialsContainer credentials) {
        this.credentials = credentials;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {
        int count = database.selectCount().from(TOKEN).where(TOKEN.LOGINID.eq(credentials.getUserId()).and(TOKEN.TOKEN_.eq(credentials.getAuthenticationToken()))).fetchOne().value1();
        result = count > 0;
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