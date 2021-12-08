package gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import controllers.Presenter;
import controllers.RunCommand;
import consoleforgui.HelperConsole;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;

public class DiseaseAnalyzerGUI extends JFrame implements ActionListener{
    private JPanel DiseaseAnalyzerGUI;
    private JTextField symptomInput;
    private JLabel instructions;
    private JButton submitButton;
    private JLabel instructions2;
    private JLabel symptomPrompt;
    private JLabel invalidInput;
    private JButton returnToMenu;
    private JLabel headerImgLabel;
    private JLabel instructions3;
    private JLabel instructionsEx;
    private JLabel instructions4;
    RunCommand commandExecutor = new RunCommand(4);
    private final Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
    private String output;
    private BufferedImage headerImg;

    public DiseaseAnalyzerGUI() throws Exception {
        //initial setup
        super("DJ WEPNY Personal Health Aid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(DiseaseAnalyzerGUI);
        this.setResizable(true);
        this.setSize(10000,10000);
        invalidInput.setVisible(false);
        invalidInput.setText(Presenter.setTextButtons("invalid"));
        returnToMenu.setText(Presenter.setTextButtons("return"));

        symptomInput.setPreferredSize(new Dimension(250, 60));
        commandExecutor.resetPotentialDisease();
        instructions.setText(Presenter.DiseasePrompt("start1"));
        instructions2.setText(Presenter.DiseasePrompt("start2"));
        instructions3.setText(Presenter.DiseasePrompt("description1"));
        instructions4.setText(Presenter.DiseasePrompt("description2"));
        instructionsEx.setText(Presenter.DiseasePrompt("example"));

        commandExecutor.executeCommand();
        output = analyze_results.retrieveOutput();
        System.out.println("output2" + output);
        symptomPrompt.setText(output);

        submitButton.addActionListener(this);
        returnToMenu.addActionListener(e -> {
            this.dispose();
            UserMenu Menu = new UserMenu(ConsoleGUI.getUserType());
            Menu.setVisible(true);
        });

        this.pack();
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton){
        if(e.getSource() == submitButton){
            invalidInput.setVisible(false);
            String symptoms = symptomInput.getText();
            List<String> newInput = HelperConsole.convertInputToList(symptoms);
            symptomInput.setText("");
            if (!symptoms.equals("N/A") && !checkValidInput(symptoms, output)) {
                    invalidInput.setVisible(true);

            }
            else{
                List<String> currentSymptoms;
                if(symptoms.equals("N/A")){
                    currentSymptoms = new ArrayList<>();
                }
                else{
                    currentSymptoms = newInput;
                }

                try {
                    int diseaseSize = commandExecutor.executeCommandDisease(currentSymptoms);
                    System.out.println("symptom" + output);
                    output = analyze_results.retrieveOutput();
                    symptomPrompt.setText(output);
                    if(diseaseSize <= 6){

                        instructions.setVisible(false);
                        instructions2.setVisible(false);
                        instructions3.setVisible(false);
                        instructionsEx.setVisible(false);
                        submitButton.setEnabled(false);

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        }


       }

    }
    /**
     * checks whether the user's input is a valid input given the symptoms prompted
     *
     * @param inputSymptoms    a String that contains the information that the user inputs
     * @param promptedSymptoms a number that specifies what type of information it is
     * @return true if user input is a valid input
     */
private static boolean checkValidInput(String inputSymptoms, String promptedSymptoms){

    return HelperConsole.checkSymptomInput(inputSymptoms, promptedSymptoms);
}

    private void createUIComponents() {
        try {
            headerImg = ImageIO.read(Presenter.printImgFile("disease"));
        } catch (IOException ex) {
            System.out.println(Presenter.pathwayNotFound());
        }

        headerImgLabel = new JLabel(new ImageIcon(headerImg));
    }
}
