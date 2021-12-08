import entities.User;
import usecases.UserManager;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 *This file contains Junit test case for the HelperConsole.java
 */
public class TestHelperConsole {
    User user;


    @Test(timeout = 500)
    public void testIsNotNum() {
        // testing to check if the function correctly identifies the input as a number or not
        boolean notNum = consoleforgui.HelperConsole.isNotNum("10");
        assertFalse(notNum);

        boolean notNum1 = consoleforgui.HelperConsole.isNotNum("hi");
        assertTrue(notNum1);
    }


    @Before
    public void setUp() {
        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("height", "1.88");
        userInfo.put("weight", "65");
        userInfo.put("age", "19");
        // initiate a new user
        user = new User(2030, "Frank", "M", userInfo);
        UserManager.addUser(true, user);

        //setting the user's activity level and exercise preference
        UserManager.getCurrentUser().setPersonalData("activity level", "Active");
        UserManager.getCurrentUser().setExercisePreference("major muscle", "Legs");
        UserManager.getCurrentUser().setExercisePreference("minor muscle", "Quads");
        UserManager.getCurrentUser().setExercisePreference("equipment", "Bar");
    }

    @Test(timeout = 500)
    public void testNoInfoFound() {
        // checking to if the user have activity level, exercise preference, risk factor and food preference
        // stored in its profile
        boolean noActivityInfo = consoleforgui.HelperConsole.noInfoFound(2);
        boolean noExerciseInfo = consoleforgui.HelperConsole.noInfoFound(3);
        boolean noRiskFactorInfo = consoleforgui.HelperConsole.noInfoFound(4);
        boolean noFoodInfo = consoleforgui.HelperConsole.noInfoFound(5);

        assertFalse(noActivityInfo);
        assertFalse(noExerciseInfo);
        assertTrue(noRiskFactorInfo);
        assertTrue(noFoodInfo);
    }
}


