package gui;


import constants.EERConstants;
import controllers.Presenter;
import controllers.RunCommand;

import javax.swing.*;

public class EERPromptGUI extends JFrame {

    private JPanel EERPromptGUI;
    private JTextPane instruction;
    private JButton commandOne;
    private JButton commandTwo;
    private JButton commandThree;
    private JButton commandFour;
    private JLabel invalidInput;
    private JButton returnToMenu;
    private String userInput;
    RunCommand commandExecutor = new RunCommand(2);
    private String output;

    public EERPromptGUI(){
        super("DJ WEPNY Personal Health Aid");
        this.setSize(1000, 700);
        EERPromptGUI.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(EERPromptGUI);
        this.setResizable(false); // Think we should set this to true, so we can resize the window.

        // Hiding this unless the user has the wrong input
        this.invalidInput.setVisible(false);

        this.instruction.setEditable(false);
        this.returnToMenu.setVisible(false);

        instruction.setText("Please select one of the following based on your daily activity level: ");
        commandOne.setText("1. Sedentary");
        commandTwo.setText(("2. Low Active"));
        commandThree.setText("3. Active");
        commandFour.setText("4. Very Active");
        returnToMenu.setText("Return to Menu");

        this.pack();

            // Get the user input from the selected button.
        commandOne.addActionListener(e -> {
            userInput = "1";
            helperForDisplay();
        });

        commandTwo.addActionListener(e -> {
            userInput = "2";
            helperForDisplay();
        });

        commandThree.addActionListener(e -> {
            userInput = "3";
            helperForDisplay();
        });

        commandFour.addActionListener(e -> {
            userInput = "4";
            helperForDisplay();
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
        this.setResizable(false);
        this.invalidInput.setVisible(false);
        commandOne.setVisible(false);
        commandTwo.setVisible(false);
        commandThree.setVisible(false);
        commandFour.setVisible(false);
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

    private void helperForDisplay(){
        // Display error message if the input is invalid, keep the error message hidden otherwise.
        if (consoleforgui.HelperConsole.isNotNum(userInput)){
            invalidInput.setVisible(true);
        }
        else{
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
            this.pack();

        }
    }

}
