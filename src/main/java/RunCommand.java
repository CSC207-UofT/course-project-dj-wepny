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
    public void executeCommand(String[] basic, String[] personal) throws Exception {

        User newUser = UserManager.createNewUser(basic, personal);
        UserManager.addUser(true, newUser);


        useranalyzer.analyze(newUser);

        }

    public void executeCommand(int command, User user) throws Exception {

        useranalyzer.analyze(user);

    }

    public void executeCommandUpdateInfo(int command2, User user, String newItem)
            throws Exception {


        switch(command2) {
            // change username
            case 1:
                UserParser.updateUserName(user.getId(),newItem);
            case 2:
                //change food preferences
            case 6:

        }
    }

}
