package GUI;

import javax.swing.*;

import constants.*;
import controllers.Presenter;
import controllers.RunCommand;

import java.awt.*;
import java.util.ArrayList;

public class MealPlanGeneratorGUI extends JFrame {

    RunCommand commandExecutor = new RunCommand(5);
    private ArrayList<Object> foodPreference = new ArrayList<>();
    private boolean lowCarbs = false;
    private boolean lowFat = false;
    private boolean lowSugar = false;
    private boolean vegetarian = false;
    private String numFoods;
    private String output;

    private JPanel MealPlanGUI;
    private JCheckBox lowCarbsCheckBox;
    private JCheckBox lowFatCheckBox;
    private JCheckBox lowSugarCheckBox;
    private JCheckBox vegetarianCheckBox;
    private JTextPane numFoodsPrompt;
    private JTextField numFoodsInput;
    private JLabel invalidInput;
    private JButton enterButton;
    private JTextPane intro;
    private JButton returnToMenu;

    /**
     * MealPlanGeneratorGUI Constructor for a new user/users that have no foodPreferences info
     */
    public MealPlanGeneratorGUI() {
        // setting up JFrame
        super("DJ WEPNY's Meal Plan Generator");
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MealPlanGUI);
        this.setResizable(false);
        this.invalidInput.setVisible(false);
        this.returnToMenu.setVisible(false);
        this.pack();

        //set text
        lowCarbsCheckBox.setText(MealPlanConstants.LOWCARBS);
        lowFatCheckBox.setText(MealPlanConstants.LOWFAT);
        lowSugarCheckBox.setText(MealPlanConstants.LOWSUGAR);
        vegetarianCheckBox.setText(MealPlanConstants.VEG);
        numFoodsPrompt.setText(MealPlanConstants.NUM_FOODS);
        invalidInput.setText(Exceptions.INVALID_INPUT);
        enterButton.setText("Enter");
        intro.setText(MealPlanConstants.MEALPLAN_INTRO_GUI);
        returnToMenu.setText("Return to Menu");

        // setting up checkboxes and foodPreference arraylist
        lowCarbsCheckBox.addActionListener(e -> lowCarbs = true);
        lowSugarCheckBox.addActionListener(e -> lowSugar = true);
        lowFatCheckBox.addActionListener(e -> lowFat = true);
        vegetarianCheckBox.addActionListener(e -> vegetarian = true);

        returnToMenu.addActionListener(e -> {
            this.dispose();
            UserMenu Menu = new UserMenu(ConsoleGUI.getUserType());
            Menu.setVisible(true);
        });

        enterButton.addActionListener(e -> {

            String tempNumFoods = numFoodsInput.getText();
            if (!(ConsoleForGUI.HelperConsole.isNotNum(tempNumFoods)) && Integer.parseInt(tempNumFoods) > 0) {
                //set numFoods
                numFoods = tempNumFoods;

                // set up foodPreference
                foodPreference.add(lowCarbs);
                foodPreference.add(lowSugar);
                foodPreference.add(lowFat);
                foodPreference.add(vegetarian);
                foodPreference.add(numFoods);

                // add info to commandExecutor
                commandExecutor.addInfo(foodPreference, 5);

                // execute command
                try {
                    commandExecutor.executeCommand();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                // retrieve the output
                Presenter analyzeResults = new Presenter(commandExecutor.getAnalyzer());
                output = analyzeResults.retrieveOutput();

                // hide components
                for (Component c : MealPlanGUI.getComponents()) {
                    c.setVisible(false);
                }

                // display results
                setSize(1000, 700);
                intro.setText(output);
                intro.setVisible(true);
                returnToMenu.setVisible(true);

            } else {
                invalidInput.setVisible(true);
            }

        });

    }

    /**
    MealPlanGeneratorGUI Constructor for users that already have foodPreferences info
     */
    public MealPlanGeneratorGUI(String usertype) {
        super("DJ WEPNY's Meal Plan Generator");
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MealPlanGUI);
        this.setResizable(false);
        this.invalidInput.setVisible(false);
        this.returnToMenu.setVisible(false);
        this.pack();

        //set text
        lowCarbsCheckBox.setText(MealPlanConstants.LOWCARBS);
        lowFatCheckBox.setText(MealPlanConstants.LOWFAT);
        lowSugarCheckBox.setText(MealPlanConstants.LOWSUGAR);
        vegetarianCheckBox.setText(MealPlanConstants.VEG);
        numFoodsPrompt.setText(MealPlanConstants.NUM_FOODS);
        invalidInput.setText(Exceptions.INVALID_INPUT);
        enterButton.setText("Enter");
        intro.setText(MealPlanConstants.MEALPLAN_INTRO_GUI);
        returnToMenu.setText("Return to Menu");

        // execute command
        try {
            commandExecutor.executeCommand();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // retrieve the output
        Presenter analyzeResults = new Presenter(commandExecutor.getAnalyzer());
        output = analyzeResults.retrieveOutput();

        // hide components
        for (Component c : MealPlanGUI.getComponents()) {
            c.setVisible(false);
        }

        // display results
        setSize(1000, 700);
        intro.setText(output);
        intro.setVisible(true);
        returnToMenu.setVisible(true);


    }
}
