package consoleforgui;

import constants.SystemConstants;
import controllers.Presenter;
import controllers.RunCommand;
import controllers.UserController;

import java.util.Scanner;

/**
 * This class interact with existing users, prompts the login page
 * and existing user menu, and receives user input. Also, responsible for sending user's
 * command to the controller class.
 */
public class ExistingUserConsole {
// TODO: Refactor this class? Only validID is used.

    /**
     * Checks that the ID input belongs to a valid user.
     * @param input of the user
     * @return whether the ID is valid or not.
     */
    public static boolean validID(String input){
        return !HelperConsole.isNotNum(input) && UserController.checkUserExist(input);
    }

    /**
     * A Static function that outputs the user the existing user menu
     * once they logged in using their id. Responsible for reading in the user's
     * command and call the commandExecutor to read the command
     *
     * @param reader a Scanner Object that can read user's keyboard input
     * @param id     the current user's id
     * @return the output of the functionality the use chose to run
     */
    public static String ExistingUserMenu(Scanner reader, int id) throws Exception {

        RunCommand currentUser = new RunCommand();
        // setting the user as the currentUser
        currentUser.setCurrentUser(id);

        String name = (String) currentUser.retrieveUser("name");
        Presenter.printExistingUserWelcome(name);

        String input = reader.nextLine();

        //check whether the command is an integer from 1 to 6
        input = HelperConsole.checkCommand(input, reader, 2);

        int command = Integer.parseInt(input);
        // initiating a RunCommand based on the user's input
        RunCommand commandExecutor = new RunCommand(command);

        // calling different analyzers using the runCommand based on the user's input
        switch (command) {
            case 2:
                // if the information needed to access EER report is missing
                if (HelperConsole.noInfoFound(command)) {
                    // allows user to add the information
                    Presenter.printNotEnoughInfoPrompt();
                    String level = HelperUserInfo.activityLevel(reader);
                    commandExecutor.addInfo(level, command);
                }
                break;
            case 3:
                // if the information needed to access exercise report is missing
                if (HelperConsole.noInfoFound(command)) {
                    // allows user to add the information
                    Presenter.printNotEnoughInfoPrompt();
                    String[] exercises = HelperUserInfo.exercisePreference(reader);
                    commandExecutor.addInfo(exercises, command);
                }
                break;
            case 4:
                // if the information needed to access disease report is missing
                if (HelperConsole.noInfoFound(command)) {
                    Presenter.printNotEnoughInfoPrompt();
                    // allows user to add the information
                    return HelperUserInfo.diseaseList(reader, commandExecutor);
                }
                break;
            case 5:
                // if the information needed to access meal plan report is missing
                if (HelperConsole.noInfoFound(command)) {
                    Presenter.printNotEnoughInfoPrompt();
                    // allows user to add the information
                    commandExecutor.addInfo(HelperUserInfo.foodPreference(reader), command);
                }
                break;
            case 6:
                // command 6 allows user to update their profile information, calls the helper
                // function updateUser
                return HelperUserInfo.updateUser(reader, commandExecutor);
        }
        // outputting the result from the analyzers using the presenter
        commandExecutor.executeCommand();
        Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
        return SystemConstants.REPORT + analyze_results.retrieveOutput();
    }

}
