package persistence;

import LayerInterfaces.IDatabaseFacade;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

/**
 *
 * @author madsh
 */
public class DatabaseFacade implements IDatabaseFacade{

    DatabaseConnection connectionPool;
    
    public DatabaseFacade () {
        connectionPool = new DatabaseConnection();
    }
    
    @Override
    public void execute(IDatabaseAction query) {
        
        
        
        try {
            DSLContext database = DSL.using(connectionPool.getConnection(), SQLDialect.POSTGRES);
            query.execute(database);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
