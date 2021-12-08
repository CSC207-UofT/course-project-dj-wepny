package gui;

import constants.SystemConstants;
import controllers.Presenter;
import controllers.RunCommand;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

/**
 * This class is the page for Editing user profile.
 */
public class EditProfile extends JFrame {
    // Components for the "Edit Profile" page
    private JPanel editProfile;
    private JTextField username;
    private JTextField weight;
    private JTextField height;
    private JTextField age;
    private JButton activityLevelButton;
    private JButton exercisePreferenceButton;
    private JButton symptomsButton;
    private JButton foodPreferenceButton;
    private JButton updateButton;
    private JRadioButton femaleRadioButton;
    private JRadioButton maleRadioButton;
    private JButton returnButton;
    private JLabel updatedMessage;
    private JLabel headerImgLabel;
    private JLabel invalidHeight;
    private JLabel invalidWeight;
    private JLabel invalidAge;
    private JLabel invalidGender;
    private BufferedImage headerImg;

    public EditProfile() {
        super("DJ WEPNY Personal Health Aid");
        // Initial setting of the page
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(editProfile);
        this.setSize(700, 1000);
        this.setResizable(false);
        this.updatedMessage.setVisible(false);
        updatedMessage.setText(SystemConstants.UPDATED_PROFILE);
        RunCommand infoGetter = new RunCommand();

        // Set text for invalid input
        this.invalidAge.setText(Presenter.setTextButtons("invalid2"));
        this.invalidGender.setText(Presenter.setTextButtons("invalid2"));
        this.invalidHeight.setText(Presenter.setTextButtons("invalid2"));
        this.invalidWeight.setText(Presenter.setTextButtons("invalid2"));

        // Set text for update information
        this.invalidAge.setText(SystemConstants.UPDATED_PROFILE);

        // Set the invalid message to invisible
        this.invalidAge.setVisible(false);
        this.invalidGender.setVisible(false);
        this.invalidHeight.setVisible(false);
        this.invalidWeight.setVisible(false);

        // Set the text for the text panes
        this.username.setText((String) infoGetter.retrieveUser("name"));
        this.weight.setText((String) infoGetter.retrieveUser("weight"));
        this.height.setText((String) infoGetter.retrieveUser("height"));
        this.age.setText((String) infoGetter.retrieveUser("age"));

        // Buttons for Gender Selection
        femaleRadioButton.addActionListener(e -> infoGetter.executeCommandUpdateInfo(5, "F"));

        maleRadioButton.addActionListener(e -> infoGetter.executeCommandUpdateInfo(5, "M"));

        updateButton.addActionListener(e -> {
            String gender;
            if(maleRadioButton.isSelected()){
                gender = "M";
            }
            else if(femaleRadioButton.isSelected()){
                gender= "F";
            }
            else{
                gender = "";
            }
            // if the input is invalid set invalid prompt to visible
            if(!consoleforgui.HelperConsole.validGender(gender)){
                this.invalidGender.setVisible(true);
                this.pack();
            }
            // if the input is valid set invalid prompt back to invisible
            if(consoleforgui.HelperConsole.validGender(gender)){
                this.invalidGender.setVisible(false);
            }
            if(!consoleforgui.HelperConsole.validHeight(height.getText())){
                this.invalidHeight.setVisible(true);
                this.pack();
            }
            if(consoleforgui.HelperConsole.validHeight(height.getText())){
                this.invalidHeight.setVisible(false);
            }
            if(!consoleforgui.HelperConsole.validWeight(weight.getText())){
                this.invalidWeight.setVisible(true);
                this.pack();
            }
            if(consoleforgui.HelperConsole.validWeight(weight.getText())){
                this.invalidWeight.setVisible(false);
            }
            if(!consoleforgui.HelperConsole.validAge(age.getText())){
                this.invalidAge.setVisible(true);
                this.pack();
            }
            if(consoleforgui.HelperConsole.validAge(age.getText())){
                this.invalidAge.setVisible(false);
            }

            // After pressing the "update" Button, the new information is saved and a message is displayed.
            if(consoleforgui.HelperConsole.validGender(gender) &&
                    consoleforgui.HelperConsole.validHeight(height.getText()) &&
                    consoleforgui.HelperConsole.validWeight(weight.getText()) &&
                    consoleforgui.HelperConsole.validAge(age.getText())) {
                infoGetter.executeCommandUpdateInfo(1, this.username.getText());
                infoGetter.executeCommandUpdateInfo(2, this.height.getText());
                infoGetter.executeCommandUpdateInfo(3, this.weight.getText());
                infoGetter.executeCommandUpdateInfo(4, this.age.getText());
                this.updatedMessage.setVisible(true);
                this.pack();
            }

        });


        // After pressing the "return" button, this page is closed and the menu page is opened.
        returnButton.addActionListener(e -> {
            this.dispose();
            UserMenu Menu = new UserMenu(ConsoleGUI.getUserType());
            Menu.setVisible(true);
        });

        // After pressing the "activity level" button, this page is closed and the EERAnalyzer page is opened for user
        // to edit their activity level.
        activityLevelButton.addActionListener(e -> {
            this.dispose();
            EERPromptGUI activityLevel = new EERPromptGUI("edit");
            activityLevel.setVisible(true);

        });

        // After pressing the "exercise preference" button, this page is closed and the EERAnalyzer page is opened
        // for the user to edit their preferences (muscle groups, equipments, etc.)
        exercisePreferenceButton.addActionListener(e -> {
            this.dispose();
            ExercisePreference preference = new ExercisePreference("edit");
            preference.setVisible(true);
        });

        // After pressing the "symptoms" button, this page is closed and the DiseaseAnalyzer page is opened for the
        // user to edit their list of symptoms.
        symptomsButton.addActionListener(e -> {
            this.dispose();
            infoGetter.addInfo("", 6);
            DiseaseAnalyzerGUI potentialDisease;
            try {
                potentialDisease = new DiseaseAnalyzerGUI();
                potentialDisease.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // After pressing the "food preference" button, this page is closed and the MealPlanGenerator page is opened
        // for the user to edit their food preferences.
        foodPreferenceButton.addActionListener(e -> {
            this.dispose();
            infoGetter.addInfo("", 7);
            MealPlanGeneratorGUI mealPlanGUI = new MealPlanGeneratorGUI("edit");
            mealPlanGUI.setVisible(true);
        });
        this.pack();
    }


    private void createUIComponents() {
        try {
            headerImg = ImageIO.read(Presenter.printImgFile(""));
        } catch (IOException ex) {
            System.out.println(Presenter.pathwayNotFound());
        }
        headerImgLabel = new JLabel(new ImageIcon(headerImg));
    }
}
