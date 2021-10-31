import java.util.HashMap;
import java.lang.Math;

import static java.util.Objects.isNull;

class UserManager {
    private static HashMap<Integer, User> existingUsers = new HashMap<Integer, User>();
    private static HashMap<Integer, User> newUsers =  new HashMap<Integer, User>();


    public static User createNewUser(String[] basic, String[] personal) {
        /**
         * A static function that creates and return a new User object
         * @param basic is an arraylist of name and gender
         * @param personal is an arraylist of weight, height, and age
         * @return a new User object
         */

            HashMap<String, Object> userInfo = new HashMap<String, Object>();

            userInfo.put("height", personal[0]);
            userInfo.put("weight", personal[1]);
            userInfo.put("age", personal[2]);


            int id = (int)Math.floor(Math.random()*(10000)+1);
            while (newUsers.containsKey(id) || existingUsers.containsKey(id)){
                id = (int)Math.floor(Math.random()*(10000)+1);
            }
            return new User(id, basic[0], basic[1], userInfo);
        }


//    public static User loadExistingUser(String name, String gender, HashMap<String, Object> personalData, HashMap<String, Object> food,
//                                        HashMap<String, Object> exercise, HashMap<String, Object> disease)
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
            newUsers.put(user.getId(), user);
        }
        else{
            existingUsers.put(user.getId(), user);
        }
    }

    public static HashMap<Integer, User> getExistingUsers() {
        return existingUsers;
    }

    public static HashMap<Integer, User> getNewUsers() {
        return newUsers;
    }

    public static User getExistingUser(int id) {
        HashMap<Integer, User> users = existingUsers;
        return users.getOrDefault(id, null);
    }

    public static void changeUserName(User user, String newName) throws Exception {
        user.setUserName(newName);
        UserParser.updateUserInfo();
    }
}
