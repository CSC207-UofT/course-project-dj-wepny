package gui;

import javax.swing.*;
import controllers.Presenter;
import controllers.RunCommand;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiseaseAnalyzerGUI extends JFrame implements ActionListener{
    private JPanel DiseaseAnalyzerGUI;
    private JTextField symptomInput;
    private JLabel instructions;
    private JButton submitButton;
    private JLabel instructions2;
    private JLabel symptomPrompt;
    private JButton returnToMenu;
    private JLabel diseaseResult;
    RunCommand commandExecutor = new RunCommand(4);
    private Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
    private String output;
    public ArrayList<String> currentSymptoms;

    public DiseaseAnalyzerGUI() throws Exception {
        //initial setup
        super("DJ WEPNY Personal Health Aid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(DiseaseAnalyzerGUI);
        this.setResizable(true);
        this.setSize(10000,10000);
        returnToMenu.setText("Return to Menu");
        returnToMenu.setVisible(false);
        diseaseResult.setVisible(false);

        symptomInput.setPreferredSize(new Dimension(250, 60));
        // do the start Prompt with instructions - currently modified Presenter to return statements (might need to
        // check if that's valid
        commandExecutor.resetPotentialDisease();
        instructions.setText(Presenter.DiseasePrompt("start"));
        instructions2.setText(Presenter.DiseasePrompt("description"));

        submitButton.addActionListener(this);
        commandExecutor.executeCommand();
        output = analyze_results.retrieveOutput();
        symptomPrompt.setText(output);

        // The "submit" button is hidden and the "return to menu" button will show up after the function
        // is finished.

        this.pack();
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton){
        if(e.getSource() == submitButton){
            String symptoms = symptomInput.getText();
            ArrayList<String> newSymptoms = new ArrayList<>();
            if (!symptoms.equals("N/A")) {
                symptoms = symptoms.replaceAll("[\\[\\](){}]", "");
                String[] symptomsList = symptoms.split(",");
                List<String> finalSymptomsList;
                finalSymptomsList = Arrays.asList(symptomsList);
                //convert client symptoms into a list of symptoms;
                //add those symptoms into the current symptoms that client has
                newSymptoms.addAll(finalSymptomsList);
                currentSymptoms = newSymptoms;

            }
            else{
                currentSymptoms = new ArrayList<String>();
            }
            try {
                int diseaseSize = commandExecutor.executeCommandDisease(currentSymptoms);
                output = analyze_results.retrieveOutput();
                diseaseResult.setText(output);
                diseaseResult.setVisible(true);
                symptomPrompt.setVisible(false);
                if(diseaseSize <= 6){
                    submitButton.setEnabled(false);
                    submitButton.setVisible(false);
                    returnToMenu.setVisible(true);

                    returnToMenu.addActionListener(f -> {
                        this.dispose();
                        UserMenu userMenu = new UserMenu(ConsoleGUI.getUserType());
                        userMenu.setVisible(true);
                    });
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(symptoms);
        }

      }

    }
}
