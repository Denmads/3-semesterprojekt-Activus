package Domain;

import Domain.Authentication.AuthenticationService;
import Exceptions.ServiceNotFoundException;
import LayerInterfaces.Enums.ServiceType;
import LayerInterfaces.IDomainFacade;
import LayerInterfaces.IService;
import java.util.HashMap;
import LayerInterfaces.ICommunicationFacade;

/**
 *
 * @author Victor
 */
public class DomainFacade implements IDomainFacade{

    private HashMap<ServiceType, IService> services;
    
    public DomainFacade (ICommunicationFacade communicationFacade) {
        services = new HashMap<>();
        InitializeServices(communicationFacade);
    }
    
    private void InitializeServices (ICommunicationFacade communicationFacade) {
        addService(ServiceType.AUTHENTICATION, new AuthenticationService(communicationFacade, this));
    }
    
    @Override
    public <T extends IService> T getService(ServiceType type) throws ServiceNotFoundException, ClassCastException{
        if (!services.containsKey(type)) {
            throw new ServiceNotFoundException();
        }
        
        return (T)services.get(type);
    }

    @Override
    public void addService(ServiceType type, IService service) {
        services.put(type, service);
    }
    
}