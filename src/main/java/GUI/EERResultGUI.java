package GUI;

import javax.swing.*;
import controllers.Presenter;
import usecases.EERAnalyzer;

public class EERResultGUI extends JFrame {
    private JPanel EERResultGUI;
    private JTextPane result;
    private JButton returnToMenu;


    public EERResultGUI(){
        super("DJ WEPNY Personal Health Aid");

        EERAnalyzer test = new EERAnalyzer();
        Presenter presenter = new Presenter(test);

        // in order to use this function I changed the UserAnalyzer Declaration from private final to private static.
        // assuming that the presenter works, then we should have the result correctly displayed.
        result.setText(presenter.retrieveOutput());
        result.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(EERResultGUI);
        this.setResizable(false);
        this.pack();


        // if the "return to menu" button is pressed, close this page and return to the selecting screen
        returnToMenu.addActionListener(e -> {
            this.dispose();
            ExistingUserMenu existingUserMenu = new ExistingUserMenu();
            existingUserMenu.setVisible(true);
        });


    }
}
