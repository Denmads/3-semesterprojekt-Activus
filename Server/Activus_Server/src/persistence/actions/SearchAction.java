package persistence.actions;

import Enums.RequestArgumentName;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Profile;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SelectFromStep;
import org.jooq.SelectJoinStep;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.LOGIN;
import static persistence.database.generated.Tables.PROFILE;

/**
 *
 * @author Victor
 */
public class SearchAction extends IDatabaseAction<ArrayList<Profile>> {

    private ArrayList<Profile> returnList = new ArrayList();

    private String search;
    private RequestArgumentName ran;

    public SearchAction(String search, RequestArgumentName ran) {
        this.search = search;
        this.ran = ran;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {

        Result<Record> result;
        SelectJoinStep<Record> baseStep = database.select(PROFILE.asterisk(), LOGIN.USERNAME).from(PROFILE.join(LOGIN).on(PROFILE.LOGIN_ID.eq(LOGIN.ID)));

        if (this.ran.equals(RequestArgumentName.PROFILE_AGE)) {
            result = baseStep.where(PROFILE.AGE.greaterOrEqual(Integer.parseInt(search))).fetch();
        } else {
            result = baseStep.where(PROFILE.CITY.eq(search).or(PROFILE.GENDER.eq(search))).fetch();
        }

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