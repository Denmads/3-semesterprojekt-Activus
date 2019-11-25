package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class Exercise implements Serializable{

    private int ID;
    private String name;
    private double time;
    private String Description;
    private String type;
    private int indexInProgram;

    private ArrayList<SetInfo> set;

    public Exercise() {
        this.set = new ArrayList<>();
    }

    public Exercise(int ID, String name, double time, String type) {
        this.ID = ID;
        this.name = name;
        this.time = time;
        this.set = new ArrayList<>();
        this.Description = "no description";
        this.type = type;
        this.indexInProgram = -1;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
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

    public void addSetInfo(SetInfo info) {
        set.add(info);
    }

    public void removeSetInfo(SetInfo info) {
        set.remove(info);
    }

    public ArrayList<SetInfo> getSetInfo() {
        return set;
    }

    public int getIndexInProgram() {
        return indexInProgram;
    }

    public void setIndexInProgram(int indexInProgram) {
        this.indexInProgram = indexInProgram;
    }
    
    public Exercise clone () {
        Exercise clone = new Exercise();
        clone.setID(getID());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setType(getType());
        
        ArrayList<SetInfo> sets = new ArrayList<>();
        
        for (SetInfo set : getSet()) {
            sets.add(set.clone());
        }
        
        clone.setSet(sets);
        
        return clone;
    }
}