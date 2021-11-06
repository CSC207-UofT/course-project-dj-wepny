import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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
        if (command == 4){
            this.useranalyzer = new DiseaseAnalyzer();
        }
    }
    public RunCommand(){}
    /**
     * Input boundary UserAnalyzer for the use cases.
     */
//    private final UserAnalyzer useranalyzer;

    /**
     * Executes the provided command line accordingly
//     * @param command is the given command
     * @return string that is given back as command is executed
     * @throws Exception if the provided command is invalid
     */
//    public String executeCommand(int command, String[] basic, String[] personal) throws Exception {
//
//        User newUser = UserManager.createNewUser(basic, personal);
//        UserManager.addUser(true, newUser);
//
//        switch(command) {
//            case 1:
//                BMIAnalyzer bmi = new BMIAnalyzer();
//
//                return (String) bmi.analyze(newUser);
//
//            default:
//                throw new Exception("Sorry, the command you have entered is invalid. Please re-enter.");
//        }
    public UserAnalyzer getAnalyzer(){
        return this.useranalyzer;
    }

    public static void createUser(String[] basic, String[] personal){
        UserManager.createNewUser(basic, personal);
    }

    public void executeCommand() throws Exception {
        useranalyzer.analyze();

    }
    public void addInfo(Object info, int command){
        UserManager.addNewInfo(info, command);
    }

    public void changeInfo(Object info, int command){
        UserManager.changeInfo(info, command);
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

    public void executeCommand(int command, User user) throws Exception {
        useranalyzer.analyze();

    }

    public String retrieveUser(String typeInfo){
        String output;
        if (typeInfo.equals("name")){
            output = UserManager.getCurrentUser().getUsername();
        }
        else {
            output = String.valueOf(UserManager.getCurrentUser().getId());
        }
        return output;
    }

    public void executeCommandUpdateInfo(int command2, User user, String newItem)
            throws Exception {


        switch(command2) {
            // change username
            case 1:
                UserManager.changeUserName(user, newItem);

            case 6:

        }
    }

}
