/*
 *This file contains Junit test case for the EERAnalyzer.java
 */

import Entities.User;
import UseCases.EERAnalyzer;
import UseCases.UserManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEERAnalyzer {
    EERAnalyzer eer;
    User user;

    @Before
    public void setUp() {
        // call UserManager to create a new user
        UserManager.createNewUser(new String[]{"Amy", "F"}, new String[]{"1.70", "58", "21"});
        // set user to currentUser
        user = UserManager.getCurrentUser();
        // set the activity level of user
        user.setPersonalData("activity level", "Active");

        // initiate a new EERAnalyzer of the current user
        eer = new EERAnalyzer(user);
    }

    @Test(timeout = 500)
    public void testOutput() {
        // analyze the EER of the user
        eer.analyze();
        assertEquals("\n*****************************************************************************\n" +
                        "The Estimated Energy Requirement (EER) is a predicted average dietary intake \n" +
                        "needed to maintain energy balance in the healthy adult of a defined age, gender, weight, \n" +
                        "and a level of physical activity that is consistent with good health.\n" +
                        "Your Estimated Energy Requirement is: 2465.78\n" +
                        "*****************************************************************************\n",
                eer.getResult());
    }

}
