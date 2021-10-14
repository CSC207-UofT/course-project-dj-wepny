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

    /**
     * Generates a new User object based on the information entered by the user in Console.
     * @param basicUserInfo An array of strings in the format of {name, age, gender}.
     * @param personalUserInfo An array of strings in the format of {height, weight}.
     */
    public void generateUser(String[] basicUserInfo, String[] personalUserInfo){
        this.tempUser = CreateUser.newUser(basicUserInfo, personalUserInfo);
    }

    /**
     * Execute the commands accordingly based on the user's output.
     * @param command An integer representing what the user wants to do.
     */
    public void executeCommand(int command){
        if (command == 1){
            BMIAnalyzer analyzer = new BMIAnalyzer();
            float bmi = (Float) analyzer.analyze(tempUser);
//            Presenter presenter = new Presenter();
//            What do I do with the presenter exactly?
            System.out.println("Your BMI is: " + bmi);
        }

    }
}
