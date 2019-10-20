package Domain.TraningScheme;

import java.util.HashMap;

/**
 *
 * @author Victor
 */
public class TrainingScheme {

    private HashMap<Day, TrainingProgram> DayTrainingProgramMap;
    
    public TrainingScheme(HashMap<Day, TrainingProgram> DayTrainingProgramMap) {
        this.DayTrainingProgramMap = DayTrainingProgramMap;
    }

    public void setDayTrainingProgramForDictionary(HashMap<Day, TrainingProgram> DayTrainingProgramMap) {
        this.DayTrainingProgramMap = DayTrainingProgramMap;
    }

    public void RemoveTrainingProgramForDay(Day day) {
        this.DayTrainingProgramMap.remove(day);
    }
}