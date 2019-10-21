/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.Actions;

import Enums.DatabaseTableName;
import Models.CredentialsContainer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import persistence.IDatabaseAction;

/**
 *
 * @author madsh
 */
public class AuthenticateTokenAction extends IDatabaseAction<Boolean>{
    
    private boolean result = false;
    private boolean executed = false;
    
    CredentialsContainer credentials;
    
    public AuthenticateTokenAction (CredentialsContainer credentials) {
        this.credentials = credentials;
    }
    
    @Override
    protected void execute(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + getTableName(DatabaseTableName.TOKEN) + " WHERE ");
    }

    @Override
    public Boolean getResult() {
        return ( executed ? result : false);
    }

    @Override
    public boolean hasResult() {
        return executed;
    }
    
}
