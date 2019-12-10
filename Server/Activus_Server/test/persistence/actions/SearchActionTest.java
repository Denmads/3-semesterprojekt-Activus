package persistence.actions;

import Enums.RequestArgumentName;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import persistence.DatabaseFacade;

/**
 *
 * @author Victor
 */
public class SearchActionTest {
    
    //Creating test object
    SearchAction search = new SearchAction("testCity", RequestArgumentName.PROFILE_CITY);
    DatabaseFacade df = new DatabaseFacade();
    
    
    public SearchActionTest() {
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
     * Test of execute method, of class SearchAction.
     */
    @Test
    public void testExecute() throws Exception {
        df.execute(search);
        //hasResult() returns isEmpty() of an arrayList.
        //So it should be false as the list should contain something.
        assertEquals(false, search.hasResult());
    }

    /**
     * Test of getResult method, of class SearchAction.
     */
    @Test
    public void testGetResult() throws SQLException {
        df.execute(search);
       
        //Verifying that the database has returned something.
        search.getResult();
        assertEquals(false, search.getResult().isEmpty());
        
        //With the test-search it should only return one profile.
        //Therefore testing if the returned profile lives in this city:
        String testCity = search.getResult().get(0).getCity();
        assertEquals("testCity", testCity);
        
        //Testing the rest of the users information
        String testFirstname = search.getResult().get(0).getFirstName();
        String testLastname = search.getResult().get(0).getLastName();
        String testUsername = search.getResult().get(0).getUsername();
        String testGender = search.getResult().get(0).getGender();
        
        assertEquals("testUser", testFirstname);
        assertEquals("testUser", testLastname);
        assertEquals("testing", testUsername);
        assertEquals("Male", testGender);     
    }

    /**
     * Test of hasResult method, of class SearchAction.
     */
    @Test
    public void testHasResult() {
        df.execute(search);
        assertEquals(false, search.hasResult());
    }
    
}
