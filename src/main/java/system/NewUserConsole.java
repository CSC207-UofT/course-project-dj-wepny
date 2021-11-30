package System;

import Controllers.Presenter;
import Controllers.RunCommand;

import java.util.*;

/**
 * This class interact with the users and receives their input, then it sends
 * the inputs to the controller class.
 */
public class NewUserConsole {
    public static String NewUserMenu(Scanner reader) throws Exception {
        RunCommand infoGetter = new RunCommand();
        String username = (String) infoGetter.retrieveUser("name");
//        System.out.println(Constants.WELCOME1 + infoGetter.retrieveUser("name") + Constants.WELCOME2 +
//                Constants.MAIN_MENU);
        Presenter.printWelcome(username);

        String input = reader.nextLine();

        // checking if the command is an integer between 1 and 5
        input = HelperConsole.checkCommand(input, reader, 1);

        int command = Integer.parseInt(input);

        RunCommand commandExecutor = new RunCommand(command);
        switch (command) {
            case 2:
                String level = HelperUserInfo.activityLevel(reader);
                commandExecutor.addInfo(level, command);
                break;
            case 3:
                commandExecutor.addInfo(HelperUserInfo.exercisePreference(reader), command);
                break;
            case 4:
                return HelperUserInfo.diseaseList(reader, commandExecutor);
            case 5:
                commandExecutor.addInfo(HelperUserInfo.foodPreference(reader), command);
        }

        commandExecutor.executeCommand();

        Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
        return analyze_results.retrieveOutput();
    }

    public static void gatherInfo(Scanner reader){
//        System.out.println(Constants.BASIC_INFO);
        Presenter.printUserInfo("basic");
        String[] basicUserInfo = HelperConsole.getBasicUserInfo(reader);
//        System.out.println(Constants.PERSONAL_INFO);
        Presenter.printUserInfo("personal");
        String[] personalUserInfo = HelperConsole.getPersonalUserInfo(reader);

        RunCommand.createUser(basicUserInfo, personalUserInfo);
        RunCommand infoGetter = new RunCommand();
        String ID = (String) infoGetter.retrieveUser("id");
//        System.out.println( Constants.ID_MESSAGE1 + infoGetter.retrieveUser("id") + Constants.ID_MESSAGE2);
        Presenter.printUserIDMessage(ID);
    }

}
