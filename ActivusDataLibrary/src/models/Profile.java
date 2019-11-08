package models;

import java.io.Serializable;


/**
 * Model class for profile
 * 
 * @author madsh
 */
public class Profile implements Serializable {

    private int profileId;
    private String username;
    private String firstName;
    private String lastName;
    private int age;
    private String city;
    private String country;
    private String gender;
    private String gym;

    private int[] buddyIds;

    public Profile(int profileId) {
        this.profileId = profileId;
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

    public int getProfileId() {
        return profileId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    
}
