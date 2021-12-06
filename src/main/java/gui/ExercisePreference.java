package gui;

import constants.GUIFormatConstants;
import constants.SystemConstants;
import controllers.Presenter;
import controllers.RunCommand;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

/**
 * This class is the page for the ExerciseAnalyzer function.
 */

public class ExercisePreference extends JFrame {
    // The components for the page.
    private JPanel exercisePreference;
    private JTextArea majorMusclePrompt;
    private JCheckBox armsCheckBox;
    private JCheckBox coreCheckBox;
    private JCheckBox fullBodyCheckBox;
    private JCheckBox legsCheckBox;
    private JCheckBox backCheckBox;
    private JTextPane exerciseWelcome;
    private JButton enterButton;
    private JTextPane minorMusclePrompt;
    private JCheckBox shouldersCheckBox;
    private JCheckBox outerThighCheckBox;
    private JCheckBox glutesCheckBox;
    private JCheckBox hamstringsCheckBox;
    private JCheckBox quadsCheckBox;
    private JCheckBox calvesCheckBox;
    private JCheckBox chestCheckBox;
    private JCheckBox bicepCheckBox;
    private JCheckBox innerThighCheckBox;
    private JCheckBox tricepCheckBox;
    private JCheckBox latsCheckBox;
    private JCheckBox obliqueCheckBox;
    private JTextPane equipmentPrompt;
    private JCheckBox dumbbellsCheckBox;
    private JCheckBox barCheckBox;
    private JCheckBox cableCheckBox;
    private JCheckBox bodyWeightCheckBox;
    private JCheckBox platformCheckBox;
    private JCheckBox machineCheckBox;
    private JCheckBox bandCheckBox;
    private JCheckBox kettleBellCheckBox;
    private JCheckBox medicineBallCheckBox;
    private JCheckBox bosuBallCheckBox;
    private JLabel invalid;
    private JButton returnToMenu;
    private JTextPane success;
    private JLabel headerImgLabel;
    private JScrollPane scroll;
    private String majorMuscle;
    private String minorMuscle;
    private ArrayList<String> equipments = new ArrayList<>();
    private StringBuilder equipment;
    private final RunCommand commandExecutor = new RunCommand(3);
    private String output;
    private BufferedImage headerImg;
    private JTextArea textArea;

