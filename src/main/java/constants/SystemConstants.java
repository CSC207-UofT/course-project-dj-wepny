package constants;

/**
 * This class contains all the constants related to the system.
 */
public class SystemConstants {

    //GUI
    public static final String RETURN_TO_MENU = "RETURN TO MENU";
    public static final String ENTER = "ENTER";

    // UserParser
    public static final String USER_FILE = "src/main/data/UserInfo.csv";
    public static final String TEST_USER_FILE = "src/test/data/TestParser.csv";

    // API
    public static final String DISEASE_DATASET_PATH = "src/main/data/GlobalDiseaseData.csv";
    public static final String FOOD_DATASET_PATH = "src/main/data/GlobalFoodData.csv";
    public static final String EXERCISE_DATASET_PATH = "src/main/data/ExerciseMovesData.csv";
    public static final double FAT_PER_DAY = 75.0;
    public static final double CARB_PER_DAY = 300.0;
    public static final double SUGAR_PER_DAY = 100.0;

    // Decorators
    public static final String DIVIDER = "\n*****************************************************************************\n";
    public static final String EMPTY_LINE = "\n\n";

    //Console
    public static final String ENTER_PIN = "Please Enter Your Personal Identification Pin if you are an existing User:";


    // Messages
    public static final String ID_MESSAGE1 =
            "Hello and welcome to DJ WEPNY's Personal Health app!" +
                    "\nThe following number is your ID:";
    public static final String ID_MESSAGE2 =
            "\nPlease take note of this number as you will need it to access your account in the future.\n";

    public static final String UPDATED_PROFILE = "Your profile has been updated!";
    public static final String UPDATED_SUCCESSFULLY = "Changed Successfully!\nPlease press 'Return' to go back";
}
