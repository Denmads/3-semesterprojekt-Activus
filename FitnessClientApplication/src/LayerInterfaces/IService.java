package LayerInterfaces;

/**
 *
 * @author madsh
 */
public abstract class IService {

    protected ICommunicationFacade communicationLayer;
    protected IDomainFacade domainFacade;

    public IService(ICommunicationFacade communication, IDomainFacade domainFacade) {
        communicationLayer = communication;
        this.domainFacade = domainFacade;
    }
}