/**
 *This file contains UserAnalyzer class, which is the parent class for all analyzers
 */
public interface UserAnalyzer {
   /*
    Below is the method that all analyzers share, the analyze method.
    This method takes in any input of User, and stores it to the variable user.
    It then returns the type of output that will be specified in the other analyzers.
    The exact contents will vary then.
     */
    void analyze(User user);
    String getResult();

    //TODO: More methods may be required in the future?
}
