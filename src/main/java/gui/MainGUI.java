package gui;

import api.UserParser;
import constants.Exceptions;
import constants.SystemConstants;
import controllers.UserController;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * This class is the GUI that runs the program.
 */

public class MainGUI {

    public static void main(String[] args) throws IOException {

        // read userinfo
        List<String> userInfo = UserParser.readUserInfo(SystemConstants.USER_FILE);

        try {
            if (!userInfo.isEmpty()) {
                // load all the information stored in the userInfo.csv into
                // existing users in the UserManager
                UserController.readExistingUser(userInfo);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println(Exceptions.FILE_LOC_ERROR);
        }

        // Calling the main console gui
        JFrame console = new ConsoleGUI();
        console.setVisible(true);
    }
}

// kept the old one just in case
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
//          break;
//        }