package Models;

import Models.SetInfo;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class Exercise {

    private int ID;
    private String name;
    private double time;
    private String Description;
    

    private ArrayList<SetInfo> set;

    public Exercise() {
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    
    public Exercise(int ID, String name, double time) {
        this.ID = ID;
        this.name = name;
        this.time = time;
        this.set = new ArrayList<>();
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public ArrayList<SetInfo> getSet() {
        return set;
    }

    public void setSet(ArrayList<SetInfo> set) {
        this.set = set;
    }

    
    public int getID() {
        return ID;
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

    public void addSetInfo (SetInfo info) {
        set.add(info);
    }
    
    public void removeSetInfo (int index) {
        set.remove(index);
    }
}