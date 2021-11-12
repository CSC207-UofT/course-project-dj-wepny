package UseCases;

/**
 * A class that uses the Factory Design Pattern to create the appropriate subclass of UserAnalyzer
 * depending on the given command
 **/

public class UserAnalyzerFactory {

    /**
     * Create the analyzer based on the command given.
     *
     * @param command an integer that correspond to the analyzer that need to be created.
     * @return a UserAnalyzer.
     */
    public UserAnalyzer create_analyzer(int command) {
        UserAnalyzer analyzer;

        if (command == 1) {
            analyzer = new BMIAnalyzer();
        } else if (command == 2) {
            analyzer = new EERAnalyzer();
        } else if (command == 3) {
            analyzer = new ExerciseAnalyzer();
        } else if (command == 4) {
            analyzer = new DiseaseAnalyzer();
        } else if (command == 5) {
            analyzer = new MealPlanGenerator();
        } else {
            analyzer = null;
        }
        return analyzer;
    }
}
