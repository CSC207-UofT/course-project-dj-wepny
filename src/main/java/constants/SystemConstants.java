package constants;

/**
 * This class contains all the constants related to the system.
 */
public class SystemConstants {

    // UserParser
    public static final String USER_FILE = "src/main/data/UserInfo.csv";
    public static final String TEST_USER_FILE = "src/main/data/TestParser.csv";

    // API
    public static final String DISEASE_DATASET_PATH = "src/main/data/GlobalDiseaseData.csv";
    public static final String FOOD_DATASET_PATH = "src/main/data/GlobalFoodData.csv";
    public static final String EXERCISE_DATASET_PATH = "src/main/data/ExerciseMovesData.csv";
    public static final double FAT_PER_DAY = 75.0;
    public static final double CARB_PER_DAY = 300.0;
    public static final double SUGAR_PER_DAY = 100.0;
    public static final double PROTEIN_PER_DAY = 50.0;
    // This varialbe is never used so maybe we can remove it?

    // Decorators
    public static final String DIVIDER = "\n*****************************************************************************\n";
    public static final String EMPTY_LINE = "\n\n";

    //Console
    public static final String BASIC_INFO = "We will start from some basic information.";
    public static final String PERSONAL_INFO = "Now, we would like to know some of your personal data.";
    public static final String WELCOME1 = "Welcome, ";
    public static final String WELCOME_EXISTING = "Welcome back, ";
    public static final String WELCOME2 = ", What would you like to do today? \n";

    //Prompts
    public static final String NAME_PROMPT = "Please enter your name:";
    public static final String GENDER_PROMPT = "Please enter your gender (M/F):";
    public static final String HEIGHT_PROMPT = "Please enter your height (in m):";
    public static final String WEIGHT_PROMPT = "Please enter your weight (in kg):";
    public static final String AGE_PROMPT = "Please enter your age:";
    public static final String ASK_EXISTING = "Are you an existing user? (Y/N)";
    public static final String ID_PROMPT = "Please enter your personal ID";
    public static final String RETURN_MENU = "Do you want to go back to your user main menu page? (Y/N)";
    public static final String RESTART_PROGRAM = "Would you like to exit the program entirely (Y/N):";

    //Menus
    public static final String MAIN_MENU =
            " You may choose the following options: (Please enter a number from 1 to 5) \n " +
                    "1. Analyze Body Mass Index (BMI) \n " +
                    "2. Analyze Energy Required per day (EER) \n " +
                    "3. Analyze Workout \n " +
                    "4. Analyze Disease \n " +
                    "5. Generate a meal plan \n";
    public static final String ACTIVITY_MENU =
            "Please enter your daily activity level: (Please enter a number from 1 to 4) \n " +
                    "1. Sedentary \n " +
                    "2. Low Active \n " +
                    "3. Active \n " +
                    "4. Very Active \n";
    public static final String EXISTING_USER_MENU =
            " You may choose the following options: (Please enter a number from 1 to 6) \n " +
                    "1. Analyze Body Mass Index (BMI) \n " +
                    "2. Analyze Energy Required per day (EER) \n " +
                    "3. Analyze Workout \n " +
                    "4. Analyze Disease \n " +
                    "5. Generate a meal plan \n " +
                    "6. Edit Profile";
    public static final String USER_UPDATE =
            " You may choose the following options: (Please enter a number from 1 to 9) \n" +
            " 1. Change Username \n" +
            " 2. Change Height \n" +
            " 3. Change Weight \n" +
            " 4. Change Age \n" +
            " 5. Change Gender \n" +
            " 6. Change Activity Level \n" +
            " 7. Change Exercise Preferences \n" +
            " 8. Change Symptoms \n" +
            " 9. Change Food Preferences \n";

    // Update profile
    public static final String CHANGE_USERNAME = "Please enter your new username.";
    public static final String CHANGE_HEIGHT = "Please enter your new height.";
    public static final String CHANGE_WEIGHT = "Please enter your new weight.";
    public static final String CHANGE_GENDER = "Please enter your new gender.";
    public static final String CHANGE_AGE = "Please enter your new age.";

    // Messages
    public static final String ID_MESSAGE1 =
            "Hello and welcome to DJ WEPNY's Personal Health app!\nThe following number is your ID:";
    public static final String ID_MESSAGE2 =
            "\nPlease take note of this number as you will need it to access your account in the future.\n";
    public static final String REPORT = "Here is your report based on previously entered information: \n\n";

    public static final String UPDATED_PROFILE = "Your profile has been updated.";
    public static final String UPDATED_USERNAME = "Thank you. Currently updating your new username.";
    public static final String UPDATED_HEIGHT = "Thank you. Currently updating your new height.";
    public static final String UPDATED_WEIGHT = "Thank you. Currently updating your new weight.";
    public static final String UPDATED_GENDER = "Thank you. Currently updating your new gender.";
    public static final String UPDATED_AGE = "Thank you. Currently updating your new age.";

}
