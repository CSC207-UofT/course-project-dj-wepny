package GUI;

import javax.swing.*;

public class BMIAnazlyerGUI extends JFrame {
    private JPanel BMIAnalyzer;

    public BMIAnazlyerGUI(){
        super("DJ WEPNY Personal Health Aid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(BMIAnalyzer);
        this.setResizable(false);
        this.pack();

    }

}
