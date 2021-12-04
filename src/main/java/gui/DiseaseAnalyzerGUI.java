package gui;

import javax.swing.*;
import controllers.Presenter;
import controllers.RunCommand;
import consoleforgui.HelperConsole;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DiseaseAnalyzerGUI extends JFrame implements ActionListener{
    private JPanel DiseaseAnalyzerGUI;
    private JTextField symptomInput;
    private JLabel instructions;
    private JButton submitButton;
    private JLabel instructions2;
    private JLabel symptomPrompt;
    private JLabel invalidInput;
    private JButton returnToMenu;
    RunCommand commandExecutor = new RunCommand(4);
    private Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
    private String output;
    private static List<String> currentSymptoms;

    public DiseaseAnalyzerGUI() throws Exception {
        //initial setup
        super("DJ WEPNY Personal Health Aid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(DiseaseAnalyzerGUI);
        this.setResizable(true);
        this.setSize(10000,10000);
        invalidInput.setVisible(false);
        invalidInput.setText("invalid input please enter again");
        returnToMenu.setText("Return To Menu");

        symptomInput.setPreferredSize(new Dimension(250, 60));
        commandExecutor.resetPotentialDisease();
        instructions.setText(Presenter.DiseasePrompt("start"));
        instructions2.setText(Presenter.DiseasePrompt("description"));

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
            if (!symptoms.equals("N/A") && !checkValidInput(symptoms, output)) {
                    invalidInput.setVisible(true);
                }
            else{
                if(symptoms.equals("N/A")){
                    currentSymptoms = new ArrayList<String>();
                }
                else{
                    currentSymptoms = newInput;
                }

                try {
                    int diseaseSize = commandExecutor.executeCommandDisease(currentSymptoms);
                    System.out.println("symptom" + output);
                    if(diseaseSize <= 6){

                        output = analyze_results.retrieveOutput();

                        symptomPrompt.setText(output);
                        instructions.setVisible(false);
                        instructions2.setVisible(false);
                        submitButton.setEnabled(false);

                    }
                    else{
                        output = analyze_results.retrieveOutput();
                        symptomPrompt.setText(output);


                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }


       }

    }
    /**
     * checks whether the user input is a valid input given the symptoms prompted
     *
     * @param inputSymptoms    a String that contains the information that the user inputs
     * @param promptedSymptoms a number that specifies what type of information it is
     * @return true if user input is a valid input
     */
private static boolean checkValidInput(String inputSymptoms, String promptedSymptoms){

    return HelperConsole.checkSymptomInput(inputSymptoms, promptedSymptoms);
}}
