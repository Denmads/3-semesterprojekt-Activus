/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import java.sql.Date;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.STATS;

/**
 *
 * @author Patrick
 */
public class SetStatsAction extends IDatabaseAction<Boolean> {

    private boolean result;
    private boolean executed;

    private int profileID;
    private int exerciseID;
    private long timeTaken;
    private int reps;
    private int sets;
    private int weight;
    private Date date;

    public SetStatsAction(int profileID, int exerciseID, long timeTaken, int reps, int sets, int weight, Date date) {
        this.profileID = profileID;
        this.exerciseID = exerciseID;
        this.timeTaken = timeTaken;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.date = date;
    }

    @Override
    protected void execute(DSLContext database) throws SQLException {
    }

    @Override
    public Boolean getResult() {
        return executed ? result : false;
    }

    @Override
    public boolean hasResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
