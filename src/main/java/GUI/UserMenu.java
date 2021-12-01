package GUI;

import controllers.RunCommand;

import javax.swing.*;

public class UserMenu extends JFrame {

    private JPanel existingUserMenu;
    private JButton a6EditProfileButton;
    private JButton a5GenerateAMealButton;
    private JButton a1AnalyzeBodyMassButton;
    private JButton a2AnalyzeEnergyRequiredButton;
    private JButton a4AnalyzeDiseaseButton;
    private JButton a3AnalyzeWorkoutButton;
    private JTextPane welcomeMessage;


    public UserMenu(int num){
        // initializing the ExistingUserMenu frame
        super("DJ WEPNY Personal Health Aid");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(existingUserMenu);
        this.setResizable(false);
        this.welcomeMessage.setEditable(false);

        RunCommand infoGetter = new RunCommand();
        if (num == 1) {
            String output = controllers.Presenter.printUserIDMessage((String) infoGetter.retrieveUser("id"));
            welcomeMessage.setText(output);
            this.a6EditProfileButton.setVisible(false);
            this.pack();
        }
        this.pack();

        // If the user clicks on the BMI, close the current page
        a1AnalyzeBodyMassButton.addActionListener(e -> {
            this.dispose();
            //TODO implement the BMIAnalyzerGUI and display the page with the user's BMI Report
            BMIAnazlyerGUI bmi = new BMIAnazlyerGUI();
            bmi.setVisible(true);
        });

        a2AnalyzeEnergyRequiredButton.addActionListener(e -> {
            this.dispose();
            if (ConsoleForGUI.HelperConsole.noInfoFound(2)) {
                //TODO if there is no information found on the user's Activity level,
                // new activityLevel Page pops up to gather user's Active status

                EERPromptGUI activityLevel = new EERPromptGUI();
                activityLevel.setVisible(true);
            }
            else{
                //TODO if there is already existing information on the user's activity level,
                // display the page with the user's EER Report
                EERPromptGUI activityLevel = new EERPromptGUI("existing");
                activityLevel.setVisible(true);
            }
        });

        a3AnalyzeWorkoutButton.addActionListener(e -> {
            this.dispose();
            if (ConsoleForGUI.HelperConsole.noInfoFound(3)) {
                //TODO if there is no information found on the user's exercise preference,
                // new exercisePreference Page pops up to gather user's exercise preference

                ActivityLevelGUI activityLevel = new ActivityLevelGUI();
                activityLevel.setVisible(true);
            }
            else{
                //TODO if there is already existing information on the user's activity level,
                // display the page with the user's EER Report
                EERPromptGUI activityLevel = new EERPromptGUI();
                activityLevel.setVisible(true);
            }
        });
    }
    // TODO: do the rest for the rest three buttons, similar to the cases before
}
