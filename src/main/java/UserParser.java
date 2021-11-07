

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
     * @param user The user of interest.
     * @throws IOException In case if there's something wrong with the file.
     */
    public static void writeUserInfo(User user) throws IOException {

        String userInfo = Integer.toString(user.getId()) + "," + user.getUsername() + "," + user.getGender() + "," +
                user.getPersonalData().get("weight") + "," + user.getPersonalData().get("height") + "," +
                user.getPersonalData().get("age");

        FileWriter writeInfo;
        writeInfo = new FileWriter(USER_FILE, true);

        writeInfo.write(userInfo+"\n");
        writeInfo.close();
    }

    /**
     * Adds all the user in the hashmap to the file.
     * @param allUsers A hashmap with an integer as a key and an user as a value.
     * @throws IOException In case if there's something wrong with the file.
     */
    public static void updateUserInfo(HashMap<Integer, User> allUsers) throws IOException{
        new FileWriter(USER_FILE, false).close();
        for (User user : allUsers.values()){
            UserParser.writeUserInfo(user);
        }
    }

}
