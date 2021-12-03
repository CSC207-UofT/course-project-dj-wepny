package gui;

import javax.swing.*;

public class ActivityLevelGUI extends JFrame {
    private JPanel activityLevel;

    public ActivityLevelGUI(){
        super("DJ WEPNY Personal Health Aid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(activityLevel);
        this.setResizable(false);
        this.pack();
    }
}
