package usecases;

import entities.User;

import java.util.*;
import java.lang.Math;

import entities.FoodIsLowCarbs;
import entities.FoodIsLowFat;
import entities.FoodIsLowSugar;
import entities.FoodIsVegetarian;

/**
 * This class keeps track of all the users, and differentiates the existing users from the current user that is using
 * currently running the program. Responsible for creating new users, loading data for existing users,
 * and updating user's profile and personal information.
 */
public class UserManager {

    /**
     * Maps user IDs to the corresponding users.
     */
    private static final HashMap<Integer, entities.IUser> existingUsers = new HashMap<>();

    /**
     * The user currently using the program.
     */
    private static User currentUser;

    /**
     * A static function that creates ca new User object and adds the user to the manager
     *
     * @param basic    is a List of Strings that contains the user's name and gender
     * @param personal is a List of Strings that contains the user's weight, height, and age
     */
    public static void createNewUser(String[] basic, String[] personal) {
        HashMap<String, Object> userInfo = new HashMap<>();

        userInfo.put("height", personal[0]);
        userInfo.put("weight", personal[1]);
        userInfo.put("age", personal[2]);


        int id = (int) Math.floor(Math.random() * (10000) + 1);
        while (existingUsers.containsKey(id)) {
            id = (int) Math.floor(Math.random() * (10000) + 1);
        }
        // initiate a new user based on the given information
        User user = new User(id, basic[0], basic[1], userInfo);
        // set this new user to currentUser
        addUser(true, user);
    }

    /**
     * A static function that takes in the String userInfo obtained from the userInfo.csv, and break down the information
     * to create a new User object to add into existingUsers.
     *
     * @param userInfo is a String that contains the user's id, name, gender, weight, height, age, exercise preference,
     *                 risk factor, activity level, and food.
     */
    public static void loadExistingUser(String[] userInfo) {
        int id = Integer.parseInt(userInfo[0]);
        String name = userInfo[1];
        String gender = userInfo[2];
        String weight = userInfo[3];
        String height = userInfo[4];
        String age = userInfo[5];
        HashMap<String, Object> personal = new HashMap<>();
        personal.put("height", height);
        personal.put("weight", weight);
        personal.put("age", age);
        // initiating a user given its basic information
        User user = new User(id, name, gender, personal);

        // setting the user's exercisePreference
        if (!userInfo[6].equals("null")) {
            String[] exercise = userInfo[6].split("\\*");

            user.setExercisePreference("major muscle", exercise[0]);
            user.setExercisePreference("minor muscle", exercise[1]);
            user.setExercisePreference("equipment", exercise[2]);
        }

        if (!userInfo[7].equals("null")) {
            // adding the user's symptoms into RiskFactors
            String[] risk = userInfo[7].split("\\*");
            for (String s : risk) {
                user.addRiskFactor(s);
            }
        }

        if (!userInfo[8].equals("null")) {
            // adding the user's activity level
            user.setPersonalData("activity level", userInfo[8]);
        }

        if (!userInfo[9].equals("null")) {
            // setting the user's FoodPreference
            String[] food = userInfo[9].split("\\*");
            FoodIsLowCarbs foodIsLowCarbs = new FoodIsLowCarbs();
            FoodIsLowFat foodIsLowFat = new FoodIsLowFat();
            FoodIsLowSugar foodIsLowSugar = new FoodIsLowSugar();
            FoodIsVegetarian foodIsVegetarian = new FoodIsVegetarian();
            List<Boolean> foodFilterCriterion = new ArrayList<>();
            for (String criterion : food) {
                if (criterion.equals("Y")) {
                    foodFilterCriterion.add(true);
                } else {
                    foodFilterCriterion.add(false);
                }
            }
            user.setFoodPreference(foodIsLowCarbs, foodFilterCriterion.get(0));
            user.setFoodPreference(foodIsLowFat, foodFilterCriterion.get(1));
            user.setFoodPreference(foodIsLowSugar, foodFilterCriterion.get(2));
            user.setFoodPreference(foodIsVegetarian, foodFilterCriterion.get(3));
            user.setNumFoods(Integer.parseInt(food[4]));
        }

        // add the initiated user into existingUser
        addUser(false, user);
    }

    /**
     * A static function that adds the user to either currentUser or existingUsers depending on userType
     *
     * @param currentUser a boolean that describes if this is a currentUser or an existing one from the local files
     */
    public static void addUser(boolean currentUser, User user) {
        if (currentUser) {
            UserManager.currentUser = user;
        } else {
            existingUsers.put(user.getId(), user);
        }
    }

    /**
     * A static function that adds the info into the currentUser
     *
     * @param info    information that needs to be added to the user
     * @param command an integer that specifies what type of info needs to be added
     */
    public static void addNewInfo(Object info, int command) {
        if (command == 2) {
            currentUser.setPersonalData("activity level", info);
        } else if (command == 3) {
            String[] info1 = (String[]) info;
            String majorMuscle = info1[0];
            String minorMuscle = info1[1];
            String equipment = info1[2];

            // adding the user's exercise preference to its profile
            currentUser.setExercisePreference("major muscle", majorMuscle);
            currentUser.setExercisePreference("minor muscle", minorMuscle);
            currentUser.setExercisePreference("equipment", equipment);
        } else if (command == 4) {
            // adding the user's symptoms to its profile
            currentUser.addRiskFactor((String) info);
        } else if (command == 5) {
            // adding the user's food preference to its profile
            List<Object> foodPreference = (ArrayList<Object>) info;
            boolean lowCarb = (boolean) foodPreference.get(0);
            boolean lowFat = (boolean) foodPreference.get(1);
            boolean lowSugar = (boolean) foodPreference.get(2);
            boolean vegetarian = (boolean) foodPreference.get(3);
            int numFoods = Integer.parseInt((String) foodPreference.get(4));
            FoodIsLowCarbs foodIsLowCarbs = new FoodIsLowCarbs();
            FoodIsLowFat foodIsLowFat = new FoodIsLowFat();
            FoodIsLowSugar foodIsLowSugar = new FoodIsLowSugar();
            FoodIsVegetarian foodIsVegetarian = new FoodIsVegetarian();
            currentUser.setFoodPreference(foodIsLowCarbs, lowCarb);
            currentUser.setFoodPreference(foodIsLowFat, lowFat);
            currentUser.setFoodPreference(foodIsLowSugar, lowSugar);
            currentUser.setFoodPreference(foodIsVegetarian, vegetarian);
            currentUser.setNumFoods(numFoods);
        } else if (command == 6) {
            // reset the user's symptoms
            currentUser.resetRiskFactor();
        } else if (command == 7) {
            // reset the user's food preference
            currentUser.resetFoodPreference();
        }

    }

    /**
     * A static function that changes the user's current information to info
     *
     * @param info    A string representing the information user want to update.
     * @param command An integer representing which type of information info is
     */
    public static void changeUserInfo(String info, int command) {
        if (command == 1) {
            currentUser.setUserName(info);
        } else if (command == 2) {
            currentUser.getPersonalData().replace("height", info);
        } else if (command == 3) {
            currentUser.getPersonalData().replace("weight", info);
        } else if (command == 4) {
            currentUser.getPersonalData().replace("age", info);
        } else if (command == 5) {
            currentUser.setUserGender(info);
        }
    }

    // getters for the private instance variables.

    public static HashMap<Integer, entities.IUser> getExistingUsers() {
        return existingUsers;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    // setter for the private instance variable CurrentUser.
    public static void setCurrentUser(int id) {
        currentUser = (User) existingUsers.get(id);
    }
}


