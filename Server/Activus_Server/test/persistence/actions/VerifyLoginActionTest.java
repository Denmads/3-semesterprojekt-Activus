package persistence.actions;

import Enums.RequestArgumentName;
import models.CredentialsContainer;
import org.jooq.DSLContext;
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
public class VerifyLoginActionTest {
    
    String username = "Vielm";
    String password = "1234";
    
    //Creating test object
    VerifyLoginAction verify = new VerifyLoginAction(username, password);
    DatabaseFacade df = new DatabaseFacade();
    
    public VerifyLoginActionTest() {
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
     * Test of execute method, of class VerifyLoginAction.
     */
    @Test
    public void testExecute() throws Exception {
        //Verifying that the login works.
        //In that also verifying that the hashing of passwords works.
        df.execute(verify);
        assertEquals(true, verify.hasResult());
    }

    /**
     * Test of getResult method, of class VerifyLoginAction.
     */
    @Test
    public void testGetResult() {
        df.execute(verify);
        
        //Verifying that the credentials of the user exists
        //And therefore also verifying that the user exists in the database.
        int loginID = verify.getResult().getLoginId();
        assertEquals(4, loginID);
    }

    /**
     * Test of hasResult method, of class VerifyLoginAction.
     */
    @Test
    public void testHasResult() {
        df.execute(verify);
        assertEquals(true, verify.hasResult());
    }
    
}
