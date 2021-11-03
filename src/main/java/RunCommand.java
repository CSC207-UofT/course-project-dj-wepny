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
    public RunCommand(UserAnalyzer useranalyzer){
        this.useranalyzer = useranalyzer;
    }
    public RunCommand(){
        this.useranalyzer = null;
    }

    /**
     * Input boundary UserAnalyzer for the use cases.
     */
    private final UserAnalyzer useranalyzer;

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
    public static User createUser(String[] basic, String[] personal){
        User newUser = UserManager.createNewUser(basic, personal);
        UserManager.addUser(true, newUser);
        return newUser;
    }

    public void executeCommand(User newUser) throws Exception {
        useranalyzer.analyze(newUser);

    }
    public void addInfo(User user, Object info, int command){
        UserManager.addNewInfo(user, info, command);
    }

    public int executeCommandDisease(User user, ArrayList<String> responses) throws Exception{
        for (String response: responses){
            addInfo(user, response, 3); //adds the symptoms identified by client into their risk factors
        }
        useranalyzer.analyze(user); //analyzes what they have
        return DiseaseAnalyzer.getPotentialDisease().size(); // the amount of diseases the client could potentially have.
    }


    public void executeCommand(int command, User user) throws Exception {

        useranalyzer.analyze(user);

    }

    public void executeCommandUpdateInfo(int command2, User user, String newItem)
            throws Exception {


        switch(command2) {
            // change username
            case 1:
//                UserParser.updateUserName(user.getId(),newItem);
                UserManager.changeUserName(user, newItem);

            case 2:
                //change food preferences
            case 6:

        }
    }

}
