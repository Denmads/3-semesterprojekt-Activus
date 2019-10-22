/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.Actions;

import Models.CredentialsContainer;
import static Persistence.Database.generated.Tables.TOKEN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Persistence.IDatabaseAction;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

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
    protected void execute(DSLContext database) throws SQLException {
        int count = database.selectCount().from(TOKEN).where(TOKEN.LOGINID.eq(credentials.getUserId()).and(TOKEN.TOKEN_.eq(credentials.getAuthenticationToken()))).fetchOne().value1();
        result = count > 0;
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
