package System;

import Controllers.UserController;
import Entities.User;
import Constants.Constants;
import UseCases.UserParser;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class runs the program.
 */
public class Main {

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
                System.out.println(exception.getMessage());
                System.out.println(Constants.FILE_LOC_ERROR);
            }

            boolean logOut = false;
            // if they are a new user run:
            if (!Console.checkExisting(reader)) {
                Console.gatherInfo(reader);
                while (!logOut) {
                    String output = Console.NewUserMenu(reader);
                    System.out.println(output);
                    logOut = Console.logOut(reader);
                }
                Console.addToExisting();
                UserParser.writeUserInfo("write");
            }
            // if they are an existing user, run:
            else{
                int id = Console.loginPage(reader);
                while (!logOut) {
                    String output = Console.ExistingUserMenu(reader, id);
                    System.out.println(output);
                    logOut = Console.logOut(reader);
                }
                UserParser.writeUserInfo("update");
            }

            boolean restart = Console.reStart(reader);
            if (restart){
                break;
            }

        }
    }

}



