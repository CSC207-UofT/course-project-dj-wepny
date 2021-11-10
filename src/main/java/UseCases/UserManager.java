package UseCases;

import Entities.User;
import java.lang.reflect.Array;
import java.util.*;
import java.lang.Math;
import Entities.FoodIsLowCarbs;
import Entities.FoodFilterCriterion;
import Entities.FoodIsLowFat;
import Entities.FoodIsLowSugar;
import Entities.FoodIsVegetarian;

import static java.util.Objects.isNull;

/**
 * This class creates new users, loads data for existing users, and can change user's username.
 */
public class UserManager {
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

        if (!userInfo[9].equals("null")){
            String[] food = userInfo[9].split("\\*");
            FoodIsLowCarbs foodIsLowCarbs = new FoodIsLowCarbs();
            FoodIsLowFat foodIsLowFat = new FoodIsLowFat();
            FoodIsLowSugar foodIsLowSugar = new FoodIsLowSugar();
            FoodIsVegetarian foodIsVegetarian = new FoodIsVegetarian();
            ArrayList<Boolean> foodFilterCriterion = new ArrayList<>();
            for(String criterion : food) {
                if (criterion.equals("Y")) {
                    foodFilterCriterion.add(true);
                }
                else {
                    foodFilterCriterion.add(false);
                }
            }
            user.setFoodPreference(foodIsLowCarbs, foodFilterCriterion.get(0));
            user.setFoodPreference(foodIsLowFat, foodFilterCriterion.get(1));
            user.setFoodPreference(foodIsLowSugar, foodFilterCriterion.get(2));
            user.setFoodPreference(foodIsVegetarian, foodFilterCriterion.get(3));
            user.setNumFoods(Integer.parseInt(food[4]));
        }

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

        else if (command == 4){
            currentUser.addRiskFactor((String) info);
        }

        else if (command == 5) {
            ArrayList<Object> foodPreference = (ArrayList<Object>) info;
            boolean lowCarb = (boolean) foodPreference.get(0);
            boolean lowFat = (boolean) foodPreference.get(1);
            boolean lowSugar = (boolean) foodPreference.get(2);
            boolean vegetarian = (boolean) foodPreference.get(3);
            int numFoods = Integer.parseInt((String)foodPreference.get(4));
            FoodIsLowCarbs foodIsLowCarbs = new FoodIsLowCarbs();
            FoodIsLowFat foodIsLowFat = new FoodIsLowFat();
            FoodIsLowSugar foodIsLowSugar = new FoodIsLowSugar();
            FoodIsVegetarian foodIsVegetarian = new FoodIsVegetarian();
            currentUser.setFoodPreference(foodIsLowCarbs, lowCarb);
            currentUser.setFoodPreference(foodIsLowFat, lowFat);
            currentUser.setFoodPreference(foodIsLowSugar, lowSugar);
            currentUser.setFoodPreference(foodIsVegetarian, vegetarian);
            currentUser.setNumFoods(numFoods);
        }
        else if (command == 6) {
            currentUser.resetRiskFactor();
        }
        else if (command == 7) {
            currentUser.resetFoodPreference();
        }

    }

//    public static User getExistingUser(int id) {
//        HashMap<Integer, User> users = existingUsers;
//        return users.getOrDefault(id, null);
//    }

    /**
     * changes the username of the user based on the given string.
     * @param info A string representing the information user want to update.
     * @throws Exception In case the username string is invalid (?)
     */
    public static void changeUserInfo(String info, int command) throws Exception {
//        switch(command) {
//            // change username
//            case 1:
//                currentUser.setUserName(info);
//            case 2:
//                currentUser.getPersonalData().replace("height", info);
//
//            case 6:
//
//        }
        if (command == 1) {
            currentUser.setUserName(info);
        }
        else if (command == 2) {
            currentUser.getPersonalData().replace("height", info);
        }
        else if (command == 3) {
            currentUser.getPersonalData().replace("weight", info);
        }
        else if (command == 4) {
            currentUser.getPersonalData().replace("age", info);
        }
        else if (command == 5) {
            currentUser.setUserGender(info);
        }
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


