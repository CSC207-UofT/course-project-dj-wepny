package GUI;

import controllers.RunCommand;

import javax.swing.*;

public class EditProfile extends JFrame {
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

    public EditProfile() {
        super("DJ WEPNY Personal Health Aid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(editProfile);
        this.setSize(700, 1000);
        this.setResizable(false);

        RunCommand infoGetter = new RunCommand();

        this.username.setText((String) infoGetter.retrieveUser("name"));
        this.weight.setText((String) infoGetter.retrieveUser("weight"));
        this.height.setText((String) infoGetter.retrieveUser("height"));
        this.age.setText((String) infoGetter.retrieveUser("age"));

        femaleRadioButton.addActionListener(e -> {
            infoGetter.executeCommandUpdateInfo(5, "F");
        });

        maleRadioButton.addActionListener(e -> {
            infoGetter.executeCommandUpdateInfo(5, "M");
        });

        updateButton.addActionListener(e -> {
            infoGetter.executeCommandUpdateInfo(1, this.username.getText());
            infoGetter.executeCommandUpdateInfo(2, this.height.getText());
            infoGetter.executeCommandUpdateInfo(3, this.weight.getText());
            infoGetter.executeCommandUpdateInfo(4, this.age.getText());
            this.dispose();
            UserMenu Menu = new UserMenu(ConsoleGUI.getUserType());
            Menu.setVisible(true);
        });

        activityLevelButton.addActionListener(e -> {
            this.dispose();
            EERPromptGUI activityLevel = new EERPromptGUI();
            activityLevel.setVisible(true);

        });

        exercisePreferenceButton.addActionListener(e -> {
            this.dispose();
            ExercisePreference preference = new ExercisePreference();
            preference.setVisible(true);
        });


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

        foodPreferenceButton.addActionListener(e -> {
            this.dispose();
            infoGetter.addInfo("", 7);
            MealPlanGeneratorGUI mealPlanGUI = new MealPlanGeneratorGUI();
            mealPlanGUI.setVisible(true);
        });
    }


}
