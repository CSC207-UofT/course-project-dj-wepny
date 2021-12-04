package gui;

import api.UserParser;
import constants.SystemConstants;
import controllers.RunCommand;
import system.HelperConsole;

import javax.swing.*;
import java.io.IOException;

public class UserMenu extends JFrame {

    private JPanel existingUserMenu;
    private JButton a6EditProfileButton;
    private JButton a5GenerateAMealButton;
    private JButton a1AnalyzeBodyMassButton;
    private JButton a2AnalyzeEnergyRequiredButton;
    private JButton a4AnalyzeDiseaseButton;
    private JButton a3AnalyzeWorkoutButton;
    private JTextPane welcomeMessage;
    private JButton logOutButton;


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
            BMIPromptGUI bmi = new BMIPromptGUI();
            bmi.setVisible(true);
        });

        a2AnalyzeEnergyRequiredButton.addActionListener(e -> {
            this.dispose();
            if (ConsoleForGUI.HelperConsole.noInfoFound(2)) {
                EERPromptGUI activityLevel = new EERPromptGUI();
                activityLevel.setVisible(true);
            }
            else{

                EERPromptGUI activityLevel = new EERPromptGUI("existing");
                activityLevel.setVisible(true);
            }
        });

        a3AnalyzeWorkoutButton.addActionListener(e -> {
            this.dispose();
            if (ConsoleForGUI.HelperConsole.noInfoFound(3)) {

                ExercisePreference preference = new ExercisePreference();
                preference.setVisible(true);
            }
            else{

                ExercisePreference preference = new ExercisePreference("existing");
                preference.setVisible(true);
            }
        });


        a4AnalyzeDiseaseButton.addActionListener(e -> {
            this.dispose();

            DiseaseAnalyzerGUI potentialDisease;
            try {
                potentialDisease = new DiseaseAnalyzerGUI();
                potentialDisease.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        a5GenerateAMealButton.addActionListener(e -> {
            this.dispose();
            if (ConsoleForGUI.HelperConsole.noInfoFound(5)) {
                MealPlanGeneratorGUI mealPlanGUI = new MealPlanGeneratorGUI();
                mealPlanGUI.setVisible(true);
            }
            else {
                MealPlanGeneratorGUI mealPlanGUI = new MealPlanGeneratorGUI("existing");
                mealPlanGUI.setVisible(true);
            }
        });

        a6EditProfileButton.addActionListener(e -> {
            this.dispose();
            EditProfile editProfile = new EditProfile();
            editProfile.setVisible(true);
        });

        logOutButton.addActionListener(e -> {
            this.dispose();
            if (num == 1) {
                // once the new user log out, add them to existing users
                HelperConsole.addToExisting();
                // append the new user's information into the file
                try {
                    UserParser.writeUserInfo("write", SystemConstants.USER_FILE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                try {
                    //update the file once the user logged out with any changed information
                    UserParser.writeUserInfo("update", SystemConstants.USER_FILE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            ConsoleGUI console = new ConsoleGUI();
            console.setVisible(true);
        });
    }


}
