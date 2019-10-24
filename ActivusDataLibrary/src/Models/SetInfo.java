package Models;

/**
 * Contains info about one set of an exercise
 * A model class
 *
 * @author Victor
 */
public class SetInfo {

    private int reps;
    private double weight;

    public SetInfo(int reps, double weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}