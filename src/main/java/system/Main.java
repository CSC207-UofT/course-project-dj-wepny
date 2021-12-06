//package system;
//
//import controllers.UserController;
//import constants.*;
//import api.UserParser;
//
//import java.util.Scanner;
//import java.util.ArrayList;
//
///**

// * This class runs the program.
// */
//public class Main {
//
//    public static void main(String[] args) throws Exception {
//
//        while (true) {
//            try {
//                // read the userInfo
//                ArrayList<String> userInfo = UserParser.readUserInfo(SystemConstants.USER_FILE);
//                if (!userInfo.isEmpty()) {
//                    // load all the information stored in the userInfo.csv into
//                    // existing users in the UserManager
//                    UserController.readExistingUser(userInfo);
//                }
//            } catch (Exception exception) {
//                System.out.println(exception.getMessage());
//                System.out.println(Exceptions.FILE_LOC_ERROR);
//            }
//
//            // initiate a new scanner object to read user's input
//            Scanner reader = new Scanner(System.in);
//
//            boolean logOut = false;
//            // if they are a new user run:
//            if (!HelperConsole.checkExisting(reader)) {
//                NewUserConsole.gatherInfo(reader);
//                // while the user chooses not to log out
//                while (!logOut) {
//                    String output = NewUserConsole.NewUserMenu(reader);
//                    System.out.println(output);
//                    logOut = HelperConsole.logOut(reader);
//                }
//                // once the new user log out, add them to existing users
//                HelperConsole.addToExisting();
//                // append the new user's information into the file
//                UserParser.writeUserInfo("write", SystemConstants.USER_FILE);
//            }
//            // if they are an existing user, run:
//            else {
//                // check if their id is a valid id using the loginPage
//                int id = ExistingUserConsole.loginPage(reader);
//                // while the user chooses not to log out
//                while (!logOut) {
//                    String output = ExistingUserConsole.ExistingUserMenu(reader, id);
//                    System.out.println(output);
//                    logOut = HelperConsole.logOut(reader);
//                }
//                //update the file once the user logged out with any changed information
//                UserParser.writeUserInfo("update", SystemConstants.USER_FILE);
//            }
//
//            // check if the user wants to rerun the whole program
//            boolean restart = HelperConsole.reStart(reader);
//            if (restart) {
//                break;
//            }
//
//        }
//    }
//}
