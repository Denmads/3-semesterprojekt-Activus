/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import models.Exercise;
import models.SetInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import org.jooq.DSLContext;
import org.jooq.JoinType;
import org.jooq.Record;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.EXERCISE;
import static persistence.database.generated.Tables.EXERCISE_SET;
import static persistence.database.generated.Tables.SET;


/**
 *
 * @author steff
 */
public class GetAllExerciseAction extends IDatabaseAction<ArrayList<Exercise>>{
    
    private ArrayList<Exercise> exerciseList = null;        

    @Override
    protected void execute(DSLContext database) throws SQLException {
        Result<Record> result = database.select().from(EXERCISE).fetch();
        this.exerciseList = new ArrayList<>();
        //Map translate to model class and add records to exercise list   
        for (Record r: result) {
            Exercise e = new Exercise();
            e.setName(r.getValue(EXERCISE.NAME));
            e.setDescription(r.getValue(EXERCISE.DESCRIPTION));
            e.setID(r.getValue(EXERCISE.ID));
            e.setType(r.getValue(EXERCISE.TYPE));
            
            Result<Record> setResult = database.select().from(EXERCISE_SET.join(SET, JoinType.JOIN).on(EXERCISE_SET.SETID.eq(SET.ID))).where(EXERCISE_SET.EXERCISEID.eq(e.getID())).fetch();
            
            ArrayList<SetInfo> sets = new ArrayList();
            
            for (Record t: setResult) {
                SetInfo si = new SetInfo();
                si.setReps(t.getValue(SET.REBS));
                si.setSetIndex(t.getValue(SET.SET_INDEX));
                si.setWeight(t.getValue(SET.WEIGHT));
                
                sets.add(si);
            }
            
            Collections.sort(sets);
            e.setSet(sets);
            
            exerciseList.add(e);
    }
    }

    @Override
    public ArrayList<Exercise> getResult() {
        return exerciseList;
    }

    @Override
    public boolean hasResult() {
        return exerciseList.isEmpty();
    }
    
    
    
    
    
}
