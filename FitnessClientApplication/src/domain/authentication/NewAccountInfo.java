package domain.authentication;

/**
 * Contains minimum info to create a new account A model class
 *
 * @author madsh
 */
public class NewAccountInfo {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String city;
    private int age;
    private String gender;

    public NewAccountInfo(String firstName, String lastName, String username, String password, String city, int age, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.city = city;
        this.age = age;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

}