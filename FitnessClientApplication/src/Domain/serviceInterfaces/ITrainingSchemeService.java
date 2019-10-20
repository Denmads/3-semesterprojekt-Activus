package Domain.serviceInterfaces;

import Domain.TraningScheme.Exercise;

/**
 *
 * @author Victor
 */
public interface ITrainingSchemeService {

    public boolean createNewTrainingScheme(String schemeName);

    public boolean createNewTrainingProgram(String programName);

    public Exercise LoadExercise();
}