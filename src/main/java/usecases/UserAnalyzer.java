package usecases;

/**
 * This is an interface of a user analyzer. Analyzers with different functionalities must override
 * the method in here.
 */
public interface UserAnalyzer {
   /*
    Below is the method that all analyzers share, the analyze method.
    This method takes in any input of User, and stores it to the variable user.
    It then returns the type of output that will be specified in the other analyzers.
    The exact contents will vary then.
    */

    /**
     * The method analyze the user.
     *
     * @throws Exception if the total number of food items in foodMap >= numFoods in the MealPlanGenerator.
     */
    void analyze() throws Exception;

    /**
     * A getter method for the result.
     */
    String getResult();

}
