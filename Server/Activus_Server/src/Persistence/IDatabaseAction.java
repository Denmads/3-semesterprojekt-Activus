/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.sql.Connection;
import java.sql.SQLException;
import org.jooq.DSLContext;

/**
 *
 * @author madsh
 */
public abstract class IDatabaseAction <T> {
    protected abstract void execute (DSLContext database) throws SQLException;
    
    public abstract T getResult ();
    public abstract boolean hasResult ();
}
