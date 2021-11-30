package GUI;

import api.UserParser;
import constants.Exceptions;
import constants.SystemConstants;
import controllers.UserController;

import javax.swing.*;
import java.util.ArrayList;

public class MainGUI {

    public static void main(String[] args){
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
        // Calling the main console gui
        JFrame console = new ConsoleGUI();
        console.setVisible(true);
    }
}
