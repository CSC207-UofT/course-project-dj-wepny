import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * This class checks for existing users in the local file, and adds users to the file if they are
 * new.
 */
public class UserController {

    public static void readExistingUser(ArrayList<String> allUser) {
        for (String user : allUser) {
            String[] userInfo = user.split(",");
            User createdUser = UserManager.loadExistingUser(userInfo[0], userInfo[1], userInfo[2],
                    userInfo[3], userInfo[4], userInfo[5]);
            UserManager.addUser(false, createdUser);
        }
    }

    public static User getCurrentUser(){
        return UserManager.getNewUsers();
    }

    public static HashMap<Integer, User> getExistingUsers(){
        HashMap<Integer, User> allUser;
        allUser = UserManager.getExistingUsers();

        return allUser;
    }

//    public static void setPotentialDisease(User user, HashMap<String, Set<String>> potentialDisease){
//        UserManager.setPontentialDisease(user, potentialDisease);
//    }
//
//    public void updateUser(int newId) throws Exception {
//        UserParser.updateUserInfo();
//    }
}