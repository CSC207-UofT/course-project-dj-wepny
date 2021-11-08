

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * This class writes user information for existing users onto a file.
 */
public class UserParser {

    public static final String USER_FILE = "src/main/java/UserInfo.csv";

    /**
     * Return an ArrayList of user information inside the file.
     * @return an ArrayList of strings, each element contains infornation for 1 unique user.
     * @throws IOException In case there's something wrong with the file.
     */
    public static ArrayList<String> readUserInfo() throws IOException {
        FileReader readInfo;

        readInfo = new FileReader(USER_FILE);
        ArrayList<String> UserInfo = new ArrayList<String>();
        BufferedReader br = new BufferedReader(readInfo);

        br.readLine();

        String currentUserInfo = br.readLine();
        while (currentUserInfo != null) {
            UserInfo.add(currentUserInfo);
            currentUserInfo = br.readLine();
        }
        br.close();
        readInfo.close();
        return UserInfo;
    }

    /**
     * An helper method that adds the user's information into the file.
     * @throws IOException In case if there's something wrong with the file.
     */
    public static void writeUserInfo(String type) throws IOException {
        if (type.equals("write")){
            User user = UserController.getCurrentUser();
            writeIntoFile(user);
        }
        else if (type.equals("update")){
            new FileWriter(USER_FILE, false).close();
            HashMap<Integer, User> allUsers = UserController.getExistingUsers();
            String header = "id, name, gender, weight, height, age, exercise preference, risk factor, activity level, food\n";
            FileWriter writeInfo;
            writeInfo = new FileWriter(USER_FILE, true);
            writeInfo.write(header);

            writeInfo.close();
            for (User user : allUsers.values()) {
                writeIntoFile(user);
            }
        }
    }

    // helper function for writeUserInfo
    public static void writeIntoFile(User user) throws IOException {
        String userInfo = Integer.toString(user.getId()) + "," + user.getUsername() + "," + user.getGender() + "," +
                    user.getPersonalData().get("weight") + "," + user.getPersonalData().get("height") + "," +
                    user.getPersonalData().get("age");

        if (!user.getExercisePreference().isEmpty()){
            userInfo = userInfo + "," + user.getExercisePreference().get("major muscle") + "*" +
                    user.getExercisePreference().get("minor muscle") + "*" +
                    user.getExercisePreference().get("equipment");
        }
        else{
            userInfo = userInfo + "," + "null";
        }
        if (!user.getRiskFactor().isEmpty()){
            String str = String.join("*",user.getRiskFactor());
            userInfo = userInfo + "," + str;
        }
        else{
            userInfo = userInfo + "," + "null";
        }
        userInfo = userInfo + "," + user.getPersonalData().getOrDefault("activity level", "null");

        if (!user.getFoodPreference().isEmpty()){
//            FoodFilterCriterion foodIsLowCarbs = new FoodIsLowCarbs();
//            FoodFilterCriterion foodIsLowFat = new FoodIsLowFat();
//            FoodFilterCriterion foodIsLowSugar = new FoodIsLowSugar();
//            FoodFilterCriterion foodIsVegetarian = new FoodIsVegetarian();

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
//            if (user.getFoodPreference().get(foodIsLowCarbs)) {
//                lowCarb = "Y";
//            }
//
//            if (user.getFoodPreference().get(foodIsLowFat)) {
//                lowFat = "Y";
//            }
//
//            if (user.getFoodPreference().get(foodIsLowSugar)) {
//                lowSugar = "Y";
//            }
//
//
//            if (user.getFoodPreference().get(foodIsVegetarian)) {
//                veg = "Y";
//            }

            userInfo = userInfo + "," + lowCarb + "*" +
                    lowFat + "*" +
                    lowSugar + "*" +
                    veg+ "*" +
                    user.getNumFood();
        }
        else{
            userInfo = userInfo + "," + "null";
        }


        FileWriter writeInfo;
        writeInfo = new FileWriter(USER_FILE, true);
        writeInfo.write(userInfo+"\n");
        writeInfo.close();
    }


}
