/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.actions;

import Models.Exercise;
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
        Result<Record> result = database.select().from(EXERCISE);
        this.exerciseList = new ArrayList<>();
        result.stream().map((r) -> new Exercise((int)r.get(EXERCISE.ID),r.get(EXERCISE.NAME), r.get(EXERCISE.DESCRIPTION))).forEachOrdered((exercise) -> {
            exerciseList.add((Exercise) exercise);
        });      
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
