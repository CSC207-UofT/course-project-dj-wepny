package controllers;

import usecases.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Controller class that executes command that are passed from the Console by calling the corresponding use cases
 * based on command given.
 */
public class RunCommand {

    public UserAnalyzer userAnalyzer;

    /**
     * Initiating a RunCommand class with the corresponding userAnalyzer
     * using the factory design pattern, based on the command that the user
     * inputted in the Console.
     */
    public RunCommand(int command) {
        UserAnalyzerFactory factory = new UserAnalyzerFactory();
        this.userAnalyzer = factory.createAnalyzer(command);
    }

    /**
     * an overloading constructor that does not require
     * the RunCommand to be initialized with a userAnalyzer
     */
    public RunCommand() {
    }

    /**
     * Creating a new user based on the basic information entered into the console
     *
     * @param basic    an array of string that contains the user's name and gender
     * @param personal an array of string that contains the user's weight, height, and age
     */
    public static void createUser(String[] basic, String[] personal) {
        UserManager.createNewUser(basic, personal);
    }

    /**
     * A function that executes the disease analyzer. It takes in currentUser's responses and add the
     * symptoms in responses into user's riskFactor. Then it uses the user's riskFactor to calculate
     * the user's potential list of disease
     *
     * @param responses a list of string containing user's possible symptoms
     * @return the number of diseases the client could potentially have.
     */
    public int executeCommandDisease(List<String> responses) throws Exception {
        for (String response : responses) {
            addInfo(response, 4); //adds the symptoms identified by client into their risk factors
        }
        userAnalyzer.analyze(); //analyzes what they have
        return DiseaseAnalyzer.getPotentialDisease().size(); // the amount of diseases the client could potentially have.
    }

    /**
     * a function that resets the user's list of symptoms to empty
     */
    public void resetPotentialDisease() {
        DiseaseAnalyzer.resetPotentialDisease();
    }

    /**
     * A function that passes information of the currentUser from the UserManager to the Console
     *
     * @param typeInfo The type of information the Console needs
     * @return a String of a certain information of the currentUser
     */
    public Object retrieveUser(String typeInfo) {
        switch (typeInfo) {
            case "name":
                return UserManager.getCurrentUser().getUsername();
            case "weight":
                return UserManager.getCurrentUser().getPersonalData().get("weight");
            case "height":
                return UserManager.getCurrentUser().getPersonalData().get("height");
            case "gender":
                return UserManager.getCurrentUser().getGender();
            case "age":
                return UserManager.getCurrentUser().getPersonalData().get("age");
            case "personal data":
                return UserManager.getCurrentUser().getPersonalData();
            case "id":
                return String.valueOf(UserManager.getCurrentUser().getId());
            case "risk":
                return UserManager.getCurrentUser().getRiskFactor();
            case "exercise":
                return UserManager.getCurrentUser().getExercisePreference();
            case "food":
                return UserManager.getCurrentUser().getFoodPreference();
            default:
                return "";
        }
    }

    /**
     * returns the result calculated by the use cases the currentUser want
     */
    public void executeCommand() throws Exception {
        userAnalyzer.analyze();
    }

    /**
     * add the info the currentUser inputted into the console if they previously don't
     * have this information stored in their profile
     *
     * @param info    a String that contains the information that the user wants to update to
     * @param command a number that specifies what type of information it is
     */
    public void addInfo(Object info, int command) {
        UserManager.addNewInfo(info, command);
    }


    /**
     * Update the user's information
     */

    public void executeCommandUpdateInfo(int command2, String newItem) {
        UserManager.changeUserInfo(newItem, command2);
    }

    /**
     * Setting the user using the program as the currentUser in UserManager
     */
    public void setCurrentUser(int id) {
        UserManager.setCurrentUser(id);
    }

    /**
     * Setting the new user that just created their account into an existing user
     * after they logged out
     */
    public void setToExisting() {
        UserManager.addUser(false, UserManager.getCurrentUser());
    }

    /**
     * Check if the user is an existing user.
     *
     * @return true it an existing user and false otherwise.
     */
    public boolean checkExistingUsers() {
        RunCommand command = new RunCommand();
        int id = Integer.parseInt((String) command.retrieveUser("id"));
        return UserManager.getExistingUsers().containsKey(id);
    }

    // getter functions
    public UserAnalyzer getAnalyzer() {
        return this.userAnalyzer;
    }
}
