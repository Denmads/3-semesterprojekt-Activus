package models;

import java.io.Serializable;

/**
 * Contains info about one set of an exercise
 * A model class
 *
 * @author Victor
 */
public class SetInfo implements Comparable<SetInfo>, Serializable{

    private int id;
    private int reps;
    private double weight;
    private int setIndex;


    public SetInfo() {
    }

    
    public SetInfo(int reps, double weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getSetIndex() {
        return setIndex;
    }

    public void setSetIndex(int setIndex) {
        this.setIndex = setIndex;
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

    @Override
    public int compareTo(SetInfo o) {
        return setIndex - o.getSetIndex();
    }

    public SetInfo clone () {
        SetInfo clone = new SetInfo();
        clone.setSetIndex(getSetIndex());
        clone.setReps(getReps());
        clone.setWeight(getWeight());
        return clone;
    }
}