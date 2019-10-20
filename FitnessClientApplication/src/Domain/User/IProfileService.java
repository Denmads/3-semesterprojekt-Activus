package Domain.User;

public interface IProfileService {
Profile curentProfile (Profile profile);
Profile searchProfile (String searchString, boolean buddySearch);
Profile getProfile (int profileID);
boolean updateProfile(Profile newProfileInfo);
boolean updateProfilePassWord (String newPassword);
boolean deleteAccount();
boolean FollowProfile(int profileID);
boolean FolloTraniningProgram(int programID);
boolean SendBuddyRequest(int buddyID);
boolean AcceptBuddyRequest(int requestingProfileID);






}




