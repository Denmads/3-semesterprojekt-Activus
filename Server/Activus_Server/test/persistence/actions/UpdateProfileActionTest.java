package persistence.actions;

import java.util.ArrayList;
import java.util.List;
import models.Profile;
import org.jooq.TableField;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import persistence.DatabaseFacade;
import static persistence.database.generated.Tables.PROFILE;

/**
 *
 * @author Victor
 */
public class UpdateProfileActionTest {

    DatabaseFacade df = new DatabaseFacade();

    String username = "testing";
    String firstname = "newFname";
    String lastname = "newLname";
    String city = "newCity";

    List<Object> changes = new ArrayList<>();
    List<TableField> tableFields = new ArrayList<>();

    UpdateProfileAction update = new UpdateProfileAction(26, changes, tableFields);

    private void updateProfile() {
        changes.clear();
        tableFields.clear();

        int id = 26;
        Profile temp = new Profile(id);
        temp.setGym("Gym");
        temp.setCity(city);
        temp.setAge(Integer.parseInt("25"));
        temp.setFirstName(firstname);
        temp.setLastName(lastname);
        temp.setGender("Male");
        temp.setCountry("Denmark");
        temp.setActiveBuddy(true);

        update(temp);
    }

    private void update(Profile profile) {
        changes.add(profile.getGym());
        tableFields.add(PROFILE.GYM);
        changes.add(profile.getCity());
        tableFields.add(PROFILE.CITY);
        changes.add(profile.getAge());
        tableFields.add(PROFILE.AGE);
        changes.add(profile.getFirstName());
        tableFields.add(PROFILE.FIRST_NAME);
        changes.add(profile.getLastName());
        tableFields.add(PROFILE.LAST_NAME);
        changes.add(profile.getGender());
        tableFields.add(PROFILE.GENDER);
        changes.add(profile.getCountry());
        tableFields.add(PROFILE.COUNTRY);
        changes.add(true);
        tableFields.add(PROFILE.ACTIVEBUDDY);
    }

    public UpdateProfileActionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class UpdateProfileAction.
     */
    @Test
    public void testExecute() throws Exception {
        updateProfile();

        df.execute(update);

        assertEquals(true, update.hasResult());
    }

    /**
     * Test of getResult method, of class UpdateProfileAction.
     */
    @Test
    public void testGetResult() {
        updateProfile();

        df.execute(update);

        assertEquals(true, update.getResult());

    }

    /**
     * Test of hasResult method, of class UpdateProfileAction.
     */
    @Test
    public void testHasResult() {
        updateProfile();

        df.execute(update);

        assertEquals(true, update.hasResult());
    }

}