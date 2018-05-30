package view;

import controller.InterviewPlayer;
import model.interview.Interview;
import model.robot.Robot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InterviewSelection extends JFrame {
    private JPanel panel;
    private JButton bestaetigenButton;
    private JComboBox comboBoxRole1;
    private JComboBox comboBoxRole2;
    private JComboBox comboBoxInterview;

    InterviewSelection(JFrame mainFrame) {
        if (InterviewPlayer.getAllInterviews().size() == 0 || Robotlist.getRobotList().size() == 0) {
            JOptionPane.showMessageDialog(null, "Fehler: Kein Interview oder keine Roboter vorhanden", "Fehler", JOptionPane.OK_CANCEL_OPTION);
            return;
        }
        setContentPane(panel);
        setPreferredSize(new Dimension(300, 200));
        for (Interview interview : InterviewPlayer.getAllInterviews()) {
            comboBoxInterview.addItem(interview.getDescription());
        }
        for (Robot robot : Robotlist.getRobotList()) {
            comboBoxRole1.addItem(robot.getName());
            comboBoxRole2.addItem(robot.getName());
        }
        setResizable(false);
        pack();
        setVisible(true);
        comboBoxRole2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBoxRole2.getSelectedItem().equals(comboBoxRole1.getSelectedItem())) {
                    JOptionPane.showMessageDialog(null, "Fehler: Gleicher Roboter", "Fehler", JOptionPane.OK_CANCEL_OPTION);
                    setVisible(false);
                    new InterviewSelection(mainFrame);
                }
            }
        });
        bestaetigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Interview interview : InterviewPlayer.getAllInterviews()) {
                    if (comboBoxInterview.getSelectedItem().equals(interview.getDescription())) {
                        if (comboBoxRole1.getSelectedItem().equals(comboBoxRole2.getSelectedItem())) {
                            JOptionPane.showMessageDialog(null, "Fehler: Gleicher Roboter", "Fehler", JOptionPane.OK_CANCEL_OPTION);
                            setVisible(false);
                            new InterviewSelection(mainFrame);
                        } else {
                            setVisible(false);
                            new InterviewPlayerView(mainFrame, interview);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Fehler: Interview nicht Gefunden", "Fehler", JOptionPane.OK_CANCEL_OPTION);
                        setVisible(false);
                        new InterviewSelection(mainFrame);
                    }
                }

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
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Rolle 2:");
        panel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Rolle 1:");
        panel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxRole1 = new JComboBox();
        panel.add(comboBoxRole1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxRole2 = new JComboBox();
        panel.add(comboBoxRole2, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bestaetigenButton = new JButton();
        bestaetigenButton.setText("Bestätigen");
        panel.add(bestaetigenButton, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Interview wählen:");
        panel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxInterview = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        comboBoxInterview.setModel(defaultComboBoxModel1);
        panel.add(comboBoxInterview, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
