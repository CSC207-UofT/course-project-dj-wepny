package System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {


    private final JFrame frame;
    private final JPanel panel;
    private final TextField textField;
    public GUI(){
        //variable instantiation
        frame = new JFrame();
        panel = new JPanel();
        textField = new TextField(20);


        //setting up a border and layout
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(new GridLayout(0,1));
        panel.add(textField);

        //adding components to the frame

        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Some Personal Health App idk");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //making the GUI respond to user actions:
        textField.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //when the user presses enter after entering the text, we do things.
    }

    public static void main(String[] args) {
        new GUI();
    }

}
