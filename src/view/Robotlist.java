package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.robot.Robot;

public class Robotlist {
    private JPanel panel;
    private JTable robotTable;
    private JButton buttonNewRobot;
    private JScrollPane robotTableScrollPane;
    private JButton löschenButton;
    private JButton zurückButton;
    private DefaultTableModel dTM;

    private static ArrayList<Robot> robotList = new ArrayList<Robot>();

    private String[] columnNames = new String[]{"ID", "Robotername", "IP-Adresse"};
    private String[][] rowData;

    Robotlist(JFrame frame) {
        refreshList();

        frame.setContentPane(panel);
        robotTable.repaint();

        frame.setPreferredSize(new Dimension(500, 300));
        frame.pack();
        frame.repaint();
        frame.setResizable(false);
        frame.setVisible(true);
        buttonNewRobot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewRobot(Robotlist.this);
            }
        });
        löschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = robotTable.getSelectedRow();
                if (selectedRow < 1) {
                    JOptionPane.showMessageDialog(null, "Fehler: Kein Roboter ausgewählt!", "Fehler", JOptionPane.OK_CANCEL_OPTION);
                    return;
                }

                robotList.remove(selectedRow);
                refreshList();
            }
        });
        zurückButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Menu();
            }
        });
    }

    public void refreshList() {
        rowData = new String[robotList.size()][];
        for (int i = 0; i < robotList.size(); i++) {
            rowData[i] = robotList.get(i).toStringArray();
        }
        robotTable = new JTable(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        robotTable.setPreferredScrollableViewportSize(new Dimension(300, 400));
        robotTable.setRowHeight(25);
        robotTableScrollPane.setViewportView(robotTable);
    }

    public void addRobot(String name, String IP) {
        try {
            robotList.add(new Robot(IP, name));
            refreshList();
        } catch (Exception e) {
            System.out.println(e);
        }
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
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        buttonNewRobot = new JButton();
        buttonNewRobot.setText("Neuer Roboter");
        panel.add(buttonNewRobot, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        robotTableScrollPane = new JScrollPane();
        panel.add(robotTableScrollPane, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 5, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 1, false));
        robotTableScrollPane.setBorder(BorderFactory.createTitledBorder("Angemeldete Roboter"));
        robotTable = new JTable();
        robotTableScrollPane.setViewportView(robotTable);
        löschenButton = new JButton();
        löschenButton.setText("Löschen");
        panel.add(löschenButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        zurückButton = new JButton();
        zurückButton.setText("Zurück");
        panel.add(zurückButton, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
