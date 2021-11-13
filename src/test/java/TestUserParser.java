/*
 *This file contains Junit test case for the UserParser.java
 */

import Constants.Constants;
import Controllers.UserController;
import Entities.User;
import UseCases.UserManager;
import UseCases.UserParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class TestUserParser {
    User user;

    @Before
    public void setUp1() {
        HashMap<String, Object> userInfo = new HashMap<>();

        userInfo.put("height", "1.88");
        userInfo.put("weight", "65");
        userInfo.put("age", "19");
        user = new User(2030, "Frank", "M", userInfo);
        UserManager.addUser(true, user);
    }

    @Test(timeout = 500)
    public void testOutput1() throws IOException {
        UserParser.writeUserInfo("write", Constants.TEST_USER_FILE);
        ArrayList<String> userInfo = UserParser.readUserInfo(Constants.TEST_USER_FILE);
        assertTrue(userInfo.contains("2030,Frank,M,65,1.88,19,null,null,null,null"));
    }

    @Before
    public void setUp2() throws IOException {
        ArrayList<String> userInfo = UserParser.readUserInfo(Constants.TEST_USER_FILE);
        UserController.readExistingUser(userInfo);
        UserManager.setCurrentUser(2021);
        UserManager.getCurrentUser().setPersonalData("activity level", "Active");
        UserManager.getCurrentUser().setExercisePreference("major muscle", "Legs");
        UserManager.getCurrentUser().setExercisePreference("minor muscle", "Quads");
        UserManager.getCurrentUser().setExercisePreference("equipment", "Bar");
    }

    @Test(timeout = 500)
    public void testOutput2() throws IOException {
        UserParser.writeUserInfo("update", Constants.TEST_USER_FILE);
        ArrayList<String> userInfo = UserParser.readUserInfo(Constants.TEST_USER_FILE);
        assertTrue(userInfo.contains("2021,Amy,F,58,1.70,21,Legs*Quads*Bar,null,Active,null"));
    }

}
