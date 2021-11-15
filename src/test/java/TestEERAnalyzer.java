/*
 *This file contains Junit test case for the EERAnalyzer.java
 */

import Entities.User;
import UseCases.EERAnalyzer;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestEERAnalyzer {
    EERAnalyzer eer;
    User user;

    @Before
    public void setUp() {
        HashMap<String, Object> userInfo = new HashMap<>();

        userInfo.put("height", "1.70");
        userInfo.put("weight", "58");
        userInfo.put("age", "21");
        user = new User(20, "Amy", "F", userInfo);
        user.setPersonalData("activity level", "Active");

        eer = new EERAnalyzer(user);
    }

    @Test(timeout = 500)
    public void testOutput() {
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
