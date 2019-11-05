package Models;

import Enums.Day;
import Models.TrainingProgram;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Storing a link between training program and days it is assigned to
 * A model class
 *
 * @author Victor
 */
public class TrainingScheme implements Serializable{

    private HashMap<Day, TrainingProgram> dayTrainingProgramMap;
    
    public TrainingScheme() {
        dayTrainingProgramMap = new HashMap<>();
    }

    public void setTrainingProgramAtDay(Day day , TrainingProgram trainingProgram) {
        dayTrainingProgramMap.put(day, trainingProgram);
    }

    
    public void RemoveTrainingProgramForDay(Day day) {
        this.dayTrainingProgramMap.remove(day);
    }
}