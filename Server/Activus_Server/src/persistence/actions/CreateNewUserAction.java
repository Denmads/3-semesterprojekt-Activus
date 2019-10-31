/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import persistence.IDatabaseAction;
import persistence.util.PasswordTool;
import static persistence.database.generated.Tables.LOGIN;
import static persistence.database.generated.Tables.PROFILE;

/**
 *
 * @author Patrick
 */
public class CreateNewUserAction extends IDatabaseAction<Boolean> {

    private boolean executed;
    private boolean result;

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public CreateNewUserAction(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {
        try {
            byte[] salt = PasswordTool.generateSalt();
            byte[] hashedPassword = PasswordTool.hashPassword(password, salt);
            int loginId = database.insertInto(LOGIN).columns(LOGIN.USERNAME, LOGIN.HASH_PASSWORD, LOGIN.PASSWORD_SALT).values(username, hashedPassword, salt).returning(LOGIN.ID).fetchOne().getValue(LOGIN.ID);
            database.insertInto(PROFILE).columns(PROFILE.LOGINID, PROFILE.FIRST_NAME, PROFILE.LAST_NAME).values(loginId, firstName, lastName).execute();
            executed = true;

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(CreateNewUserAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Result<Record> res = database.select(LOGIN.ID).from(LOGIN).where(LOGIN.USERNAME.eq(username));
        if (!res.isEmpty()) {
            result = true;
        }
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
