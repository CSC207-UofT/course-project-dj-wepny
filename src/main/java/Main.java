import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static final String USER_FILE = "src/main/java/UserInfo.csv";


    public static void main(String[] args) throws Exception {

        while (true) {
            Scanner reader = new Scanner(System.in);

            try {
                ArrayList<String> userInfo = UserParser.readUserInfo();
                System.out.println(userInfo);
                if (!userInfo.isEmpty()){
                    UserController.readExistingUser(userInfo);
                }
            }
            catch (Exception exception) {
                System.out.println("Uh oh, an error has occurred! " +
                        "Please check that the files exist and are in the specified position.");
            }

            boolean logOut = false;
            // if they are a new user run:
            if (!Console.checkExisting(reader)) {
                User userInfo = Console.gatherInfo(reader);
                while (!logOut) {

                    String output = Console.NewUserMenu(reader, userInfo);
                    System.out.println(output);
                    logOut = Console.logOut(reader);
                }
                User user = UserController.getCurrentUser();
                UserParser.writeUserInfo(user);

            }
            // if they are an existing user, run:
            else{
                int id = Console.loginPage(reader);
                while (!logOut) {
                    String output = Console.ExistingUserMenu(reader, id);
                    System.out.println(output);
                    logOut = Console.logOut(reader);
                }
                HashMap<Integer, User> allUsers = UserController.getExistingUsers();
                UserParser.updaterUserInfo(allUsers);
            }

            boolean restart = Console.reStart(reader);
            if (restart){
                break;
            }

        }
    }

}



