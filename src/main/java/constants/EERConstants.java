package constants;

/**
 * This class contains constants relative to the EERAnalyzer function.
 */
public class EERConstants {
    // Menu
    public static final String ACTIVITY_MENU =
            "Please enter your daily activity level: (Please enter a number from 1 to 4)";

    // Activity Level
    public static final String SED = "Sedentary";
    public static final String LOW = "Low Active";
    public static final String MID = "Active";
    public static final String HIGH = "Very Active";

    // PAL Constants
    public static final double M_SEDENTARY = 1.0;
    public static final double M_LOW = 1.11;
    public static final double M_ACTIVE = 1.25;
    public static final double M_HIGH = 1.48;
    public static final double F_SEDENTARY = 1.0;
    public static final double F_LOW = 1.12;
    public static final double F_ACTIVE = 1.27;
    public static final double F_HIGH = 1.45;

    // Messages
    public static final String EER_DESCRIPTION =
            "The Estimated Energy Requirement (EER) is a predicted average dietary intake \n" +
                    "needed to maintain energy balance in the healthy adult of a defined age, gender, weight, \n" +
                    "and a level of physical activity that is consistent with good health." +
                    "\nYour Estimated Energy Requirement is: ";
}
