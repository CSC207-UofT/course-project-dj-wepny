/*
 *This file contains Junit test case for the UserParser.java
 */

import Constants.SystemConstants;
import Controllers.UserController;
import Entities.User;
import UseCases.UserManager;
import API.UserParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class TestUserParser {
    User user;

    @Before
    public void setUpUser() {
        HashMap<String, Object> userInfo = new HashMap<>();

        userInfo.put("height", "1.88");
        userInfo.put("weight", "65");
        userInfo.put("age", "19");
        // initiate a new user
        user = new User(2030, "Frank", "M", userInfo);
        UserManager.addUser(true, user);
    }

    @Test(timeout = 500)
    public void testOutputWrite() throws IOException {
        // write the information of the new user into TestParser.csv
        UserParser.writeUserInfo("write", SystemConstants.TEST_USER_FILE);
        // read the TestParser.csv and store the information into an ArrayList
        ArrayList<String> userInfo = UserParser.readUserInfo(SystemConstants.TEST_USER_FILE);
        // check that the new user is written into file
        assertTrue(userInfo.contains("2030,Frank,M,65,1.88,19,null,null,null,null"));
    }

    @Before
    public void setUpNewInfoForUser() throws IOException {
        // read the TestParser.csv and store the information into an ArrayList
        ArrayList<String> userInfo = UserParser.readUserInfo(SystemConstants.TEST_USER_FILE);
        // call UserController to read the userInfo to store the information of each user as a User object
        UserController.readExistingUser(userInfo);
        // set current user as the user with id 2021
        UserManager.setCurrentUser(2021);
        // update the information of the current user
        UserManager.getCurrentUser().setPersonalData("activity level", "Active");
        UserManager.getCurrentUser().setExercisePreference("major muscle", "Legs");
        UserManager.getCurrentUser().setExercisePreference("minor muscle", "Quads");
        UserManager.getCurrentUser().setExercisePreference("equipment", "Bar");
    }

    @Test(timeout = 500)
    public void testOutputUpdateUser() throws IOException {
        // update the TestParser.csv
        UserParser.writeUserInfo("update", SystemConstants.TEST_USER_FILE);
        // read the TestParser.csv and store the information into an ArrayList
        ArrayList<String> userInfo = UserParser.readUserInfo(SystemConstants.TEST_USER_FILE);
        // check that the information of the user is updated in file
        assertTrue(userInfo.contains("2021,Amy,F,58,1.70,21,Legs*Quads*Bar,null,Active,null"));
    }
}

