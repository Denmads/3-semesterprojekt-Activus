package Persistence;

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
 * A singleton class to provide a singular connection pool to multiple classes.
 * @author Morten Kargo Lyngesen
 */
public class DatabaseConnection {
    private static final DatabaseConnection INSTANCE = new DatabaseConnection();
    private static BasicDataSource connectionPool;
    
    
    private Properties prop;
    //Database connection parameters
    private final String dbAddressString;
    private final String username;
    private final String password;
    
    private DatabaseConnection () {
        readConfig();
        
        String ipAddress = prop.getProperty("databaseIpAddress");
        String port = prop.getProperty("databasePort");
        String dbName = prop.getProperty("databaseName");
        dbAddressString = "jdbc:postgresql://" + ipAddress + ":" + port + "/" + dbName;
        
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
        try (FileReader reader = new FileReader(new File("config.properties"))) {
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
    public static Connection getConnection () throws SQLException{
        return connectionPool.getConnection();
    }
    
    
    public static void close() throws SQLException {
        connectionPool.close();
    }
}