package Domain.TraningScheme;

import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class TrainingProgram {

    private ArrayList<Exercise> exercises;

    public TrainingProgram(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
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