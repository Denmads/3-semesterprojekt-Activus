package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class to provide a singular connection pool to multiple classes.
 */
public class DatabaseConnection {
    
    
    private Properties prop;
    //Database connection parameters
    private final String dbAddressString;
    private final String username;
    private final String password;
    
    public DatabaseConnection () {
        readConfig();
        
        String ipAddress = prop.getProperty("databaseIpAddress");
        String dbName = prop.getProperty("databaseName");
        dbAddressString = "jdbc:postgresql://" + ipAddress + "/" + dbName;
        
        username = prop.getProperty("databaseUsername");
        password = prop.getProperty("databasePassword");
    }
    
    private void readConfig () {
        //Change if path on server is changed
        //On server home/
        File f = new File("config.properties");
        System.out.println(f.getAbsolutePath());
        
        try (FileReader reader = new FileReader(f)) {
            prop = new Properties();
            prop.load(reader);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Returns a connection to perform transactions on.
     * @return a connection pool to perform transactions on.
     */
    public Connection getConnection () throws SQLException{
//        return connectionPool.getConnection();

        return DriverManager.getConnection(dbAddressString, username, password);
    }
}