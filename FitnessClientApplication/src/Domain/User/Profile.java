package Domain.User;

import java.util.List;

public class Profile {

    private String firstName;
    private String LastName;
    private String Username;
    private int profileID;
    private int[] buddiesID;
    private List<Profile> buffyList;

    public Profile(String Username, int profileID) {
        this.Username = Username;
        this.profileID = profileID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public int[] getBuddiesID() {
        return buddiesID;
    }

    public void setBuddiesID(int[] buddiesID) {
        this.buddiesID = buddiesID;
    }

    public List<Profile> getBuffyList() {
        return buffyList;
    }

    public void setBuffyList(List<Profile> buffyList) {
        this.buffyList = buffyList;
    }

}