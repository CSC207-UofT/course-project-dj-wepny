package GUI;

import API.UserParser;
import Constants.Exceptions;
import Constants.SystemConstants;
import Controllers.UserController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainGUI {

    public static void main(String[] args) throws Exception {
        while (true) {
            try {
                // read the userInfo
                ArrayList<String> userInfo = UserParser.readUserInfo(SystemConstants.USER_FILE);
                if (!userInfo.isEmpty()) {
                    // load all the information stored in the userInfo.csv into
                    // existing users in the UserManager
                    UserController.readExistingUser(userInfo);
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                System.out.println(Exceptions.FILE_LOC_ERROR);
            }

          break;
        }
        JFrame console = new ConsoleGUI();
        console.setVisible(true);
    }
}
