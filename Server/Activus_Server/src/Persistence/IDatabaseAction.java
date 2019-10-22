package Persistence;

import java.sql.SQLException;
import org.jooq.DSLContext;

/**
 *
 * @author madsh
 */
public abstract class IDatabaseAction<T> {

    protected abstract void execute(DSLContext database) throws SQLException;

    public abstract T getResult();

    public abstract boolean hasResult();
}