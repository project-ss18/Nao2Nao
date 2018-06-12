package view;

import model.interview.Answer;
import model.interview.Block;
import model.interview.Interview;
import model.interview.Question;
import model.robot.Robot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InterviewPlayer {

    private JPanel panel;
    private JButton playButton;
    private JButton stopButton;
    private JButton pauseButton;
    private JProgressBar progressBar;
    private JButton zuruckButton;
    private JTextPane previewJTextPane;
    private JComboBox comboBoxGoTo;
    private JButton goToButton;

    private controller.InterviewPlayer interviewPlayer;
    private ArrayList<Robot> robotList = new ArrayList<Robot>();


    InterviewPlayer(JFrame frame, Interview interview, ArrayList<Robot> robots) {

        for (Robot r : robots) {
            this.robotList.add(r);
        }
        previewJTextPane.setEditable(false);
        previewJTextPane.setText("");
        frame.setContentPane(panel);
        frame.setPreferredSize(new Dimension(600, 350));
        progressBar.getModel().setValue(50);

        interviewPlayer = new controller.InterviewPlayer(interview);

        for (Block block : interview.getBlockList()) {
            previewJTextPane.setText(previewJTextPane.getText() + ("\nBlock " + String.valueOf(block.getBid()) + ": "));
            for (Question question : block.getQuestionList()) {
                previewJTextPane.setText(previewJTextPane.getText() + "\n\tFrage " + (String.valueOf(question.getId()) + ":\t" + question.getPhrase()));
                comboBoxGoTo.addItem(("B " + block.getBid() + ": " + "Frage " + question.getId()));
                for (Answer answer : question.getAnswerList()) {
                    previewJTextPane.setText(previewJTextPane.getText() + "\n\t\tAntwort " + (String.valueOf(answer.getId()) + ":\t" + answer.getPhrase()));
                    comboBoxGoTo.addItem(("B " + block.getBid() + "  F" + question.getId() + ": " + "Antwort " + answer.getId()));
                }
            }
        }

        previewJTextPane.setVisible(true);
        frame.pack();
        frame.repaint();
        frame.setVisible(true);
        zuruckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Menu();
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    interviewPlayer.startInterview(robotList);
                } catch (Exception exc) {
                    System.out.println(exc);
                }
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interviewPlayer.pauseInterview();
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 5, new Insets(0, 0, 0, 0), -1, -1));
        playButton = new JButton();
        playButton.setText("Play");
        panel.add(playButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        stopButton = new JButton();
        stopButton.setText("Stop");
        panel.add(stopButton, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pauseButton = new JButton();
        pauseButton.setText("Pause");
        panel.add(pauseButton, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        zuruckButton = new JButton();
        zuruckButton.setText("Zurück");
        panel.add(zuruckButton, new com.intellij.uiDesigner.core.GridConstraints(3, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        previewJTextPane = new JTextPane();
        previewJTextPane.setText("");
        panel.add(previewJTextPane, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        progressBar = new JProgressBar();
        panel.add(progressBar, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxGoTo = new JComboBox();
        panel.add(comboBoxGoTo, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        goToButton = new JButton();
        goToButton.setText("Go to");
        panel.add(goToButton, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Sprungauswahl:");
        panel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}