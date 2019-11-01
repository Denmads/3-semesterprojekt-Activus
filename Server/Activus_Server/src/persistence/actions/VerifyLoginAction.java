package persistence.actions;

import Models.CredentialsContainer;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import persistence.IDatabaseAction;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import persistence.util.PasswordTool;
import static persistence.database.generated.Tables.LOGIN;
import static persistence.database.generated.Tables.TOKEN;

/**
 * An action for verifying the login information
 *
 * @author Victor
 */
public class VerifyLoginAction extends IDatabaseAction<CredentialsContainer> {
    private CredentialsContainer credentials = null;
    
    private String username;
    private String password;

    public VerifyLoginAction(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {
        //Fetching login information from database.
        Result<Record> res = database.select().from(LOGIN).where(LOGIN.USERNAME.eq(username)).fetch();

        //If the database returns something(Isn't empty) the password is verified.
        if (!res.isEmpty()) {
            Record record = res.get(0);
            
            boolean loginCorrect = checkPassword(record);
            
            if (loginCorrect) {
                int id = record.getValue(LOGIN.ID);
                credentials = new CredentialsContainer(username, id, createAuthenticationToken(database, record));
            }
        }
    }
    
    private boolean checkPassword (Record record) {
        byte[] passSalt = record.getValue(LOGIN.PASSWORD_SALT);
        byte[] hashedPassword = null;
        try {
            hashedPassword = PasswordTool.hashPassword(password, passSalt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(VerifyLoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Arrays.equals(hashedPassword, record.getValue(LOGIN.HASH_PASSWORD));
    }
    
    private UUID createAuthenticationToken (DSLContext database, Record record) {
        UUID uuid = UUID.randomUUID();
        database.insertInto(TOKEN).columns(TOKEN.LOGINID, TOKEN.TOKEN_).values(record.getValue(LOGIN.ID), uuid).execute();
        return uuid;
    }

    @Override
    public CredentialsContainer getResult() {
        return credentials;
    }

    @Override
    public boolean hasResult() {
        return credentials != null;
    }

}
