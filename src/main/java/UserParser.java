/*
 * A class to write user information for existing
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserParser {

    public static final String USER_FILE = "src/main/java/UserInfo.csv";

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

    public static void updateUserInfo() throws Exception{
        HashMap<Integer, User> allUsers = UserController.getUsers(true);
        for(Map.Entry<Integer, User> entry : allUsers.entrySet()) {
            User user = entry.getValue();
            String id = String.valueOf(user.getId());
            String name = user.getUsername();
            String gender = user.getGender();
            String weight = (String) user.getPersonalData().get("weight");
            String age = (String) user.getPersonalData().get("age");
            String height = (String) user.getPersonalData().get("height");
            UserParser.writeUserInfo(id+","+name+","+gender+","+weight+","+height+","+age);
        }
    }

    public static void writeUserInfo(String info) throws IOException {
        FileWriter writeInfo;
        writeInfo = new FileWriter(USER_FILE, true);

        writeInfo.write(info+"\n");
        writeInfo.close();
    }
}