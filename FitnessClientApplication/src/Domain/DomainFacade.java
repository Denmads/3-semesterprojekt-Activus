package Domain;

import Domain.Authentication.AuthenticationService;
import Exceptions.ServiceNotFoundException;
import LayerInterfaces.Enums.ServiceType;
import LayerInterfaces.ICommunication;
import LayerInterfaces.IDomainFacade;
import LayerInterfaces.IService;
import java.util.HashMap;

/**
 *
 * @author Victor
 */
public class DomainFacade implements IDomainFacade{

    private HashMap<ServiceType, IService> services;
    
    public DomainFacade (ICommunication communication) {
        services = new HashMap<>();
        InitializeServices(communication);
    }
    
    private void InitializeServices (ICommunication communication) {
        services.put(ServiceType.AUTHENTICATION, new AuthenticationService(communication));
    }
    
    @Override
    public <T extends IService> T GetService(ServiceType type) throws ServiceNotFoundException{
        if (!services.containsKey(type)) {
            throw new ServiceNotFoundException();
        }
        
        return (T)services.get(type);
    }
    
}