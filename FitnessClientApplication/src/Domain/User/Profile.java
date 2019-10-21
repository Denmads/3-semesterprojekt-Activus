package Domain.User;

import java.util.List;

public class Profile {

    private String firstName;
    private String lastName;
    private String username;
    private int profileId;
    private int[] buddyIds;
    private List<Profile> buddyList;

    public Profile(String Username, int profileID) {
        this.username = Username;
        this.profileId = profileID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String LastName) {
        this.lastName = LastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String Username) {
        this.username = Username;
    }

    public int getProfileID() {
        return profileId;
    }

    public void setProfileID(int profileID) {
        this.profileId = profileID;
    }

    public int[] getBuddiesID() {
        return buddyIds;
    }

    public void setBuddiesID(int[] buddiesID) {
        this.buddyIds = buddiesID;
    }

    public List<Profile> getBuffyList() {
        return buddyList;
    }

    public void setBuffyList(List<Profile> buffyList) {
        this.buddyList = buffyList;
    }

}