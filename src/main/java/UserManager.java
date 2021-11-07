import java.lang.reflect.Array;
import java.util.*;
import java.lang.Math;

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
     */
    public static void loadExistingUser(String[] userInfo){
        int id = Integer.parseInt(userInfo[0]);
        String name = userInfo[1];
        String gender = userInfo[2];
        String weight = userInfo[3];
        String height = userInfo[4];
        String age = userInfo[5];
        HashMap<String, Object> personal = new HashMap<String, Object>();
        personal.put("height", height);
        personal.put("weight", weight);
        personal.put("age", age);
        User user = new User(id, name, gender, personal);

        if (!userInfo[6].equals("null")){
            String[] exercise = userInfo[6].split("\\*");

            user.setExercisePreference("major muscle", exercise[0]);
            user.setExercisePreference("minor muscle", exercise[1]);
            user.setExercisePreference("equipment", exercise[2]);
        }

        if(!userInfo[7].equals("null")){
            String[] risk = userInfo[7].split("\\*");
            for (String s : risk){
                user.addRiskFactor(s);
            }
        }

        if (!userInfo[8].equals("null")){
            user.setPersonalData("activity level", userInfo[8]);
        }
//
//        if (!userInfo[9].equals("null")){
//
//        }
//
        addUser(false, user);
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

    public static User getCurrentUsers() {
        return currentUser;
    }

    public static void addNewInfo(Object info, int command){
        if (command == 2){
            currentUser.setPersonalData("activity level", (String) info);
        }
        else if (command == 3){
            String[] info1 = (String[]) info;
//                ArrayList<String> exerciseList = new ArrayList<>();

            String majorMuscle = info1[0];
            String minorMuscle = info1[1];
            String equipment = info1[2];

            currentUser.setExercisePreference("major muscle", majorMuscle);
            currentUser.setExercisePreference("minor muscle", minorMuscle);
            currentUser.setExercisePreference("equipment", equipment);
        }
        // TODO: add food generator command
        else{
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

//    public static void changeInfo(Object info, int command){
//        switch(command){
//            case 2: currentUser.setPersonalData("activity level", (String) info);
//                // temporary test diseaseAnalyzer
//            case 4:
//                currentUser.setRiskFactor((ArrayList<String>)info);
//        }
//    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(int id){
        currentUser = existingUsers.get(id);
    }
}


