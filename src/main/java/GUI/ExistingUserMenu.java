package GUI;

import javax.swing.*;

public class ExistingUserMenu extends JFrame {

    private JPanel existingUserMenu;
    private JButton a6EditProfileButton;
    private JButton a5GenerateAMealButton;
    private JButton a1AnalyzeBodyMassButton;
    private JButton a2AnalyzeEnergyRequiredButton;
    private JButton a4AnalyzeDiseaseButton;
    private JButton a3AnalyzeWorkoutButton;


    public ExistingUserMenu(){
        // initializing the ExistingUserMenu frame
        super("DJ WEPNY Personal Health Aid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(existingUserMenu);
        this.setResizable(false);
        this.pack();

        // If the user clicks on the BMI, close the current page
        a1AnalyzeBodyMassButton.addActionListener(e -> {
            this.dispose();
            //TODO implement the BMIAnalyzerGUI and display the page with the user's BMI Report
            BMIAnalyzerGUI bmi = new BMIAnalyzerGUI();
            bmi.setVisible(true);
        });

        a2AnalyzeEnergyRequiredButton.addActionListener(e -> {
            this.dispose();
            if (ConsoleForGUI.HelperConsole.noInfoFound(2)) {
                //TODO if there is no information found on the user's Activity level,
                // new activityLevel Page pops up to gather user's Active status

                ActivityLevelGUI activityLevel = new ActivityLevelGUI();
                activityLevel.setVisible(true);
            }
            else{
                //TODO if there is already existing information on the user's activity level,
                // display the page with the user's EER Report
                EERGUI eerGUI = new EERGUI();
                eerGUI.setVisible(true);
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
                EERGUI eerGUI = new EERGUI();
                eerGUI.setVisible(true);
            }
        });
    }
    // TODO: do the rest for the rest three buttons, similar to the cases before
}
