package gui;

import javax.swing.*;

/**
 * This class is the page for the Login page of a new user.
 */
public class NewUserLogin extends JFrame {

    // Components of the page.
    private JPanel newUserLoginMenu;
    private JTextPane welcomeMessage;
    private JFormattedTextField username;
    private JButton enterButton;
    private JFormattedTextField gender;
    private JTextField weight;
    private JTextField height;
    private JLabel invalidGender;
    private JLabel invalidHeight;
    private JLabel invalidWeight;
    private JFormattedTextField age;
    private JLabel invalidAge;

    public NewUserLogin() {
        super("DJ WEPNY Personal Health Aid");
        // Initial settings of the page.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(newUserLoginMenu);
        this.setSize(1000, 700);
        this.setResizable(false);
        // set invalid output to invisible
        this.invalidGender.setVisible(false);
        this.invalidHeight.setVisible(false);
        this.invalidWeight.setVisible(false);
        this.invalidAge.setVisible(false);
        this.welcomeMessage.setEditable(false);

        this.pack();

        // what happens when the enter button is pressed
        this.enterButton.addActionListener(e -> {
            // get user input form test field
            String name = username.getText();
            String userGender = gender.getText();
            String userHeight = height.getText();
            String userWeight = weight.getText();
            String userAge = age.getText();

            // if the input is invalid set invalid prompt to visible
            if(!consoleforgui.HelperConsole.validGender(userGender)){
                this.invalidGender.setVisible(true);
            }
            // if the input is valid set invalid prompt back to invisible
            if(consoleforgui.HelperConsole.validGender(userGender)){
                this.invalidGender.setVisible(false);
            }
            if(!consoleforgui.HelperConsole.validHeight(userHeight)){
                this.invalidHeight.setVisible(true);
            }
            if(consoleforgui.HelperConsole.validHeight(userHeight)){
                this.invalidHeight.setVisible(false);
            }
            if(!consoleforgui.HelperConsole.validWeight(userWeight)){
                this.invalidWeight.setVisible(true);
            }
            if(consoleforgui.HelperConsole.validWeight(userWeight)){
                this.invalidWeight.setVisible(false);
            }
            if(!consoleforgui.HelperConsole.validAge(userAge)){
                this.invalidAge.setVisible(true);
            }
            if(consoleforgui.HelperConsole.validAge(userAge)){
                this.invalidAge.setVisible(false);
            }
            // if all inputs are valid, create new user with the information
            if(consoleforgui.HelperConsole.validGender(userGender) &&
                    consoleforgui.HelperConsole.validHeight(userHeight) &&
                    consoleforgui.HelperConsole.validWeight(userWeight) &&
                    consoleforgui.HelperConsole.validAge(userAge)) {
                String[] basic = {name, userGender};
                String[] personal = {userHeight, userWeight, userAge};
                consoleforgui.NewUserConsole.createUser(basic,personal);

                this.pack();
                this.dispose();
                this.pack();
                UserMenu Menu = new UserMenu(ConsoleGUI.getUserType());
                Menu.setVisible(true);
            }

        });


    }

}
