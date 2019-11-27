package persistence;

import layerInterfaces.IDatabaseFacade;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import java.sql.Connection;
import org.jooq.DSLContext;

/**
 *
 * @author madsh
 */
public class DatabaseFacade extends IDatabaseFacade{
    
    @Override
    public void execute(IDatabaseAction query) {
        try {
            Connection con = connectionPool.getConnection();
            DSLContext database = DSL.using(con, SQLDialect.POSTGRES);
            query.execute(database);
            database.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
