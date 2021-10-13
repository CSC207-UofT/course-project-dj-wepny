package main.java;
/*
 * This class executes command appropriately based on command given.
 */
public class RunCommand {



    /**
     * Constructor for the RunCommand class based on the command that the user Choose in the Console.
     */
    public RunCommand(){}


//    /**
//     * Executes the provided command line accordingly
//     * @param command is the given command
//     * @return string that is given back as command is executed
//     * @throws Exception if the provided command is invalid
//     */
//    public String executeCommand(int command) throws Exception {
//
//        switch(command) {
//            case "Use Case 1":
//                // TODO: call use case function here and return string
//                break;
//                return "";
//            case "Use Case 2":
//                // TODO: call use case function here and return string
//                break;
//                return "";
//            default:
//                // TODO: Decide where to catch exception, and also what is the exception text
//                throw new Exception("Some error text to indicate command is invalid");
//        }
//        // What do we return here?
//    }
    // I'm commenting this method out for now or else it gives an error in Console.main().

    public void newUser(String[] basicUserInfo, String[] personalUserInfo){
        User newUser = CreateUser.newUser(basicUserInfo, personalUserInfo);
    }
}
