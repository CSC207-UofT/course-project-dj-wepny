

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

//    public static void updateUserInfo() throws Exception{
//        HashMap<Integer, User> allUsers = UserController.getUsers(false);
//        for(Map.Entry<Integer, User> entry : allUsers.entrySet()) {
//            User user = entry.getValue();
//            String id = String.valueOf(user.getId());
//            String name = user.getUsername();
//            String gender = user.getGender();
//            String weight = (String) user.getPersonalData().get("weight");
//            String age = (String) user.getPersonalData().get("age");
//            String height = (String) user.getPersonalData().get("height");
//            UserParser.writeUserInfo(id+","+name+","+gender+","+weight+","+height+","+age);
//        }
//    }

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

//
//    public static void deleteUserInfo(String info) throws IOException {
//        FileWriter writeInfo;
//        writeInfo = new FileWriter(USER_FILE, true);
//
//        writeInfo.write(info+"\n");
//        writeInfo.close();
//    }
//    public static void updateUserName(int id, String newUserName) throws IOException {
//        FileWriter writeInfo;
//
//        ArrayList<String> userInfo = readUserInfo();
//
//        for (int i = 0; i < userInfo.size(); i++) {
//            String individualInfo = userInfo.get(i);
//            List<String> infoList = Arrays.asList(individualInfo.split(","));
//            if (Integer.parseInt(infoList.get(0)) == id) {
//                infoList.set(1, newUserName);
//                individualInfo = infoList.toString();
//                // format the new info into our format
//                String tempStr = individualInfo.replace("[", "");
//                String tempStr2 = tempStr.replace(" ", "");
//                String newIndividualInfo = tempStr2.replace("]", "");
//                // update userInfo with the modified individual info
//                userInfo.set(i, newIndividualInfo);
//            }
//        }
//
//        // userInfo now is a list of strings with the correct info
//        // to update this info back to our csv we need to:
//        // 1. overwrite/clear the existing content
//        // 2. but each string in this new userInfo back one by one
//        for (int i = 0; i < userInfo.size(); i++) {
//            if (i==0){
//                // first  write needs to be overwrite mode to clear the existing csv content
//                writeInfo = new FileWriter(USER_FILE, false);
//                writeInfo.write(userInfo.get(i)+"\n");
//                writeInfo.close();
//            } else {
//                writeUserInfo(userInfo.get(i));
//            }
//        }
//    }
}