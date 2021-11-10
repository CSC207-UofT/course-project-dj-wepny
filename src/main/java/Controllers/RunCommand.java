package Controllers;
import Entities.User;
import UseCases.BMIAnalyzer;
import UseCases.DiseaseAnalyzer;
import UseCases.EERAnalyzer;
import UseCases.ExerciseAnalyzer;
import UseCases.UserAnalyzer;
import UseCases.UserManager;
import Constants.Constants;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import UseCases.MealPlanGenerator;

/**
 * This class executes command appropriately based on command given.
 */
public class RunCommand {
    /**
     * Constructor for the RunCommand class based on the command that the user Choose in the Console.
     */
    public UserAnalyzer useranalyzer;

    public RunCommand(int command) {
        if (command == 1) {
            this.useranalyzer = new BMIAnalyzer();
        }
        else if (command == 2){
            this.useranalyzer = new EERAnalyzer();
        }
        else if (command == 3){
            this.useranalyzer = new ExerciseAnalyzer();
        }
        else if (command == 4){
            this.useranalyzer = new DiseaseAnalyzer();
        }
        else if (command == 5){
            this.useranalyzer = new MealPlanGenerator();
        }       // See MealPlanGenerator
    }
    public RunCommand(){}

    public UserAnalyzer getAnalyzer(){
        return this.useranalyzer;
    }

    public static void createUser(String[] basic, String[] personal){
        UserManager.createNewUser(basic, personal);
    }

    public void executeCommand() throws Exception {useranalyzer.analyze();}

    public void addInfo(Object info, int command){
        UserManager.addNewInfo(info, command);
    }

    public int executeCommandDisease(ArrayList<String> responses) throws Exception{
        for (String response: responses){
            addInfo(response, 4); //adds the symptoms identified by client into their risk factors
        }
        useranalyzer.analyze(); //analyzes what they have
        return DiseaseAnalyzer.getPotentialDisease().size(); // the amount of diseases the client could potentially have.
    }

    public void resetPotentialDisease(){
        DiseaseAnalyzer.resetPotentialDisease();
    }

    public Object retrieveUser(String typeInfo){
        switch (typeInfo) {
            case "name": return UserManager.getCurrentUser().getUsername();
            case "personal data" : return UserManager.getCurrentUser().getPersonalData();
            case "id" : return String.valueOf(UserManager.getCurrentUser().getId());
            case "risk" : return UserManager.getCurrentUser().getRiskFactor();
            case "exercise": return UserManager.getCurrentUser().getExercisePreference();
            case "food": return UserManager.getCurrentUser().getFoodPreference();
            default : return "";
        }
    }

    public void executeCommandUpdateInfo(int command2, String newItem) throws Exception {
        UserManager.changeUserInfo(newItem, command2);

    }

    public HashMap<Integer, User> getAllExistingUser(){
        return UserManager.getExistingUsers();
    }

    public void setCurrentUser(int id){
        UserManager.setCurrentUser(id);
    }

    public void setToExisting(){
        UserManager.addUser(false, UserManager.getCurrentUser());
    }

}
