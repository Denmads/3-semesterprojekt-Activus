/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.SQLException;
import org.jooq.DSLContext;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.LOGIN;

/**
 *
 * @author madsh
 */
public class IsUsernameUniqueAction extends IDatabaseAction<Boolean>{

    private boolean result = false;
    private boolean exec = false;
    
    private String username;
    
    public IsUsernameUniqueAction (String username) {
        this.username = username;
    }
    
    @Override
    protected void execute(DSLContext database) throws SQLException {
        int res = database.selectCount().from(LOGIN).where(LOGIN.USERNAME.eq(username)).fetchOne().value1();
        
        result = res > 0;
        exec = true;
    }

    @Override
    public Boolean getResult() {
        return (exec ? result : false);
    }

    @Override
    public boolean hasResult() {
        return exec;
    }
    
}
