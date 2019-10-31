package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * A class to provide a singular connection pool to multiple classes.
 */
public class DatabaseConnection {
    private static BasicDataSource connectionPool;
    
    
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
        
        connectionPool = new BasicDataSource();
        connectionPool.setUsername(this.username);
        connectionPool.setPassword(this.password);
        connectionPool.setDriverClassName("org.postgresql.Driver");
        connectionPool.setUrl(this.dbAddressString);
        connectionPool.setInitialSize(10);
    }
    
    private void readConfig () {
        //Change if path on server is changed
        File f = new File("home/config.properties");
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
        return connectionPool.getConnection();
    }
    
    
    public void close() throws SQLException {
        connectionPool.close();
    }
}