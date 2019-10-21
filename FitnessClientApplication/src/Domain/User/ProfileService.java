package Domain.User;

import Models.Profile;
import Domain.serviceInterfaces.IProfileService;
import Domain.DomainFacade;
import Domain.TrainingScheme.Exercise;
import Domain.serviceInterfaces.IAuthenticationService;
import Enums.RequestArgumentName;
import Enums.RequestType;
import Enums.ResponseArgumentName;
import Enums.SearchType;
import Exceptions.ServiceNotFoundException;
import LayerInterfaces.Enums.ServiceType;
import LayerInterfaces.ICommunicationFacade;
import LayerInterfaces.IDomainFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Exceptions.*;
import Models.Request;
import Models.Response;

/**
 *
 * @author steff
 */
public class ProfileService extends IProfileService {

    private Profile currentProfile;

    public ProfileService(Profile currentProfile, ICommunicationFacade communication, IDomainFacade domainFacade) {
        super(communication, domainFacade);
        this.currentProfile = currentProfile;
    }

    @Override
    public List<Profile> search(String searchString, SearchType searchType) {
       
        List<Profile> profiles = null;
        try {
            
            Request request = getAuthenticationService().createRequest(RequestType.SEARCH);
            request.addArgument(RequestArgumentName.SEARCH_TYPE, searchType);
            request.addArgument(RequestArgumentName.TEXT, searchType);
            Response response = communicationLayer.sendRequest(request);
            profiles= (List < Profile >) response.getArgument(ResponseArgumentName.PROFILE);
        } catch (ArgumentNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return profiles;
    }

    @Override
    public Profile getProfile(int profileID) {
        return (Profile) returnObject(RequestType.GET_PROFILE, RequestArgumentName.PROFILE_ID, ResponseArgumentName.PROFILE, profileID);
        
    }

    @Override
    public boolean updateProfile(Profile newProfileInfo) {
        boolean isUpdate;
        currentProfile = (Profile) returnObject(RequestType.UPDATE_PROFILE, RequestArgumentName.PROFILE_ID, ResponseArgumentName.PROFILE, newProfileInfo);
        //To do OPdate the profile in db;
        if(currentProfile!=newProfileInfo){
            isUpdate= false;
        }else{
            isUpdate=true;
        }
        return isUpdate;
    }

    @Override
    public boolean updateProfilePassWord(String newPassword) {
        return (boolean) returnObject(RequestType.UPDATE_PASSWORD, RequestArgumentName.PASSWORD, ResponseArgumentName.SUCCESS, newPassword);
    }

    @Override
    public boolean deleteAccount() {
        return (boolean) returnObject(RequestType.DELETE_ACCOUNT, RequestArgumentName.PROFILE_ID, ResponseArgumentName.SUCCESS, currentProfile);
    }

    @Override
    public boolean followProfile(int profileID) {
        return (boolean) returnObject(RequestType.FOLLOW_PROFILE, RequestArgumentName.PROFILE_ID, ResponseArgumentName.SUCCESS, profileID);
    }

    @Override
    public boolean folloTraniningProgram(int programID) {
        return (boolean) returnObject(RequestType.FOLLOW_TRAINING_PROGRAM, RequestArgumentName.PROGRAM_ID, ResponseArgumentName.SUCCESS, programID);
        
    }

    @Override
    public boolean sendBuddyRequest(int buddyID) {
        return (boolean) returnObject(RequestType.SEND_BUDDY_REQUEST, RequestArgumentName.PROFILE_ID, ResponseArgumentName.SUCCESS, buddyID);
        
    }

    @Override
    public boolean acceptBuddyRequest(int requestingProfileID) {
        return (boolean) returnObject(RequestType.ACCEPT_BUDDY_REQUEST, RequestArgumentName.PROFILE_ID, ResponseArgumentName.SUCCESS, requestingProfileID);
    }

    @Override
    public boolean setGoal(String Goal) {
        return (boolean) returnObject(RequestType.SET_GOAL, RequestArgumentName.TEXT, ResponseArgumentName.SUCCESS, Goal);
    }

    @Override
    public int setStats(Exercise exercise) {
        return (int) returnObject(RequestType.SET_STAT, RequestArgumentName.STAT_ID, ResponseArgumentName.STATS, exercise);
    }

    @Override
    public boolean removeStats(int statsID) {
       return (boolean) returnObject(RequestType.REMOVE_STAT, RequestArgumentName.STAT_ID, ResponseArgumentName.STATS, statsID);
    }

    private IAuthenticationService getAuthenticationService() throws ServiceNotFoundException{
        return  domainFacade.<IAuthenticationService>getService(ServiceType.AUTHENTICATION);
    }
    private Object returnObject(RequestType requestType, RequestArgumentName requestArguementName,ResponseArgumentName responseArguementName, Object o){
        Object object=null;
        try {
            Request request = getAuthenticationService().createRequest(requestType);
            request.addArgument(requestArguementName, o);
            Response response = communicationLayer.sendRequest(request);
            object= response.getArgument(responseArguementName);
        } catch (ServiceNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassCastException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArgumentNotFoundException ex) {
            Logger.getLogger(ProfileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return object;
    }
}