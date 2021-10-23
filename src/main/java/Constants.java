import java.util.HashMap;

/**
 * This class holds the constants used in the program.
 */
public class Constants {
    // BMI constants
    public static final double OBESE_BMI = 30;
    public static final double OVERWEIGHT_BMI = 25;
    public static final double HEALTHY_BMI = 18.5;

    // Command constants
    public static final HashMap<Integer, UserAnalyzer> COMMANDS = new HashMap<>();
    static {
        COMMANDS.put(1, new BMIAnalyzer());
        // Add other functionalities here
    }
    // I will put this in the RunCommand class for now since I can't import for some reason??



    // TODO: Should we make messages constants as well instead of hard coding them? e.g. Exceptions.

}
