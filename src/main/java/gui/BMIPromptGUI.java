package gui;

import controllers.Presenter;
import controllers.RunCommand;

import javax.swing.*;

/**
 * This class is the Page for BMIAnalyzer function.
 */

public class BMIPromptGUI extends JFrame {

    // Components we have for the page: one text pane for displaying the result, one button to
    // navigate back to the menu, and a command executor to run the BMIAnalyzer.
    private JPanel BMIPromptGUI;
    private JTextPane result;
    private JButton returnToMenu;
    private final RunCommand commandExecutor = new RunCommand(1);

    public BMIPromptGUI(){
        super("DJ WEPNY Personal Health Aid");
        // Initial setting of the page
//        this.setSize(1000, 1000);
        BMIPromptGUI.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(BMIPromptGUI);
        this.setResizable(true);
        this.result.setEditable(false);
        returnToMenu.setText("Return to Menu");

        // Runs BMIAnalyzer
        try {
            commandExecutor.executeCommand();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Creates a Presenter object that gets the output from the BMIAnalyzer
        Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());

        // Sets the output result to be displayed on the text pane.
        String output = analyze_results.retrieveOutput();
        result.setText(output);

        // Set the "return to menu" button visible.
        returnToMenu.setVisible(true);
        this.pack();

        // When clicked, the button closes this page and opens the menu page.
        returnToMenu.addActionListener(e -> {
            this.dispose();
            UserMenu userMenu = new UserMenu(ConsoleGUI.getUserType());
            userMenu.setVisible(true);
        });

    }

}
