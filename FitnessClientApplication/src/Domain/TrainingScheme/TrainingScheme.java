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

    public void setDayTrainingProgramForDictionary(HashMap<Day, TrainingProgram> DayTrainingProgramMap) {
        this.dayTrainingProgramMap = DayTrainingProgramMap;
    }

    public void RemoveTrainingProgramForDay(Day day) {
        this.dayTrainingProgramMap.remove(day);
    }
}