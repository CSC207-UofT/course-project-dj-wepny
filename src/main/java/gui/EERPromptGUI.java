package gui;


import constants.EERConstants;
import constants.GUIFormatConstants;
import constants.SystemConstants;
import controllers.Presenter;
import controllers.RunCommand;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.awt.image.BufferedImage;

/**
 * This class is the page for the EERAnalyzer function.
 */
public class EERPromptGUI extends JFrame {

    // Components for the page.
    private JPanel EERPromptGUI;
    private JTextPane instruction;
    private JButton commandOne;
    private JButton commandTwo;
    private JButton commandThree;
    private JButton commandFour;
    private JLabel invalidInput;
    private JButton returnToMenu;
    private JLabel success;
    private JLabel headerImgLabel;
    private String userInput;
    private final RunCommand commandExecutor = new RunCommand(2);
    private String output;
    private BufferedImage headerImg;

    public EERPromptGUI(){
        super("DJ WEPNY Personal Health Aid");
        // Initial setting of the page.
        this.setSize(1000, 700);
        EERPromptGUI.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(EERPromptGUI);
        this.success.setVisible(false);
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

        // Get the resulting output from the selected button.
        commandOne.addActionListener(e -> {
            userInput = "1";
            helperForDisplay(1);
        });

        commandTwo.addActionListener(e -> {
            userInput = "2";
            helperForDisplay(1);
        });

        commandThree.addActionListener(e -> {
            userInput = "3";
            helperForDisplay(1);
        });

        commandFour.addActionListener(e -> {
            userInput = "4";
            helperForDisplay(1);
        });

        // After pressing the "return to menu" button, this page is closed and the user menu page is opened.
        returnToMenu.addActionListener(e -> {
            this.dispose();
            UserMenu Menu = new UserMenu(ConsoleGUI.getUserType());
            Menu.setVisible(true);
        });
    }

    // Overloaded constructor for existing users. Basically the same as the case of a new user.
    public EERPromptGUI(String userType) {
        super("DJ WEPNY Personal Health Aid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(EERPromptGUI);
        this.setResizable(false);
        this.setSize(1000, 700);
        EERPromptGUI.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        this.invalidInput.setVisible(false);
        this.success.setVisible(false);

        if (userType.equals("existing")) {
            commandOne.setVisible(false);
            commandTwo.setVisible(false);
            commandThree.setVisible(false);
            commandFour.setVisible(false);
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
            Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
            this.output = analyze_results.retrieveOutput();
            instruction.setText(this.output);
            returnToMenu.setVisible(true);
        }
        else {
            this.instruction.setEditable(false);

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
                helperForDisplay(2);
                success.setText(SystemConstants.UPDATED_SUCCESSFULLY);
                success.setVisible(true);
                this.pack();
            });

            commandTwo.addActionListener(e -> {
                userInput = "2";
                helperForDisplay(2);
                success.setText(SystemConstants.UPDATED_SUCCESSFULLY);
                success.setVisible(true);
                this.pack();
            });

            commandThree.addActionListener(e -> {
                userInput = "3";
                helperForDisplay(2);
                success.setText(SystemConstants.UPDATED_SUCCESSFULLY);
                success.setVisible(true);
                this.pack();
            });

            commandFour.addActionListener(e -> {
                userInput = "4";
                helperForDisplay(2);
                success.setText(SystemConstants.UPDATED_SUCCESSFULLY);
                success.setVisible(true);
                this.pack();
            });

            returnToMenu.addActionListener(e -> {
                this.dispose();
                EditProfile Menu = new EditProfile();
                Menu.setVisible(true);
            });
        }

    }

    /**
     * Helper function to set up the display based on the option that the user chooses.
     * @param type An integer representing the type of the user.
     */
    private void helperForDisplay(int type){
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
            if (type == 1) {
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

    private void createUIComponents() {
        try{
            headerImg = ImageIO.read(GUIFormatConstants.eerAnalyzerImgFile);
        }catch (IOException ex){
            System.out.println("File pathway was not found");
        }

        headerImgLabel = new JLabel(new ImageIcon(headerImg));
    }
}
