package GUI;

import javax.swing.*;

public class BMIAnalyzerGUI extends JFrame {
    private JPanel BMIAnalyzer;

    public BMIAnalyzerGUI(){
        super("DJ WEPNY Personal Health Aid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(BMIAnalyzer);
        this.setResizable(false);
        this.pack();

    }

}
