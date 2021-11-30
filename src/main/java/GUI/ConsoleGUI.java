package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsoleGUI extends JFrame{
    private JButton newUserButton;
    private JPanel console;
    private JFormattedTextField UserID;
    private JTextPane welcomeMessage;
    private JLabel IDError;
    private JButton enterID;

    public ConsoleGUI(){
        super("DJ WEPNY Personal Health Aid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(console);
        this.setSize(1000, 700);
        this.setResizable(false);
        IDError.setVisible(false);

        newUserButton.addActionListener(e -> {
            NewUserLogin newUserLogin = new NewUserLogin();
            newUserLogin.setVisible(true);
        });
        enterID.addActionListener(e -> {
            String userInput = UserID.getText();
            if(!ConsoleForGUI.ExistingUserConsole.validID(userInput)){
                IDError.setVisible(true);
//                    userInput = UserID.getText();
            }
            else{
                IDError.setVisible(false);
                int id = Integer.parseInt(userInput);
                ExistingUserMenu existingUserMenu = new ExistingUserMenu();
                existingUserMenu.setVisible(true);
            }
        });
    }
}
