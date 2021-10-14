package main.java;
/*
 * This class executes command appropriately based on command given.
 */
public class RunCommand {

    private User tempUser;

    /**
     * Constructor for the RunCommand class based on the command that the user Choose in the Console.
     */
    public RunCommand(){}


    /**
     * Executes the provided command line accordingly
     * @param command is the given command
     * @return string that is given back as command is executed
     * @throws Exception if the provided command is invalid
     */
    public String executeCommand(int command, String[] basic, String[] personal) throws Exception {

        User newUser = CreateUser.newUser(basic, personal);

        switch(command) {
            case 1:
                BMIAnalyzer bmi = new BMIAnalyzer();

               return (String) bmi.analyze(newUser);

            default:
                throw new Exception("Sorry, the command you have entered is invalid. Please re-enter.");
        }
    }
    // I'm commenting this method out for now or else it gives an error in Console.main().

}
