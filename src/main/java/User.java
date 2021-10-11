/* Below is the User class which stores users basic information.
 */

public class User {

    public String username;
    public UserInformation information;
    public String gender;

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

}
