package System;

import Controllers.Presenter;
import Controllers.RunCommand;
import Constants.Constants;

import java.util.*;

/**
 * This class interact with the users and receives their input, then it sends
 * the inputs to the controller class.
 */
public class NewUserConsole {
    public static String NewUserMenu(Scanner reader) throws Exception {
        RunCommand infoGetter = new RunCommand();
        System.out.println(Constants.WELCOME1 + infoGetter.retrieveUser("name") + Constants.WELCOME2 +
                Constants.MAIN_MENU);

        String input = reader.nextLine();

        // checking if the command is an integer between 1 and 5
        input = HelperConsole.checkCommand(input, reader, 1);

        int command = Integer.parseInt(input);

        RunCommand commandExecutor = new RunCommand(command);
        switch (command) {
            case 2:
                String level = HelperConsole.activityLevel(reader);
                commandExecutor.addInfo(level, command);
                break;
            case 3:
                commandExecutor.addInfo(HelperConsole.exercisePreference(reader), command);
                break;
            case 4:
                return HelperConsole.diseaseList(reader, commandExecutor);
            case 5:
                commandExecutor.addInfo(HelperConsole.foodPreference(reader), command);
        }

        commandExecutor.executeCommand();

        Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
        return analyze_results.retrieveOutput();
    }

    public static void gatherInfo(Scanner reader) throws Exception {
        System.out.println(Constants.BASIC_INFO);
        String[] basicUserInfo = HelperConsole.getBasicUserInfo(reader);
        System.out.println(Constants.PERSONAL_INFO);
        String[] personalUserInfo = HelperConsole.getPersonalUserInfo(reader);

        RunCommand.createUser(basicUserInfo, personalUserInfo);
        RunCommand infoGetter = new RunCommand();
        System.out.println( Constants.ID_MESSAGE1 + infoGetter.retrieveUser("id") + Constants.ID_MESSAGE2);
    }

}
