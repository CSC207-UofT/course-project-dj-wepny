package GUI;


import constants.EERConstants;
import controllers.Presenter;
import controllers.RunCommand;

import javax.swing.*;

public class EERPromptGUI extends JFrame {

    private JPanel EERPromptGUI;
    private JFormattedTextField preferenceInput;
    private JButton enterButton;
    private JTextPane instruction;
    private JLabel commandOne;
    private JLabel commandTwo;
    private JLabel commandThree;
    private JLabel commandFour;
    private JLabel invalidInput;
    private JButton returnToMenu;
    private String userInput;
    RunCommand commandExecutor = new RunCommand(2);
    private String output;

    public EERPromptGUI(){
            super("DJ WEPNY Personal Health Aid");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setContentPane(EERPromptGUI);
            this.setResizable(false);
            // Hiding this unless the user has the wrong input
            this.invalidInput.setVisible(false);
            this.instruction.setEditable(false);
            this.returnToMenu.setVisible(false);

            instruction.setText("Please enter your daily activity level: (Please enter a number from 1 to 4) ");
            commandOne.setText("1. Sedentary");
            commandTwo.setText(("2. Low Active"));
            commandThree.setText("3. Active");
            commandFour.setText("4. Very Active");

            this.pack();

            // Get the user input from the text field
        enterButton.addActionListener(e -> {
            userInput = preferenceInput.getText();

            // Display error message if the input is invalid, keep the error message hidden otherwise.
            if (ConsoleForGUI.HelperConsole.isNotNum(userInput)){
                invalidInput.setVisible(true);
            }
            else{
                //TODO: analyze the EER somehow.
                String level = "";
                switch (userInput) {
                    case "1":
                        level = EERConstants.SED;
                        break;
                    case "2":
                        level = EERConstants.LOW;
                        break;
                    case "3":
                        level = EERConstants.MID;
                        break;
                    case "4":
                        level = EERConstants.HIGH;
                        break;
                }
                commandExecutor.addInfo(level, 2);
                try {
                    commandExecutor.executeCommand();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
                this.output = analyze_results.retrieveOutput();
                instruction.setText(this.output);
                returnToMenu.setVisible(true);
                this.pack();
                commandOne.setVisible(false);
                commandTwo.setVisible(false);
                commandThree.setVisible(false);
                commandFour.setVisible(false);
                enterButton.setVisible(false);
                preferenceInput.setVisible(false);
                this.pack();

            }

        });

        returnToMenu.addActionListener(e -> {
            this.dispose();
            UserMenu Menu = new UserMenu(ConsoleGUI.getUserType());
            Menu.setVisible(true);
        });
    }

    public EERPromptGUI(String userType) {
        super("DJ WEPNY Personal Health Aid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(EERPromptGUI);
        this.setResizable(true);
        this.setSize(900,900);
        this.invalidInput.setVisible(false);
        commandOne.setVisible(false);
        commandTwo.setVisible(false);
        commandThree.setVisible(false);
        commandFour.setVisible(false);
        enterButton.setVisible(false);
        preferenceInput.setVisible(false);
        try {
            commandExecutor.executeCommand();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
        this.output = analyze_results.retrieveOutput();
        instruction.setText(this.output);
        returnToMenu.setVisible(true);
        this.pack();
    }

}
