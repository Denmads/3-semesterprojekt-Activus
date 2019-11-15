package Models;

import Models.Exercise;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A training program consists of exercises
 * A model class
 * 
 * @author Victor
 */
public class TrainingProgram implements Serializable{

    private int id;
    
    private String name;
    private String description;
    
    private ArrayList<Exercise> exercises;

    public TrainingProgram() {
        exercises = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public void addExerciseAtIndex(Exercise exercise, int index) {
        this.exercises.add(index, exercise);
    }

    public void removeExercise(Exercise exercise) {
        this.exercises.remove(exercise);
    }
    
    public void removeExerciseAtIndex(int index) {
        this.exercises.remove(index);
    }
    
    public ArrayList<Exercise> getExercises () {
        return exercises;
    }
}