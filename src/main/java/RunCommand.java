package main.java;

/**
 * This class executes command appropriately based on command given.
 */
public class RunCommand {

    private int commandNumber;

    /**
     * Constructor for the RunCommand class based on the command that the user Choose in the Console.
     * @param commandNumber An integer that represents the functionality the user chose in the console.
     */
    public RunCommand(int commandNumber){
        this.commandNumber = commandNumber;
    }

    /**
     * Setter for commandNumber based on the number that user put in the console.
     * @param commandNumber The integer that the user passed in.
     */
    public void setCommandNumber(int commandNumber) {
        this.commandNumber = commandNumber;
    }

//    /**
//     * Executes the provided command line accordingly
//     * @param command is the given command
//     * @return string that is given back as command is executed
//     * @throws Exception if the provided command is invalid
//     */
//    public String executeCommand(String command) throws Exception {
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

    public void createUser(String[] basicUserInfo, String[] personalUserInfo){
        // Assuming that we can create a User Object directly in this class, I will finish this method.
        // Or else, The basicUserInfo and personalUserInfo will be passed into one of the use cases.
    }
}