    public ExercisePreference() {
        super("DJ WEPNY Personal Health Aid");

        // Initial setup of the page.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(exercisePreference);
        this.setSize(700, 1000);
        this.setResizable(false);
        this.invalid.setVisible(false);
        this.returnToMenu.setVisible(false);
        this.exerciseWelcome.setEditable(false);
        this.majorMusclePrompt.setEditable(false);
        this.minorMusclePrompt.setEditable(false);
        this.equipmentPrompt.setEditable(false);
//        this.pack();

        setUpButtons();

        // After pressing the "return to menu" button, this page is closed and the user menu page is opened.
        returnToMenu.addActionListener(e -> {
            this.dispose();
            UserMenu Menu = new UserMenu(ConsoleGUI.getUserType());
            Menu.setVisible(true);
        });

        // Runs ExerciseAnalyzer after pressing the "enter" button.
        enterButton.addActionListener(e -> {
            if (this.majorMuscle == null || this.minorMuscle == null || this.equipments == null) {
                this.invalid.setVisible(true);
            } else {

                this.equipment = new StringBuilder(this.equipments.get(0));
                if (this.equipments.size() > 1) {
                    for (int i = 1; i < this.equipments.size(); i++) {
                        this.equipment.append('/').append(this.equipments.get(i));
                    }
                }
                commandExecutor.addInfo(new String[]{majorMuscle, minorMuscle, this.equipment.toString()}, 3);
                try {
                    commandExecutor.executeCommand();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
                this.output = analyze_results.retrieveOutput();
                for (Component child : exercisePreference.getComponents()) {
                    child.setVisible(false);
                }
                this.setSize(500, 500);
                this.getContentPane().setLayout(new FlowLayout());

                JTextArea textArea = new JTextArea(this.output,20, 40);
                JScrollPane scrollableTextArea = new JScrollPane(textArea);

                scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                this.getContentPane().add(scrollableTextArea);

                this.exerciseWelcome.setAutoscrolls(true);
//                exerciseWelcome.setText(this.output);
//                exerciseWelcome.setVisible(true);
                returnToMenu.setVisible(true);
//                this.setPreferredSize(new Dimension(1000, 500));
            }
        });

    }

    // Overloaded constructor if the user is an existing user. Everything's basically the same as the case
    // of an new user.
    public ExercisePreference(String userType) {
        super("DJ WEPNY Personal Health Aid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(exercisePreference);
        this.setSize(1000, 500);
        this.setResizable(true);
        this.success.setVisible(false);


        if (userType.equals("existing")) {
            returnToMenu.addActionListener(e -> {
                this.dispose();
                UserMenu Menu = new UserMenu(ConsoleGUI.getUserType());
                Menu.setVisible(true);
            });
            try {
                commandExecutor.executeCommand();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
            this.output = analyze_results.retrieveOutput();
            for (Component child : exercisePreference.getComponents()) {
                child.setVisible(false);
            }
            this.setSize(500, 500);
            this.getContentPane().setLayout(new FlowLayout());

            JTextArea textArea = new JTextArea(this.output,20, 40);
            JScrollPane scrollableTextArea = new JScrollPane(textArea);

            scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            this.getContentPane().add(scrollableTextArea);
            returnToMenu.setVisible(true);

        } else {

            this.invalid.setVisible(false);
            this.returnToMenu.setVisible(false);

            setUpButtons();

            this.pack();

            returnToMenu.addActionListener(e -> {
                this.dispose();
                EditProfile Menu = new EditProfile();
                Menu.setVisible(true);
            });

            this.exerciseWelcome.setAutoscrolls(true);


            enterButton.addActionListener(e -> {
                if (this.majorMuscle == null || this.minorMuscle == null || this.equipments == null) {
                    this.invalid.setVisible(true);
                } else {
                    this.returnToMenu.setVisible(true);
                    this.success.setText(SystemConstants.UPDATED_SUCCESSFULLY);
                    this.success.setVisible(true);
                    this.equipment = new StringBuilder(this.equipments.get(0));
                    if (this.equipments.size() > 1) {
                        for (int i = 1; i < this.equipments.size(); i++) {
                            this.equipment.append('/').append(this.equipments.get(i));
                        }
                    }
                    commandExecutor.addInfo(new String[]{majorMuscle, minorMuscle, this.equipment.toString()}, 3);
                    this.pack();
                }
            });
        }
    }

    /**
     * Helper function to set up buttons for the muscle groups, equipments, etc.
     */
    public void setUpButtons(){
        // Make the checkboxes react to user's clicking.
        armsCheckBox.addActionListener(e -> this.majorMuscle = "Arms");

        coreCheckBox.addActionListener(e -> this.majorMuscle = "Core");

        fullBodyCheckBox.addActionListener(e -> this.majorMuscle = "Full Body");

        backCheckBox.addActionListener(e -> this.majorMuscle = "Back");

        legsCheckBox.addActionListener(e -> this.majorMuscle = "Legs");

        bicepCheckBox.addActionListener(e -> this.minorMuscle = "Bicep");

        shouldersCheckBox.addActionListener(e -> this.minorMuscle = "Shoulders");

        outerThighCheckBox.addActionListener(e -> this.minorMuscle = "Outer Thigh");

        glutesCheckBox.addActionListener(e -> this.minorMuscle = "Glutes");

        hamstringsCheckBox.addActionListener(e -> this.minorMuscle = "Hamstrings");

        quadsCheckBox.addActionListener(e -> this.minorMuscle = "Quads");

        calvesCheckBox.addActionListener(e -> this.minorMuscle = "Calves");

        chestCheckBox.addActionListener(e -> this.minorMuscle = "Chest");

        innerThighCheckBox.addActionListener(e -> this.minorMuscle = "Inner Thigh");

        tricepCheckBox.addActionListener(e -> this.minorMuscle = "Tricep");

        latsCheckBox.addActionListener(e -> this.minorMuscle = "Lats");

        obliqueCheckBox.addActionListener(e -> this.minorMuscle = "Oblique");

        dumbbellsCheckBox.addActionListener(e -> this.equipments.add("Dumbbells"));

        barCheckBox.addActionListener(e -> this.equipments.add("Bar"));

        cableCheckBox.addActionListener(e -> this.equipments.add("Cable"));

        bodyWeightCheckBox.addActionListener(e -> this.equipments.add("Body Weight"));

        platformCheckBox.addActionListener(e -> this.equipments.add("Platform"));

        machineCheckBox.addActionListener(e -> this.equipments.add("Machine"));

        bandCheckBox.addActionListener(e -> this.equipments.add("Band"));

        kettleBellCheckBox.addActionListener(e -> this.equipments.add("Kettle Bell"));

        medicineBallCheckBox.addActionListener(e -> this.equipments.add("Medicine Ball"));

        bosuBallCheckBox.addActionListener(e -> this.equipments.add("Bosu Ball"));
    }

    private void createUIComponents() {
        try{
            headerImg = ImageIO.read(GUIFormatConstants.workoutAnalyzerImgFile);
        }catch (IOException ex){
            System.out.println("File pathway was not found");
        }

        headerImgLabel = new JLabel(new ImageIcon(headerImg));
    }
}
