package GUI;

import javax.swing.*;
import controllers.Presenter;

public class EERGUI extends JFrame {
    private JPanel EERGUI;
    private JTextPane result;
    private JButton returnToMenu;


    public EERGUI(){
        super("DJ WEPNY Personal Health Aid");

        // in order to use this function I changed the UserAnalyzer Declaration from private final to private static.
        result.setText(Presenter.retrieveOutput());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(EERGUI);
        this.setResizable(false);
        this.pack();


        // if the "return to menu" button is pressed, close this page and return to the selecting screen
        returnToMenu.addActionListener(e -> {
            this.dispose();
            //TODO: create a new selecting screen page once it's complete.
        });


    }
}
