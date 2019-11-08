package models;

import models.Exercise;
import java.util.ArrayList;

/**
 * A training program consists of exercises
 * A model class
 * 
 * @author Victor
 */
public class TrainingProgram {

    private ArrayList<Exercise> exercises;

    public TrainingProgram() {
        exercises = new ArrayList();
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

}