package persistence.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import models.Profile;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.LOGIN;
import static persistence.database.generated.Tables.PROFILE;

/**
 *
 * @author Victor
 */
public class SearchAction extends IDatabaseAction<ArrayList<Profile>> {

    private ArrayList<Profile> returnList = new ArrayList();

    private int age;

    private String gender;

    private String city;

    public SearchAction(int age, String gender, String city) {
        this.age = age;
        this.gender = gender;
        this.city = city;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {

        Result<Record> result = database.select().from(PROFILE).where(PROFILE.AGE.eq(age).or(PROFILE.CITY.eq(city).or(PROFILE.GENDER.eq(gender)))).fetch();

        for (Record r : result) {
            Profile profile = new models.Profile(r.getValue(PROFILE.ID));
            profile.setAge(r.getValue(PROFILE.AGE));
            profile.setCity(r.getValue(PROFILE.CITY));
            profile.setCountry(r.getValue(PROFILE.COUNTRY));
            profile.setFirstName(r.getValue(PROFILE.FIRST_NAME));
            profile.setLastName(r.getValue(PROFILE.LAST_NAME));
            profile.setGender(r.getValue(PROFILE.GENDER));
            profile.setGym(r.getValue(PROFILE.GYM));
            profile.setUsername(r.getValue(LOGIN.USERNAME));

            returnList.add(profile);
        }

    }

    @Override
    public ArrayList<Profile> getResult() {
        return returnList;
    }

    @Override
    public boolean hasResult() {
        return returnList.isEmpty();
    }

}