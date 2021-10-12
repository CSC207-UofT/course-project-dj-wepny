/*
This file contains UserAnalyzer class, which is the parent class for all analyzers
 */
package main.java;

public class UserAnalyzer implements UserInformation{

    /*
    Below is the method that all analyzers share, the analyze method.
    This method takes in any input of User, and stores it to the variable user.
    It then returns the type of output that will be specified in the other analyzers.
    The exact contents will vary then.
     */

    public Object analyze(User user){
        return "";
    }

    //TODO: More methods may be required in the future?
}
