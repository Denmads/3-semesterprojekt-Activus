package Domain.serviceInterfaces;

import Domain.TrainingScheme.Exercise;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import LayerInterfaces.IService;

/**
 *
 * @author Victor
 */
public abstract class ITrainingSchemeService extends IService{

    public ITrainingSchemeService(ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
    }

    public abstract boolean createNewTrainingProgram(String programName);

    public abstract Exercise LoadExercise();
}