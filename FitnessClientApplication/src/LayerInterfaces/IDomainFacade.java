package layerInterfaces;

import Exceptions.ServiceNotFoundException;
import Enums.ServiceType;

/**
 * Defines the domainFacade class
 * Provides acces to the different services
 *
 * @author madsh
 */
public interface IDomainFacade {

    <T extends IService> T getService(ServiceType type) throws ServiceNotFoundException, ClassCastException;

    public void addService(ServiceType type, IService service);
}