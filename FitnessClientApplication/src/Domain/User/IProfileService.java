package Domain.User;

import Domain.TraningScheme.Exercise;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import LayerInterfaces.IService;

public abstract class IProfileService extends IService{
    
    public IProfileService(ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
    }
    
public abstract Profile searchProfile (String searchString, boolean buddySearch);
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




