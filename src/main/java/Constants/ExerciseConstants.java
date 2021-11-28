package Constants;

/**
 * This class contains all the constants related to ExerciseAnalyzer function.
 */
public class ExerciseConstants extends Constants{

    // ExerciseAnalyzer messages
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

    //Exercises
    // Exercise
    public static final String ALL_MAJOR_MUSCLES = "Arms, Core, Full Body, Legs, Back";
    public static final String ALL_MINOR_MUSCLES = "Bicep, Shoulders, Outer Thigh, Glutes, Hamstrings, Quads, " +
            "Calves, Chest, Inner Thigh, Tricep, Lats, Oblique";
    public static final String ALL_EQUIPMENTS = "Dumbbells, Bar, Cable, Body Weight, Platform, Machine, " +
            "Band, Kettle Bell, Medicine Ball, Bosu Ball";
}
