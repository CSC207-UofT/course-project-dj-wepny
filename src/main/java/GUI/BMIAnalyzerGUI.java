package GUI;

import javax.swing.*;

public class BMIAnalyzerGUI extends JFrame {
    private JPanel BMIAnalyzer;

    public BMIAnalyzerGUI(){
        super("DJ WEPNY Personal Health Aid");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(BMIAnalyzer);
        this.setResizable(false);
        this.pack();

    }

}
