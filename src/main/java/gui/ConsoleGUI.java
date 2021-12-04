package gui;

import controllers.RunCommand;
import constants.GUIFormatConstants;

import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class ConsoleGUI extends JFrame{
    private static int userType;
    private JButton newUserButton;
    private JPanel console;
    private JFormattedTextField UserID;
    private JLabel welcomeMessage;
    private JLabel IDError;
    private JButton enterID;
    private JLabel imgLabel;
    private JLabel emptyLabel;
    private BufferedImage introImg;

    public ConsoleGUI(){
        // initializing the screen
        super("DJ WEPNY Personal Health Aid");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(console);
        this.setResizable(false);
        IDError.setVisible(false);
        welcomeMessage.setText("Please Enter Your Personal Identification Pin if you are an existing User:");
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
            if(!consoleforgui.ExistingUserConsole.validID(userInput)){
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

    /**
     * Helper function to set the user's type (if the user exist in the file or not)
     * @param newUser A Boolean that's true if the user's a new user, false otherwise.
     */
    public static void setUserType(boolean newUser) {
        if (newUser) {
            userType = 1;
        }
        else {
            userType = 2;
        }
    }

    // Getter for user type.
    public static int getUserType() {
        return userType;
    }

    private void createUIComponents() {
        try{
            introImg = ImageIO.read(GUIFormatConstants.introImgFile);
        }catch (IOException ex){
            System.out.println("File pathway was not found");
        }

        imgLabel = new JLabel(new ImageIcon(introImg));
    }
}
