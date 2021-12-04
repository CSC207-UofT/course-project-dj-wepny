package gui;

import controllers.RunCommand;

import javax.swing.*;

public class ConsoleGUI extends JFrame{
    private static int userType;
    private JButton newUserButton;
    private JPanel console;
    private JFormattedTextField UserID;
    private JTextPane welcomeMessage;
    private JLabel IDError;
    private JButton enterID;

    public ConsoleGUI(){
        // initializing the screen
        super("DJ WEPNY Personal Health Aid");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(console);
        this.setResizable(false);
        IDError.setVisible(false);
        this.pack();

        // if the "create new user" button is pressed, close the current page and open the new UserLoginPage
        newUserButton.addActionListener(e -> {
            this.dispose();
            NewUserLogin newUserLogin = new NewUserLogin();
            newUserLogin.setVisible(true);
            setUserType(true);
        });

        // if the "enter" button is pressed, close the current page and open the new existing user page
        enterID.addActionListener(e -> {

            // Gets the text from the text box
            String userInput = UserID.getText();
            // check if the user's input is valid
            if(!ConsoleForGUI.ExistingUserConsole.validID(userInput)){
                // if the input is not valid, show the ID error message
                IDError.setVisible(true);
            }
            // if the input is a valid and existing personal id, open the existingUserMenu
            else{
                IDError.setVisible(false);
                setUserType(false);
                int id = Integer.parseInt(userInput);
                RunCommand currentUser = new RunCommand();
                currentUser.setCurrentUser(id);
                this.dispose();
                UserMenu existingUserMenu = new UserMenu(getUserType());
                existingUserMenu.setVisible(true);
            }
        });
    }

    public static void setUserType(boolean newUser) {
        if (newUser) {
            userType = 1;
        }
        else {
            userType = 2;
        }
    }

    public static int getUserType() {
        return userType;
    }
}
