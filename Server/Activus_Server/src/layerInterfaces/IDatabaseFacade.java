/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layerInterfaces;

import java.sql.SQLException;
import persistence.DatabaseConnection;
import persistence.IDatabaseAction;

/**
 * Defines the database facade, which should be able to execute a databaseAction
 *
 * @author madsh
 */
public abstract class IDatabaseFacade {
    protected DatabaseConnection connectionPool;
    
    public IDatabaseFacade () {
        connectionPool = new DatabaseConnection();
    }
    
    public abstract void execute(IDatabaseAction query);
}
