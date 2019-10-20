package Domain.User;

import Domain.TraningScheme.Exercise;

public interface IProfileService {
Profile searchProfile (String searchString, boolean buddySearch);
Profile getProfile (int profileID);
boolean updateProfile(Profile newProfileInfo);
boolean updateProfilePassWord (String newPassword);
boolean deleteAccount();
boolean FollowProfile(int profileID);
boolean FolloTraniningProgram(int programID);
boolean SendBuddyRequest(int buddyID);
boolean AcceptBuddyRequest(int requestingProfileID);
boolean setGoal(String Goal);
int setStats(Exercise exercise);
boolean removeStats(int statsID);






}




