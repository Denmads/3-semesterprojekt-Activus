package Domain.TrainingScheme;

import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class Exercise {

    private int ID;
    private String name;
    private double time;

    private ArrayList<SetInfo> Set;

    public Exercise(int ID, String name, double time, ArrayList<SetInfo> Set) {
        this.ID = ID;
        this.name = name;
        this.time = time;
        this.Set = Set;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public ArrayList<SetInfo> getSet() {
        return Set;
    }

    public void setSet(ArrayList<SetInfo> Set) {
        this.Set = Set;
    }

}