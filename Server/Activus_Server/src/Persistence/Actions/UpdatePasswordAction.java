package Persistence.Actions;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import persistence.IDatabaseAction;
import persistence.Util.PasswordTool;
import static persistence.database.generated.Tables.LOGIN;

/**
 *
 * @author Victor
 */
public class UpdatePasswordAction extends IDatabaseAction<Boolean> {
    
    private boolean updated = false;
    private boolean executed = false;
    
    private String username;
    private String password;
    
    public UpdatePasswordAction(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        
        byte[] newSalt = null;
        byte[] hashedPass = null;

        //Hashing the password argument(From constructor) so it gets hashed in the database.
        try {
            newSalt = PasswordTool.generateSalt();
            hashedPass = PasswordTool.hashPassword(password, newSalt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(VerifyLoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Updating the salt & the password.
        //.execute() because we don't want it to return anything.
        database.update(LOGIN).set(LOGIN.HASH_PASSWORD, hashedPass).set(LOGIN.PASSWORD_SALT, newSalt).where(LOGIN.USERNAME.eq(username)).execute();

        //Verify that the password is updated:
        Result<Record> res = database.select().from(LOGIN).where(LOGIN.USERNAME.eq(username)).fetch();
        Record record = res.get(0);
        
        updated = Arrays.equals(hashedPass, record.getValue(LOGIN.HASH_PASSWORD)) && Arrays.equals(newSalt, record.getValue(LOGIN.PASSWORD_SALT));
        
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