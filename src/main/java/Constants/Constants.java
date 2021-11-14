package Constants;



/**
 * This class contains all the constants used in the program.
 */
public class Constants {

    // API
    public static final String DISEASE_DATASET_PATH = "src/main/java/GlobalDiseaseData.csv";
    public static final String FOOD_DATASET_PATH = "src/main/java/GlobalFoodData.csv";
    public static final String EXERCISE_DATASET_PATH = "src/main/java/ExerciseMovesData.csv";
    public static final double FAT_PER_DAY = 75.0;
    public static final double CARB_PER_DAY = 300.0;
    public static final double SUGAR_PER_DAY = 100.0;

    // Decorators
    public static final String DIVIDER = "\n*****************************************************************************\n";
    public static final String EMPTY_LINE = "\n\n";

    // BMIAnalyzer
    // BMI Constants
    public static final double OBESE_BMI = 30.0;
    public static final double OVERWEIGHT_BMI = 25.0;
    public static final double HEALTHY_BMI = 18.5;

    // Classifications
    public static final String OBESE = "Obese.";
    public static final String OVERWEIGHT = "Overweight.";
    public static final String HEALTHY = "Healthy Weight.";
    public static final String UNDERWEIGHT = "Underweight.";

    // Messages
    public static final String BMI_INTRO =
            "The Body Mass Index (BMI) is a number calculated given your weight and height. \n" +
                    "High BMI (>25.0) can indicate high body fatness, and considered as overweight. \n" +
                    "BMI can indicate health problems such as obesity and malnutrition.\nYour Body Mass Index is ";
    public static final String BMI_ANALYSIS = ", your BMI is considered: ";


    // MealPlanGenerator
    // Messages
    public static final String MEALPLAN_INTRO = ", the following foods are based on your preferences:";
    public static final String MEALPLAN_WELCOME = "Welcome to the Meal Plan Generator!\n" +
            "Please enter Y or N for the following options.";

    //Preferences
    public static final String LOWCARBS = "I prefer low carbohydrates";
    public static final String LOWFAT = "I prefer low fat";
    public static final String LOWSUGAR = "I prefer low sugar";
    public static final String VEG = "I am vegetarian";
    public static final String NUM_FOODS = "How many foods would you like?";


    // EER
    // Activity Level
    public static final String SED = "Sedentary";
    public static final String LOW = "Low Active";
    public static final String MID = "Active";
    public static final String HIGH = "Very Active";

    // Messages
    public static final String EER_DESCRIPTION =
            "The Estimated Energy Requirement (EER) is a predicted average dietary intake \n" +
                    "needed to maintain energy balance in the healthy adult of a defined age, gender, weight, \n" +
                    "and a level of physical activity that is consistent with good health." +
                    "\nYour Estimated Energy Requirement is: ";

    // Disease
    public static final String DISEASE_START =
            "Welcome to the Disease Analyzer. Given the lists of potential symptoms,\n" +
            "please enter the ones you are experiencing, and the program will generate potential\n" +
            "diseases that you may be at risk for.";
    public static final String SYMPTOMS_DESC = "\nHere are the options. " +
            "If you are currently experiencing more than one symptom, please separate the input using a comma ','\n" +
            "for example, 'high_fever,back_pain' with no spaces in between\n" +
            "\nIf none of them apply to you, please type in N/A.";

