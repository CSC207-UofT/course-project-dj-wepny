package gui;

import constants.GUIFormatConstants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class NewUserLogin extends JFrame {

    private JPanel newUserLoginMenu;
    private JFormattedTextField username;
    private JButton enterButton;
    private JTextField weight;
    private JTextField height;
    private JLabel invalidGender;
    private JLabel invalidHeight;
    private JLabel invalidWeight;
    private JFormattedTextField age;
    private JLabel invalidAge;
    private static ButtonGroup gender;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JLabel headerImgLabel;
    private BufferedImage headerImg;

    public NewUserLogin() {
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
        gender = new ButtonGroup();
        gender.add(maleRadioButton);
        gender.add(femaleRadioButton);

        // what happens when the enter button is pressed
        enterButton.addActionListener(e -> {
            // get user input form test field
            String name = username.getText();
            String userGender;
            if(maleRadioButton.isSelected()){
                userGender = "M";
            }
            else if(femaleRadioButton.isSelected()){
                userGender = "F";
            }
            else{
                userGender = "";
            }
            String userHeight = height.getText();
            String userWeight = weight.getText();
            String userAge = age.getText();

            // if the input is invalid set invalid prompt to visible
            if(!consoleforgui.HelperConsole.validGender(userGender)){
                invalidGender.setVisible(true);
            }
            // if the input is valid set invalid prompt back to invisible
            if(consoleforgui.HelperConsole.validGender(userGender)){
                invalidGender.setVisible(false);
            }
            if(!consoleforgui.HelperConsole.validHeight(userHeight)){
                invalidHeight.setVisible(true);
            }
            if(consoleforgui.HelperConsole.validHeight(userHeight)){
                invalidHeight.setVisible(false);
            }
            if(!consoleforgui.HelperConsole.validWeight(userWeight)){
                invalidWeight.setVisible(true);
            }
            if(consoleforgui.HelperConsole.validWeight(userWeight)){
                invalidWeight.setVisible(false);
            }
            if(!consoleforgui.HelperConsole.validAge(userAge)){
                invalidAge.setVisible(true);
            }
            if(consoleforgui.HelperConsole.validAge(userAge)){
                invalidAge.setVisible(false);
            }
            // if all inputs are valid, create new user with the information
            // TODO: need to go to the next window after the new user is created
            if(consoleforgui.HelperConsole.validGender(userGender) &&
                    consoleforgui.HelperConsole.validHeight(userHeight) &&
                    consoleforgui.HelperConsole.validWeight(userWeight) &&
                    consoleforgui.HelperConsole.validAge(userAge)) {
                String[] basic = {name, userGender};
                String[] personal = {userHeight, userWeight, userAge};
                consoleforgui.NewUserConsole.createUser(basic,personal);
            }
            this.pack();
            this.dispose();
            UserMenu Menu = new UserMenu(ConsoleGUI.getUserType());
            Menu.setVisible(true);

        });


    }

    private void createUIComponents() {
        try{
            headerImg = ImageIO.read(GUIFormatConstants.loginImgFile);
        }catch (IOException ex){
            System.out.println("File pathway was not found");
        }

        headerImgLabel = new JLabel(new ImageIcon(headerImg));
    }
}
