/*
This class contains the JUnit test cases for UserAnalyzerFactory.
 */

import Entities.User;
import UseCases.*;
import UseCases.UserAnalyzerFactory;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestUserAnalyzerFactory {

    public static UserAnalyzerFactory factory = new UserAnalyzerFactory();

    @Test(timeout = 5000)
    public void testFactoryBMIAnalyzer() {
        UserAnalyzer analyzer = factory.createAnalyzer(1);
        assertTrue(analyzer instanceof BMIAnalyzer);
    }

    @Test(timeout = 5000)
    public void testFactoryEERAnalyzer() {
        UserAnalyzer analyzer = factory.createAnalyzer(2);
        assertTrue(analyzer instanceof EERAnalyzer);
    }

    @Test(timeout = 5000)
    public void testFactoryExerciseAnalyzer() {
        UserAnalyzer analyzer = factory.createAnalyzer(3);
        assertTrue(analyzer instanceof ExerciseAnalyzer);
    }

    @Test(timeout = 5000)
    public void testFactoryDiseaseAnalyzer() {
        UserAnalyzer analyzer = factory.createAnalyzer(4);
        assertTrue(analyzer instanceof DiseaseAnalyzer);
    }

    @Test(timeout = 5000)
    public void testFactoryMealPlanGenerator() {
        UserAnalyzer analyzer = factory.createAnalyzer(5);
        assertTrue(analyzer instanceof MealPlanGenerator);
    }

    @Test(timeout = 5000)
    public void testFactorynull1() {
        UserAnalyzer analyzer = factory.createAnalyzer(0);
        assertNull(analyzer);
    }

    @Test(timeout = 5000)
    public void testFactorynull2() {
        UserAnalyzer analyzer = factory.createAnalyzer(6);
        assertNull(analyzer);
    }

    @Test(timeout = 5000)
    public void testFactorynull3() {
        UserAnalyzer analyzer = factory.createAnalyzer(-5);
        assertNull(analyzer);
    }

}
