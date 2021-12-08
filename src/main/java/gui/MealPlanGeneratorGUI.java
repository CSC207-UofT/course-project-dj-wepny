package gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import constants.*;
import controllers.Presenter;
import controllers.RunCommand;
import constants.GUIFormatConstants;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

/**
 * This class is the page for the MealPlanGenerator function.
 */
public class MealPlanGeneratorGUI extends JFrame {

    // Variables that stores the user's preferences.
    private final RunCommand commandExecutor = new RunCommand(5);
    private ArrayList<Object> foodPreference = new ArrayList<>();
    private boolean lowCarbs = false;
    private boolean lowFat = false;
    private boolean lowSugar = false;
    private boolean vegetarian = false;
    private String numFoods;
    private String output;

    // Components of the page.
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
    private JLabel headerImgLabel;
    private JTextPane success;
    private BufferedImage headerImg;

    /**
     * MealPlanGeneratorGUI Constructor for a new user/users that have no foodPreferences info.
     */
    public MealPlanGeneratorGUI() {
        super("DJ WEPNY's Meal Plan Generator");
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MealPlanGUI);
        this.setResizable(false);
        this.success.setVisible(false);


        //set text
        lowCarbsCheckBox.setText(MealPlanConstants.LOWCARBS);
        lowFatCheckBox.setText(MealPlanConstants.LOWFAT);
        lowSugarCheckBox.setText(MealPlanConstants.LOWSUGAR);
        vegetarianCheckBox.setText(MealPlanConstants.VEG);
        numFoodsPrompt.setText(MealPlanConstants.NUM_FOODS);
        invalidInput.setText(Exceptions.INVALID_INPUT);
        enterButton.setText("Enter");
        intro.setText(MealPlanConstants.MEALPLAN_INTRO_GUI);
        returnToMenu.setText("Return");

        this.invalidInput.setVisible(false);
        this.returnToMenu.setVisible(false);
        this.pack();

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
            if (!(consoleforgui.HelperConsole.isNotNum(tempNumFoods)) && Integer.parseInt(tempNumFoods) > 0) {
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

                this.getContentPane().setLayout(new FlowLayout());
                this.setSize(1000, 700);

                JTextArea textArea = new JTextArea(this.output, 35, 60);
                JScrollPane scrollableTextArea = new JScrollPane(textArea);

                scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                this.getContentPane().add(scrollableTextArea);
                returnToMenu.setVisible(true);

            } else {
                invalidInput.setVisible(true);
            }

        });

    }

    /**
     * MealPlanGeneratorGUI Constructor for users that already have foodPreferences info
     */
    public MealPlanGeneratorGUI(String userType) {
        super("DJ WEPNY's Meal Plan Generator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MealPlanGUI);
        this.setResizable(false);
        this.success.setVisible(false);
        this.invalidInput.setVisible(false);

        if (userType.equals("existing")) {
            //set text
            lowCarbsCheckBox.setText(MealPlanConstants.LOWCARBS);
            lowFatCheckBox.setText(MealPlanConstants.LOWFAT);
            lowSugarCheckBox.setText(MealPlanConstants.LOWSUGAR);
            vegetarianCheckBox.setText(MealPlanConstants.VEG);
            numFoodsPrompt.setText(MealPlanConstants.NUM_FOODS);
            invalidInput.setText(Exceptions.INVALID_INPUT);
            enterButton.setText("ENTER");
            intro.setText(MealPlanConstants.MEALPLAN_INTRO_GUI);
            returnToMenu.setText("RETURN");

            this.invalidInput.setVisible(false);
            this.returnToMenu.setVisible(false);
            this.pack();

            // execute command
            try {
                commandExecutor.executeCommand();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            returnToMenu.addActionListener(e -> {
                this.dispose();
                UserMenu Menu = new UserMenu(ConsoleGUI.getUserType());
                Menu.setVisible(true);
            });

            // retrieve the output
            Presenter analyzeResults = new Presenter(commandExecutor.getAnalyzer());
            output = analyzeResults.retrieveOutput();

            // hide components
            for (Component c : MealPlanGUI.getComponents()) {
                c.setVisible(false);
            }

            this.getContentPane().setLayout(new FlowLayout());
            this.setSize(1000, 700);
            JTextArea textArea = new JTextArea(this.output, 35, 60);
            JScrollPane scrollableTextArea = new JScrollPane(textArea);

            scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            this.getContentPane().add(scrollableTextArea);
            returnToMenu.setVisible(true);
            this.pack();
        } else {
            //set text
            lowCarbsCheckBox.setText(MealPlanConstants.LOWCARBS);
            lowFatCheckBox.setText(MealPlanConstants.LOWFAT);
            lowSugarCheckBox.setText(MealPlanConstants.LOWSUGAR);
            vegetarianCheckBox.setText(MealPlanConstants.VEG);
            numFoodsPrompt.setText(MealPlanConstants.NUM_FOODS);
            invalidInput.setText(Exceptions.INVALID_INPUT);
            enterButton.setText("ENTER");
            intro.setText(MealPlanConstants.MEALPLAN_INTRO_GUI);
            returnToMenu.setText("RETURN");
            returnToMenu.setVisible(false);

            this.invalidInput.setVisible(false);
            this.returnToMenu.setVisible(false);
            this.pack();

            // setting up checkboxes and foodPreference arraylist
            lowCarbsCheckBox.addActionListener(e -> lowCarbs = true);
            lowSugarCheckBox.addActionListener(e -> lowSugar = true);
            lowFatCheckBox.addActionListener(e -> lowFat = true);
            vegetarianCheckBox.addActionListener(e -> vegetarian = true);

            returnToMenu.addActionListener(e -> {
                this.dispose();
                EditProfile Menu = new EditProfile();
                Menu.setVisible(true);
            });

            enterButton.addActionListener(e -> {
                String tempNumFoods = numFoodsInput.getText();
                if (!(consoleforgui.HelperConsole.isNotNum(tempNumFoods)) && Integer.parseInt(tempNumFoods) > 0) {
                    //set numFoods
                    numFoods = tempNumFoods;

                    // set up foodPreference
                    foodPreference.add(lowCarbs);
                    foodPreference.add(lowSugar);
                    foodPreference.add(lowFat);
                    foodPreference.add(vegetarian);
                    foodPreference.add(numFoods);
                    success.setText(SystemConstants.UPDATED_SUCCESSFULLY);
                    success.setVisible(true);
                    this.returnToMenu.setVisible(true);
                    invalidInput.setVisible(false);

                    // add info to commandExecutor
                    commandExecutor.addInfo(foodPreference, 5);

                } else {
                    invalidInput.setVisible(true);
                }

            });

        }
    }

    private void createUIComponents() {
        try {
            headerImg = ImageIO.read(GUIFormatConstants.mealPlanGeneratorImgFile);
        } catch (IOException ex) {
            System.out.println("File pathway was not found");
        }

        headerImgLabel = new JLabel(new ImageIcon(headerImg));
    }
}
