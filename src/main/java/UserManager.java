import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;

/**
 * This class creates new users, loads data for existing users, and can change user's username.
 */
class UserManager {
    private static HashMap<Integer, User> existingUsers = new HashMap<Integer, User>();
    private static User currentUser;


    /**
     * A static function that creates and return a new User object
     * @param basic is an arraylist of name and gender
     * @param personal is an arraylist of weight, height, and age
     * @return a new User object
     */
    public static void createNewUser(String[] basic, String[] personal) {

            HashMap<String, Object> userInfo = new HashMap<String, Object>();

            userInfo.put("height", personal[0]);
            userInfo.put("weight", personal[1]);
            userInfo.put("age", personal[2]);


            int id = (int)Math.floor(Math.random()*(10000)+1);
            while (existingUsers.containsKey(id)){
                id = (int)Math.floor(Math.random()*(10000)+1);
            }

            User user = new User(id, basic[0], basic[1], userInfo);
            addUser(true, user);
        }


//    public static User loadExistingUser(String name, String gender, HashMap<String, Object> personalData,
//                                                                            HashMap<String, Object> food,
//                                        HashMap<String, Object> exercise, HashMap<String, Object> disease)


    /**
     * Loads the existing user based on the inputs.
     * @param id A string representing the user's ID.
     * @param name A string representing the user's name.
     * @param gender A string representing a user's gender.
     * @param weight A string representing the weight of the user.
     * @param height A string representing the height of the user.
     * @param age A string representing the age of the user.
     * @return A User object.
     */
    public static User loadExistingUser(String id, String name, String gender, String weight, String height, String age){
        HashMap<String, Object> userInfo = new HashMap<String, Object>();

        userInfo.put("height", height);
        userInfo.put("weight", weight);
        userInfo.put("age", age);
        int id1 = Integer.parseInt(id);

        return new User(id1, name, gender, userInfo);
    }

    /**
     * Add the user to either newUsers or existingUsers depending on userType
     * @param newUser a boolean that describes if this is an new user or an existing one from the local files
     */
    public static void addUser(boolean newUser, User user){
        if (newUser){
            currentUser = user;
        }
        else{
            existingUsers.put(user.getId(), user);
        }
    }

    // getters for the private instance variables.

    public static HashMap<Integer, User> getExistingUsers() {
        return existingUsers;
    }

    public static User getNewUsers() {
        return currentUser;
    }

    public static void addNewInfo(Object info, int command){
        switch(command){
            case 2: currentUser.setPersonalData("activity level", (String) info);
                // temporary test diseaseAnalyzer
            case 4:
                currentUser.addRiskFactor((String) info);
        }
    }

//    public static User getExistingUser(int id) {
//        HashMap<Integer, User> users = existingUsers;
//        return users.getOrDefault(id, null);
//    }

    /**
     * changes the username of the user based on the given string.
     * @param newName A string representing the new username for the user.
     * @throws Exception In case the username string is invalid (?)
     */
    public static void changeUserName(String newName) throws Exception {
        currentUser.setUserName(newName);
//        UserParser.updateUserInfo();
    }

    public static void changeInfo(Object info, int command){
        switch(command){
            case 2: currentUser.setPersonalData("activity level", (String) info);
                // temporary test diseaseAnalyzer
            case 4:
                currentUser.setRiskFactor((ArrayList<String>)info);
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(int id){
        currentUser = existingUsers.get(id);
    }
}


