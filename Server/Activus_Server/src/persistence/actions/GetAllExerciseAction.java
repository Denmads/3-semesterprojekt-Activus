/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import models.Exercise;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.EXERCISE;


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
