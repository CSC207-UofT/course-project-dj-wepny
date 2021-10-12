package main.java;

/**
 * This class executes command appropriately based on command given.
 */
public class RunCommand {




    /**
     * Executes the provided command line accordingly
     * @param command is the given command
     * @return string that is given back as command is executed
     * @throws Exception if the provided command is invalid
     */
    public String executeCommand(String command) throws Exception {

        switch(command) {
            case "Use Case 1":
                // TODO: call use case function here and return string
                return "";
                break;
            case "Use Case 2":
                // TODO: call use case function here and return string
                break;
                return "";
            default:
                // TODO: Decide where to catch exception, and also what is the exception text
                throw new Exception("Some error text to indicate command is invalid");
        }
    }
}
