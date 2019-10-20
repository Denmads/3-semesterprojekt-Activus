package Domain.serviceInterfaces;

import Domain.TraningScheme.Exercise;
import Domain.User.Profile;
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
public abstract boolean FollowProfile(int profileID);
public abstract boolean FolloTraniningProgram(int programID);
public abstract boolean SendBuddyRequest(int buddyID);
public abstract boolean AcceptBuddyRequest(int requestingProfileID);
public abstract boolean setGoal(String Goal);
public abstract int setStats(Exercise exercise);
public abstract boolean removeStats(int statsID);






}




