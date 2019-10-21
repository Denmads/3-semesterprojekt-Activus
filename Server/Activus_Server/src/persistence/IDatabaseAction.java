/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import Enums.DatabaseTableName;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author madsh
 */
public abstract class IDatabaseAction <T> {
    protected abstract void execute (Connection connection) throws SQLException;
    
    public abstract T getResult ();
    public abstract boolean hasResult ();
    
    protected String getTableName (DatabaseTableName tableName) {
        return tableName.toString();
    }
}
