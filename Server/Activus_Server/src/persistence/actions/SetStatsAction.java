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
        //After Stats UI Design is made this function needs to be looked at. Benjamin
        Result<Record1<Integer>> res = database.select(STATS.ID).from(STATS).where(STATS.PROFILEID.eq(profileID)).and(STATS.EXERCISEID.eq(exerciseID)).and(STATS.TIME_TAKEN.eq(timeTaken)).and(STATS.REBS.eq(reps)).and(STATS.SETS.eq(sets)).and(STATS.WEIGHT.eq(weight)).and(STATS.DATE.eq(date)).fetch();
        int id = res.get(0).get(STATS.ID);
        if (id > -1) {
            database.update(STATS).set(STATS.PROFILEID, profileID).set(STATS.EXERCISEID, exerciseID).set(STATS.TIME_TAKEN, timeTaken).set(STATS.REBS, reps).set(STATS.SETS,sets).set(STATS.WEIGHT,weight).set(STATS.DATE,date).where(STATS.ID.eq(id)).execute();
        } else {
            database.insertInto(STATS).columns(STATS.PROFILEID, STATS.EXERCISEID, STATS.TIME_TAKEN, STATS.REBS, STATS.SETS, STATS.WEIGHT, STATS.DATE).values(profileID, exerciseID, timeTaken, reps, sets, weight, date).execute();
        }
        
        executed = true;
        Result<Record> temp = database.select().from(STATS).where(STATS.ID.eq(id)).fetch();
        if(!temp.isEmpty()){
            result = true;
        }
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
