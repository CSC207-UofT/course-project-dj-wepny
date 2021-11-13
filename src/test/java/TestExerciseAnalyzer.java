/*
 *This file contains Junit test case for the ExerciseAnalyzer.java for when
 * an appropriate list of exercises is given
 */

import UseCases.UserManager;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.HashMap;
import UseCases.ExerciseAnalyzer;
import Entities.User;

public class TestExerciseAnalyzer {
    ExerciseAnalyzer exercises;
    User user;

    @Before
    public void setUp() {
        // call UserManager to create a new user
        UserManager.createNewUser(new String[]{"John", "M"}, new String[]{"1.80", "65", "21"});
        // set user to currentUser
        user = UserManager.getCurrentUser();

        // set the exercise preference of user
        user.setExercisePreference("major muscle", "Arms");
        user.setExercisePreference("minor muscle", "Biceps");
        user.setExercisePreference("equipment", "Dumbbells");

        // initiate a new ExerciseAnalyzer of the current user
        exercises = new ExerciseAnalyzer(user);

    }

    @Test(timeout = 500)
    public void testOutput() {
        // analyze the exercise suggestion for the user
        exercises.analyze();
        String expected_string ="\n*****************************************************************************\n" +
                "Exercises for " + user.getUsername() + ": \n" +
                "The following exercises are based on your preferences on the muscles exercised and equipment. \n\n" +
                "-  Arnold Press\n" +
                "    -> Type of the exercise: [Weight]\n" +
                "    -> Uses: [Dumbbells];\n" +
                "    -> The major muscle exercised is: [Arms]\n" +
                "    -> The minor muscle exercised is: [Bicep, Shoulders]\n" +
                "\n" +
                "\n" +
                "-  Chest Press\n" +
                "    -> Type of the exercise: [Weight]\n" +
                "    -> Uses: [Dumbbells];\n" +
                "    -> The major muscle exercised is: [Arms]\n" +
                "    -> The minor muscle exercised is: [Chest]\n" +
                "\n" +
                "\n" +
                "-  Close to Wide Grip Burnout\n" +
                "    -> Type of the exercise: [Weight]\n" +
                "    -> Uses: [Dumbbells];\n" +
                "    -> The major muscle exercised is: [Arms]\n" +
                "    -> The minor muscle exercised is: [Bicep]\n" +
                "\n" +
                "\n" +
                "-  Side Arm / Lateral Raise\n" +
                "    -> Type of the exercise: [Weight]\n" +
                "    -> Uses: [Dumbbells];\n" +
                "    -> The major muscle exercised is: [Arms]\n" +
                "    -> The minor muscle exercised is: [Lats]\n" +
                "\n" +
                "\n" +
                "-  Tricep Kick-Back\n" +
                "    -> Type of the exercise: [Weight]\n" +
                "    -> Uses: [Dumbbells];\n" +
                "    -> The major muscle exercised is: [Arms]\n" +
                "    -> The minor muscle exercised is: [Tricep]\n" +
                "\n" +
                "\n" +
                "-  Tricep Overhead Press\n" +
                "    -> Type of the exercise: [Weight]\n" +
                "    -> Uses: [Dumbbells];\n" +
                "    -> The major muscle exercised is: [Arms]\n" +
                "    -> The minor muscle exercised is: [Tricep]\n" +
                "\n" +
                "\n" +
                "*****************************************************************************\n";
        assertEquals(expected_string,
                exercises.getResult());
    }

}
