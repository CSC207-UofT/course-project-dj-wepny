package gui;

import api.UserParser;
import controllers.Presenter;
import controllers.RunCommand;
import controllers.UserController;
import consoleforgui.HelperConsole;

import javax.swing.*;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * This class is the menu page of the program (the page that has all the functionalities as an option).
 */

public class UserMenu extends JFrame {

    // Components of the page.
    private JPanel existingUserMenu;
    private JButton a6EditProfileButton;
    private JButton a5GenerateAMealButton;
    private JButton a1AnalyzeBodyMassButton;
    private JButton a2AnalyzeEnergyRequiredButton;
    private JButton a4AnalyzeDiseaseButton;
    private JButton a3AnalyzeWorkoutButton;
    private JTextPane welcomeMessage;
    private JButton logOutButton;
    private JLabel headerImgLabel;
    private BufferedImage headerImg;


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
            String output = Presenter.printUserIDMessage((String) infoGetter.retrieveUser("id"));
            welcomeMessage.setText(output);
            this.a6EditProfileButton.setVisible(false);
            this.pack();
        }
        this.pack();

        // If the user clicks on the BMI button, close the current page and opens the BMI page.
        a1AnalyzeBodyMassButton.addActionListener(e -> {
            this.dispose();
            BMIPromptGUI bmi = new BMIPromptGUI();
            bmi.setVisible(true);
        });

        // If the user clicks on the EER button, close the current page and opens the EER page.
        a2AnalyzeEnergyRequiredButton.addActionListener(e -> {
            this.dispose();
            if (consoleforgui.HelperConsole.noInfoFound(2)) {
                EERPromptGUI activityLevel = new EERPromptGUI();
                activityLevel.setVisible(true);
            }
            else{

                EERPromptGUI activityLevel = new EERPromptGUI("existing");
                activityLevel.setVisible(true);
            }
        });

        // If the user clicks on the "analyze workout" button, close the current page and opens the ExerciseAnalyzer
        // page.
        a3AnalyzeWorkoutButton.addActionListener(e -> {
            this.dispose();
            if (consoleforgui.HelperConsole.noInfoFound(3)) {

                ExercisePreference preference = new ExercisePreference();
                preference.setVisible(true);
            }
            else{

                ExercisePreference preference = new ExercisePreference("existing");
                preference.setVisible(true);
            }
        });

        //If the "analyze disease" buttons is pressed, close the current page and open the DiseaseAnalyzer page.
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

        // If the "generate a meal plan" button is pressed, close the current page and open the MealPlanGenerator
        // page.
        a5GenerateAMealButton.addActionListener(e -> {
            this.dispose();
            if (consoleforgui.HelperConsole.noInfoFound(5)) {
                MealPlanGeneratorGUI mealPlanGUI = new MealPlanGeneratorGUI();
                mealPlanGUI.setVisible(true);
            }
            else {
                MealPlanGeneratorGUI mealPlanGUI = new MealPlanGeneratorGUI("existing");
                mealPlanGUI.setVisible(true);
            }
        });

        // If the "edit profile" button is pressed, close the current page and open the Edit Profile page.
        a6EditProfileButton.addActionListener(e -> {
            this.dispose();
            EditProfile editProfile = new EditProfile();
            editProfile.setVisible(true);
        });
        // If the "log out" button is pressed, close the page.
        logOutButton.addActionListener(e -> {
            this.dispose();
            if (num == 1) {
                // once the new user log out, add them to existing users
                HelperConsole.addToExisting();
                // append the new user's information into the file
                try {
                    UserParser.writeUserInfo("write", Presenter.printUserFile());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                try {
                    //update the file once the user logged out with any changed information
                    UserParser.writeUserInfo("update", Presenter.printUserFile());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                List<String> userInfo = UserParser.readUserInfo(Presenter.printUserFile());
                UserController.readExistingUser(userInfo);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ConsoleGUI console = new ConsoleGUI();
            console.setVisible(true);

        });
    }


    private void createUIComponents() {
        try{
            headerImg = ImageIO.read(Presenter.printImgFile("login"));
        } catch (IOException ex) {
            System.out.println(Presenter.pathwayNotFound());
        }

        headerImgLabel = new JLabel(new ImageIcon(headerImg));
    }
}
