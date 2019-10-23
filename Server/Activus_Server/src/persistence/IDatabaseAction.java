package persistence;

import java.sql.SQLException;
import org.jooq.DSLContext;

/**
 * Defines a action in the database, which can be specified with a concrete result type in the implementation
 *
 * @author madsh
 */
public abstract class IDatabaseAction<T> {

    /**
     * Is responsible for creating a sql query and executing and converting the result to a reult of the specified type
     */
    protected abstract void execute(DSLContext database) throws SQLException;

    public abstract T getResult();

    public abstract boolean hasResult();
}