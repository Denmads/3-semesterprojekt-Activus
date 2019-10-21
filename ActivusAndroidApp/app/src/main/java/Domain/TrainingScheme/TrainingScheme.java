package Domain.TrainingScheme;

import Enums.Day;
import java.util.HashMap;

/**
 *
 * @author Victor
 */
public class TrainingScheme {

    private HashMap<Day, TrainingProgram> dayTrainingProgramMap;
    
    public TrainingScheme() {
        dayTrainingProgramMap = new HashMap<>();
    }

    public void setTrainingProgramAdDay(Day day , TrainingProgram trainingProgram) {
        dayTrainingProgramMap.put(day, trainingProgram);
    }

    
    public void RemoveTrainingProgramForDay(Day day) {
        this.dayTrainingProgramMap.remove(day);
    }
}