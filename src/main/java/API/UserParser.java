package API;

import Controllers.UserController;
import Entities.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import Entities.FoodIsLowCarbs;
import Entities.FoodFilterCriterion;
import Entities.FoodIsLowFat;
import Entities.FoodIsLowSugar;
import Entities.FoodIsVegetarian;


/**
 * This class writes user information for existing users onto a file.
 */
public class UserParser {

    /**
     * Return an ArrayList of user information inside the file.
     *
     * @return an ArrayList of strings, each element contains information for 1 unique user.
     * @throws IOException In case there's something wrong with the file.
     */
    public static ArrayList<String> readUserInfo(String path) throws IOException {
        FileReader readInfo;

        // initialize a FileReader
        readInfo = new FileReader(path);
        ArrayList<String> UserInfo = new ArrayList<>();
        BufferedReader br = new BufferedReader(readInfo);

        br.readLine();

        String currentUserInfo = br.readLine();
        // add the user information from file into UserInfo as a String.
        while (currentUserInfo != null) {
            UserInfo.add(currentUserInfo);
            currentUserInfo = br.readLine();
        }
        br.close();
        readInfo.close();
        return UserInfo;
    }

    /**
     * Helper method that adds the user's information into the file.
     *
     * @throws IOException In case if there's something wrong with the file.
     */
    public static void writeUserInfo(String type, String path) throws IOException {
        // if the user is a new user, write the user information by appending to the file
        if (type.equals("write")) {
            User user = UserController.getCurrentUser();
            writeIntoFile(user, path);
        }
        // if the user is an existing user and update their profile, rewrite the whole file
        // with the updated information.
        else if (type.equals("update")) {
            new FileWriter(path, false).close();
            HashMap<Integer, User> allUsers = UserController.getExistingUsers();
            String header = "id, name, gender, weight, height, age, exercise preference, risk factor, activity level, food\n";
            FileWriter writeInfo;
            writeInfo = new FileWriter(path, true);
            writeInfo.write(header);

            writeInfo.close();
            for (User user : allUsers.values()) {
                writeIntoFile(user, path);
            }
        }
    }

    /**
     * helper method for writeUserInfo that write the information of the user into file.
     *
     * @param user is the user whose information is writing into the file
     * @throws IOException In case if there's something wrong with the file.
     */
    public static void writeIntoFile(User user, String path) throws IOException {
        // turn the basic information of user into String with , separating the different information
        String userInfo = user.getId() + "," + user.getUsername() + "," + user.getGender() + "," +
                user.getPersonalData().get("weight") + "," + user.getPersonalData().get("height") + "," +
                user.getPersonalData().get("age");

        // turn the exercise preference of user into String with * separating the different muscle and equipment
        // add it to userInfo with , separating it from previous info
        if (!user.getExercisePreference().isEmpty()) {
            userInfo = userInfo + "," + user.getExercisePreference().get("major muscle") + "*" +
                    user.getExercisePreference().get("minor muscle") + "*" +
                    user.getExercisePreference().get("equipment");
        }
        // if user has not yet input exercise preference, add null to userInfo
        // with , separating it from previous info
        else {
            userInfo = userInfo + "," + "null";
        }

        // turn the symptoms of user into String with * separating the different symptoms
        // add it to userInfo with , separating it from previous info
        if (!user.getRiskFactor().isEmpty()) {
            String str = String.join("*", user.getRiskFactor());
            userInfo = userInfo + "," + str;
        }
        // if user has not yet input symptoms, add null userInfo
        // with , separating it from previous info
        else {
            userInfo = userInfo + "," + "null";
        }

        // add activity level of user to userInfo with , separating from the previous info and
        // add null if user has not yet input activity level
        userInfo = userInfo + "," + user.getPersonalData().getOrDefault("activity level", "null");

        // turn the food preference of user into String with * separating the different preferences
        // add it to userInfo with , separating it from previous info
        if (!user.getFoodPreference().isEmpty()) {
            Set<FoodFilterCriterion> preference = user.getFoodPreference().keySet();

            String lowCarb = "N";
            String lowFat = "N";
            String lowSugar = "N";
            String veg = "N";
            for (FoodFilterCriterion pref : preference) {
                if (pref instanceof FoodIsLowCarbs && user.getFoodPreference().get(pref)) {
                    lowCarb = "Y";
                }
                if (pref instanceof FoodIsLowFat && user.getFoodPreference().get(pref)) {
                    lowFat = "Y";
                }
                if (pref instanceof FoodIsLowSugar && user.getFoodPreference().get(pref)) {
                    lowSugar = "Y";
                }
                if (pref instanceof FoodIsVegetarian && user.getFoodPreference().get(pref)) {
                    veg = "Y";
                }
            }
            userInfo = userInfo + "," + lowCarb + "*" +
                    lowFat + "*" +
                    lowSugar + "*" +
                    veg + "*" +
                    user.getNumFood();
        }
        // if user has not yet input food preferences, add null userInfo
        // with , separating it from previous info
        else {
            userInfo = userInfo + "," + "null";
        }

        // write the information of user into UserInfo.csv
        FileWriter writeInfo;
        writeInfo = new FileWriter(path, true);
        writeInfo.write(userInfo + "\n");
        writeInfo.close();
    }


}
