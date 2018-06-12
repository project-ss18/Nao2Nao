package view;

import controller.InterviewLoader;
import javafx.scene.control.ComboBox;
import model.interview.Interview;
import model.robot.Robot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStoreException;


public class InterviewSelection extends JFrame {
    private JPanel panel;
    private JButton bestaetigenButton;

    private JComboBox comboBoxInterview;

    InterviewSelection(JFrame mainFrame) {

        if (InterviewLoader.getAllInterviews(true).size() == 0) {
            JOptionPane.showMessageDialog(null, "Fehler: Kein Interviews vorhanden", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        setContentPane(panel);
        setPreferredSize(new Dimension(300, 100));
        setSize(new Dimension(300, 200));

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        int dx = (ge.getCenterPoint().x - getSize().width / 2);
        int dy = (ge.getCenterPoint().y - getSize().height / 2);
        setLocation(dx, dy);

        for (Interview interview : InterviewLoader.getAllInterviews(false)) {
            comboBoxInterview.addItem(interview.getDescription());
        }

        setResizable(false);
        pack();
        setVisible(true);

        bestaetigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Interview i : Interview.getAllInterviews()) {
                    if (i.getDescription().equals(comboBoxInterview.getSelectedItem().toString())) {
                        if (InterviewLoader.checkSyntax(i.getFileName())) {
                            new RobotSelection(mainFrame, i);
                            setVisible(false);
                            //new InterviewPlayer(mainFrame, i, Robot.getRobotList());
                        }
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
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        bestaetigenButton = new JButton();
        bestaetigenButton.setText("Bestätigen");
        panel.add(bestaetigenButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Interview wählen:");
        panel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
