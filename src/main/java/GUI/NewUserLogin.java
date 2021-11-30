package GUI;

import javax.swing.*;

public class NewUserLogin extends JFrame {

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
    private JTextField age;
    private JLabel invalidAge;

    public NewUserLogin(){
        super("DJ WEPNY Personal Health Aid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(newUserLoginMenu);
        this.setSize(1000, 700);
        this.setResizable(false);
        // set invalid output to invisible
        this.invalidGender.setVisible(false);
        this.invalidHeight.setVisible(false);
        this.invalidWeight.setVisible(false);
        this.invalidAge.setVisible(false);

        this.pack();

        // what happens when the enter button is pressed
        enterButton.addActionListener(e -> {
            // get user input form test field
            String name = username.getText();
            String userGender = gender.getText();
            String userHeight = height.getText();
            String userWeight = weight.getText();
            String userAge = age.getText();

            // if the input is invalid set invalid prompt to visible
            if(!ConsoleForGUI.HelperConsole.validGender(userGender)){
                invalidGender.setVisible(true);
            }
            // if the input is valid set invalid prompt back to invisible
            if(ConsoleForGUI.HelperConsole.validGender(userGender)){
                invalidGender.setVisible(false);
            }
            if(!ConsoleForGUI.HelperConsole.validHeight(userHeight)){
                invalidHeight.setVisible(true);
            }
            if(ConsoleForGUI.HelperConsole.validHeight(userHeight)){
                invalidHeight.setVisible(false);
            }
            if(!ConsoleForGUI.HelperConsole.validWeight(userWeight)){
                invalidWeight.setVisible(true);
            }
            if(ConsoleForGUI.HelperConsole.validWeight(userWeight)){
                invalidWeight.setVisible(false);
            }
            if(!ConsoleForGUI.HelperConsole.validAge(userAge)){
                invalidAge.setVisible(true);
            }
            if(ConsoleForGUI.HelperConsole.validAge(userAge)){
                invalidAge.setVisible(false);
            }
            // if all inputs are valid, create new user with the information
            // TODO: need to go to the next window after the new user is created
            if(ConsoleForGUI.HelperConsole.validGender(userGender) &&
                    ConsoleForGUI.HelperConsole.validHeight(userHeight) &&
                    ConsoleForGUI.HelperConsole.validWeight(userWeight) &&
                    ConsoleForGUI.HelperConsole.validAge(userAge)) {
                String[] basic = {name, userGender};
                String[] personal = {userHeight, userWeight, userAge};
                ConsoleForGUI.NewUserConsole.createUser(basic,personal);
            }
            this.pack();

        });


    }

}
