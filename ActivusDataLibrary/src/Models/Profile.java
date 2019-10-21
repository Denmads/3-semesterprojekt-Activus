package Models;

import java.io.Serializable;
import java.util.List;

public class Profile implements Serializable {

    private int profileId;
    private String firstName;
    private String lastName;
    private String username;
    private String city;
    private String country;
    private String gender;
    private String gym;

    private int[] buddyIds;

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

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    public int[] getBuddyIds() {
        return buddyIds;
    }

    public void setBuddyIds(int[] buddyIds) {
        this.buddyIds = buddyIds;
    }

}
