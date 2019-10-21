package Domain.serviceInterfaces;

import Domain.TrainingScheme.Exercise;
import Models.Profile;
import Enums.SearchType;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import LayerInterfaces.IService;
import java.util.List;

public abstract class IProfileService extends IService{
    
    public IProfileService(ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
    }
    
public abstract List<Profile> search (String searchString, SearchType searchType);
public abstract Profile getProfile (int profileID);
public abstract boolean updateProfile(Profile newProfileInfo);
public abstract boolean updateProfilePassWord (String newPassword);
public abstract boolean deleteAccount();
public abstract boolean followProfile(int profileID);
public abstract boolean folloTraniningProgram(int programID);
public abstract boolean sendBuddyRequest(int buddyID);
public abstract boolean acceptBuddyRequest(int requestingProfileID);
public abstract boolean setGoal(String Goal);
public abstract int setStats(Exercise exercise);
public abstract boolean removeStats(int statsID);






}




