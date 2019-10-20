package Domain.TraningScheme;

import Domain.serviceInterfaces.ITrainingSchemeService;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class Exercise implements ITrainingSchemeService {

    private int ID;
    private String name;
    private double time;
    private ArrayList<SetInfo> Set;
    private SetInfo setInfo;
    
    

    

    

    @Override
    public boolean createNewTrainingScheme(String schemeName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createNewTrainingProgram(String programName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Exercise LoadExercise() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}