package Communication;

import Exceptions.ConfigFileNotFound;
import java.io.IOException;

/**
 *
 * @author Victor
 */
public class ConnectionTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ConfigFileNotFound, IOException {
        CommunicationFacade CF = new CommunicationFacade();
        CF.openConnection();
        CF.closeConnection();
    }

}