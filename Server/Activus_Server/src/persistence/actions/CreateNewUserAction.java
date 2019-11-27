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
    private String city;
    private int age;
    private String gender;

    public CreateNewUserAction(String firstName, String lastName, String username, String password, String city, int age, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.city = city;
        this.age = age;
        this.gender = gender;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {
        try {
            byte[] salt = PasswordTool.generateSalt();
            byte[] hashedPassword = PasswordTool.hashPassword(password, salt);
            int loginId = database.insertInto(LOGIN).columns(LOGIN.USERNAME, LOGIN.HASH_PASSWORD, LOGIN.PASSWORD_SALT).values(username, hashedPassword, salt).returning(LOGIN.ID).fetchOne().getValue(LOGIN.ID);
            database.insertInto(PROFILE).columns(PROFILE.LOGIN_ID, PROFILE.FIRST_NAME, PROFILE.LAST_NAME, PROFILE.CITY, PROFILE.AGE, PROFILE.GENDER).values(loginId, firstName, lastName, city, age, gender).execute();
            executed = true;

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(CreateNewUserAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        Result<Record> res = database.select().from(LOGIN).where(LOGIN.USERNAME.eq(username)).fetch();
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