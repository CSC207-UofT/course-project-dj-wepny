package gui;

import api.UserParser;
import constants.Exceptions;
import constants.SystemConstants;
import controllers.UserController;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class acts as the starting point of the program.
 */

public class MainGUI {

    public static void main(String[] args) throws IOException {
        // reading userinfo
        ArrayList<String> userInfo = UserParser.readUserInfo(SystemConstants.USER_FILE);

        try {
            if (!userInfo.isEmpty()) {
                // load all the information stored in the userInfo.csv into
                // existing users in the UserManager
                UserController.readExistingUser(userInfo);
            }
        } catch (Exception exception) {
            // if there is an error while reading the files, output an error instead of program crashing
            System.out.println(exception.getMessage());
            System.out.println(Exceptions.FILE_LOC_ERROR);
        }

        // Calling the main console gui
        JFrame console = new ConsoleGUI();
        console.setVisible(true);
    }
}