    // ExerciseAnalyzer
    // Messages
    public static final String EXERCISE_START =
            "This exercise analyzer generate a list of exercising moves based on your preference.\n";
    public static final String EXERCISE_MAJOR =
            "Please select only ONE major muscle you want to exercise from the following list:\n" +
            "\"Arms, Core, Full Body, Legs, Back\"";
    public static final String EXERCISE_MAJOR_ERROR = "The major muscle entered is not valid. \n" +
            "Please select only ONE major muscle you want to exercise from the following list:\n" +
                    "\"Arms, Core, Full Body, Legs, Back\"";
    public static final String EXERCISE_MINOR =
            "Please select only ONE minor muscle you want to exercise from the following list:\n" +
            "\"Bicep, Shoulders, Outer Thigh, Glutes, Hamstrings, Quads, \"" +
            "Calves, Chest, Inner Thigh, Tricep, Lats, Oblique\"";
    public static final String EXERCISE_MINOR_ERROR = "The minor muscle entered is not valid. \n" +
            "Please select only ONE minor muscle you want to exercise from the following list:\n" +
                    "\"Bicep, Shoulders, Outer Thigh, Glutes, Hamstrings, Quads, \"" +
                    "Calves, Chest, Inner Thigh, Tricep, Lats, Oblique\"";
    public static final String EXERCISE_EQUIPMENT = "Please select one equipment you have or want to use:\n" +
            "\"Dumbbells, Bar, Cable, Body Weight, Platform, Machine, Band, Kettle Bell, Medicine Ball, Bosu Ball\"";
    public static final String EXERCISE_EQUIPMENT2 = "Please select another equipment you have or want to use, " +
            "or enter 'None' if there isn't anymore:\n" +
            "\"Dumbbells, Bar, Cable, Body Weight, Platform, Machine, Band, Kettle Bell, Medicine Ball, Bosu Ball\"";
    public static final String EXERCISE_EQUIPMENT3 = "The equipment entered has already been added. " +
            "Please select another equipment you have or want to use, or enter 'None' if there isn't anymore:\n" +
            "\"Dumbbells, Bar, Cable, Body Weight, Platform, Machine, Band, Kettle Bell, Medicine Ball, Bosu Ball\"";
    public static final String EXERCISE_EQUIPMENT_ERROR_BG = "The equipment entered is invalid. " +
            "Please select one equipment you have or want to use:\n" +
            "\"Dumbbells, Bar, Cable, Body Weight, Platform, Machine, Band, Kettle Bell, Medicine Ball, Bosu Ball\"";
    public static final String EXERCISE_EQUIPMENT_ERROR_AF = "The equipment entered is invalid. " +
            "Please select one equipment you have or want to use, or enter 'None' if there isn't anymore:\n" +
            "\"Dumbbells, Bar, Cable, Body Weight, Platform, Machine, Band, Kettle Bell, Medicine Ball, Bosu Ball\"";
    public static final String EXERCISE_INTRO1 = "Exercises for ";
    public static final String EXERCISE_INTRO2 =
            ": \nThe following exercises are based on your preferences on the muscles exercised and equipment. \n";
    public static final String TAB = "\n-  ";
    public static final String EX_DESC_TYPE = "\n    -> Type of the exercise: ";
    public static final String EX_DESC_USES = "\n    -> Uses: ";
    public static final String EX_DESC_MAJOR = ";\n    -> The major muscle exercised is: ";
    public static final String EX_DESC_MINOR = "\n    -> The minor muscle exercised is: ";
    public static final String NO_EXERCISES_FOUND =
            "\nUnfortunately, we currently do not have any exercises that match these preferences. Please try again.";

    //Console
    public static final String BASIC_INFO = "We will start from some basic information.";
    public static final String PERSONAL_INFO = "Now, we would like to know some of your personal data.";
    public static final String WELCOME1 = "Welcome, ";
    public static final String WELCOME_EXISTING = "Welcome back, ";
    public static final String WELCOME2 = ", What would you like to do today? \n";

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

    // Prompts
    public static final String NAME_PROMPT = "Please enter your name:";
    public static final String GENDER_PROMPT = "Please enter your gender (M/F):";
    public static final String HEIGHT_PROMPT = "Please enter your height (in m):";
    public static final String WEIGHT_PROMPT = "Please enter your weight (in kg):";
    public static final String AGE_PROMPT = "Please enter your age:";
    public static final String ASK_EXISTING = "Are you an existing user? (Y/N)";
    public static final String ID_PROMPT = "Please enter your personal ID";
    public static final String RETURN_MENU = "Do you want to go back to your user main menu page? (Y/N)";
    public static final String RESTART_PROGRAM = "Would you like to exit the program entirely (Y/N):";

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
  
    // PAL Constants
    public static final double M_SEDENTARY = 1.0;
    public static final double M_LOW = 1.11;
    public static final double M_ACTIVE = 1.25;
    public static final double M_HIGH = 1.48;
    public static final double F_SEDENTARY = 1.0;
    public static final double F_LOW = 1.12;
    public static final double F_ACTIVE = 1.27;
    public static final double F_HIGH = 1.45;

    // Exercise
    public static final String ALL_MAJOR_MUSCLES = "Arms, Core, Full Body, Legs, Back";
    public static final String ALL_MINOR_MUSCLES = "Bicep, Shoulders, Outer Thigh, Glutes, Hamstrings, Quads, " +
            "Calves, Chest, Inner Thigh, Tricep, Lats, Oblique";
    public static final String ALL_EQUIPMENTS = "Dumbbells, Bar, Cable, Body Weight, Platform, Machine, " +
            "Band, Kettle Bell, Medicine Ball, Bosu Ball";

    // Errors
    public static final String INVALID_INPUT = "Sorry, your input is invalid. Please try again. \n";
    public static final String FILE_LOC_ERROR =
            "Uh oh, an error has occurred! Please check that the files exist and are in the specified position.";
    public static final String NOT_ENOUGH_INFO = "There is currently not enough information " +
            "in your profile to generate this report.\n" + "Please fill in the following information:\n";

}
