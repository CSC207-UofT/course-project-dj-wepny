package gui;

import constants.GUIFormatConstants;
import controllers.Presenter;
import controllers.RunCommand;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.awt.image.BufferedImage;

/**
 * This class is the Page for BMIAnalyzer function.
 */

public class BMIPromptGUI extends JFrame {

    // Components of the page.
    private JPanel BMIPromptGUI;
    private JTextPane result;
    private JButton returnToMenu;
    private JLabel headerImgLabel;
    private final RunCommand commandExecutor = new RunCommand(1);
    private BufferedImage headerImg;

    public BMIPromptGUI(){
        super("DJ WEPNY Personal Health Aid");
        // Initial setting of the page.
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

    private void createUIComponents() {
        try{
            headerImg = ImageIO.read(GUIFormatConstants.bmiGeneratorImgFile);
        }catch (IOException ex){
            System.out.println("File pathway was not found");
        }

        headerImgLabel = new JLabel(new ImageIcon(headerImg));
    }
}
