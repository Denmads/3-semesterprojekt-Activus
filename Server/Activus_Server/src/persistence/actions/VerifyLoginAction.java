package persistence.actions;


import models.CredentialsContainer;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import persistence.IDatabaseAction;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import persistence.Util.PasswordTool;
import static persistence.database.generated.Tables.LOGIN;

/**
 * An action for verifying the login information
 *
 * @author Victor
 */
public class VerifyLoginAction extends IDatabaseAction<Boolean> {
    private boolean loginCorrect = false;
    private boolean executed = false;
    
    private String username;
    private String password;

    public VerifyLoginAction(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        //Fetching login information from database.
        Result<Record> res = database.select().from(LOGIN).where(LOGIN.USERNAME.eq(username).and(LOGIN.FLAG.eq(false))).fetch();

        //If the database returns something(Isn't empty) the password is verified.
        if (!res.isEmpty()) {
            Record record = res.get(0);
            
            boolean loginCorrect = checkPassword(record);
            if (loginCorrect) {
                int id = record.getValue(LOGIN.ID);
                credentials = new CredentialsContainer(username, id, createAuthenticationToken(database, record));
            }
            
            loginCorrect = Arrays.equals(hashedPassword, record.getValue(LOGIN.HASH_PASSWORD));
        }
        
        executed = true;
    }

    @Override
    public Boolean getResult() {
        return (executed ? loginCorrect : false);
    }

    @Override
    public boolean hasResult() {
        return executed;
    }
    
}
