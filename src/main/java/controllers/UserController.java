package controllers;

import entities.IUser;
import usecases.UserManager;

import java.util.HashMap;
import java.util.List;

/**
 * This is a Controller class responsible for connecting the UserParser with the UserManager.
 * Checks for existing users in the local file, and adds users to the file if they are
 * new.
 */

public class UserController {

    /**
     * A function called by the UserParser, passes the information read from the userInfo
     * csv into the UserManager to be loaded into a User object
     *
     * @param allUser an List with each element containing information about one user
     */
    public static void readExistingUser(List<String> allUser) {
        for (String user : allUser) {
            String[] userInfo = user.split(",");
            UserManager.loadExistingUser(userInfo);
        }
    }

    /**
     * Get the currentUser from the UserManager
     *
     * @return a User Object
     */
    public static IUser getCurrentUser() {
        return UserManager.getCurrentUser();
    }

    /**
     * Get all the existing users from the UserManager
     *
     * @return a hashmap of all users' id mapped to the user object
     */
    public static HashMap<Integer, IUser> getExistingUsers() {
        HashMap<Integer, IUser> allUser;
        allUser = UserManager.getExistingUsers();

        return allUser;
    }

    /**
     * Check if the user is an existing user.
     *
     * @param id is a string that represent the id of the user.
     * @return true if the user is an existing user and false otherwise.
     */
    public static boolean checkUserExist(String id) {
        return UserManager.getExistingUsers().containsKey(Integer.parseInt(id));
    }

}