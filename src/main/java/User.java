/* Below is the User class which stores users basic information.
 */

public class User {

    private final String username;
    private final UserInformation information;
    private final String gender;

    /**
     * Construct a User, with a username they chose, the information of the user,
     * and the user's gender
     *
     * @param username Username of the user
     * @param information  Stores the information object of the user
     * @param gender  Gender of the user
     */

    public User(String username, String gender, UserInformation information){
        this.username= username;
        this.gender = gender;
        this.information = information;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public UserInformation getInformation() {
        return information;
    }

}
