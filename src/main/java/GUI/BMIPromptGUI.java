package GUI;

import controllers.Presenter;
import controllers.RunCommand;

import javax.swing.*;

public class BMIPromptGUI extends JFrame {
    private JPanel BMIPromptGUI;
    private JTextPane result;
    private JButton returnToMenu;
    RunCommand commandExecutor = new RunCommand(1);

    public BMIPromptGUI(){
        super("DJ WEPNY Personal Health Aid");
        this.setSize(1000, 700);
        BMIPromptGUI.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(BMIPromptGUI);
        this.setResizable(true);
        createUIComponents();

        try {
            commandExecutor.executeCommand();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
        String output = analyze_results.retrieveOutput();
        result.setText(output);
        returnToMenu.setVisible(true);
        this.pack();

        returnToMenu.addActionListener(e -> {
            this.dispose();
            UserMenu userMenu = new UserMenu(ConsoleGUI.getUserType());
            userMenu.setVisible(true);
        });

    }

    private void createUIComponents() {
        returnToMenu.setText("Return to Menu");
    }
}
