package GUI;


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
    private String userInput;

    public EERPromptGUI(){
            super("DJ WEPNY Personal Health Aid");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setContentPane(EERPromptGUI);
            this.setResizable(false);
            // Hiding this unless the user has the wrong input
            this.invalidInput.setVisible(false);

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

                //Close this window and opens the result GUI.
                this.dispose();
                EERResultGUI eerresultGUI = new EERResultGUI();
                eerresultGUI.setVisible(true);
            }


        });
    }
}
