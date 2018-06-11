package view;


import javax.swing.*;
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
    private JButton pingButton;



    private String[] columnNames = new String[]{"ID", "Robotername", "IP-Adresse"};
    private String[][] rowData;

    public Robotlist(JFrame frame) {
        refreshList();

        frame.setContentPane(panel);
        robotTable.repaint();

        frame.setPreferredSize(new Dimension(500, 300));
        frame.setSize(new Dimension(500, 300));

        Dimension windowSize = frame.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = (centerPoint.x - windowSize.width / 2);
        int dy = (centerPoint.y - windowSize.height / 2);
        frame.setLocation(dx, dy);

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
                try {
                    int selectedRow = robotTable.getSelectedRow();
                    String tempID = (String) robotTable.getValueAt(selectedRow, 0);
                    for (Robot r : Robot.getRobotList()) {
                        if (tempID.equals(String.valueOf(r.get_ID()))) {
                            Robot.getRobotList().remove(r);
                            refreshList();
                            return;
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex + "\n RobotList@löschenButton.actionPerformed");
                    JOptionPane.showMessageDialog(null, "Fehler: Kein Roboter ausgewählt!", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        zurückButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Menu();
            }
        });
        pingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow = robotTable.getSelectedRow();
                    String tempID = (String) robotTable.getValueAt(selectedRow, 0);
                    for (Robot r : Robot.getRobotList()) {
                        if (tempID.equals(String.valueOf(r.get_ID()))) {
                            r.ping();
                            return;
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex + "\n RobotList@löschenButton.actionPerformed");
                    JOptionPane.showMessageDialog(null, "Fehler: Kein Roboter ausgewählt!", "Fehler", JOptionPane.OK_CANCEL_OPTION);
                }
            }
        });
    }

    public void refreshList() {
        rowData = new String[Robot.getRobotList().size()][];
        for (int i = 0; i < Robot.getRobotList().size(); i++) {
            rowData[i] = Robot.getRobotList().get(i).toStringArray();
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
            Robot.getRobotList().add(new Robot(IP, name));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            for (Robot r : Robot.getRobotList()) {
                if (r.getName().equals(name)) Robot.getRobotList().remove(r);
            }
            JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler", JOptionPane.OK_CANCEL_OPTION);
        }
        refreshList();
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
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        buttonNewRobot = new JButton();
        buttonNewRobot.setText("Neuer Roboter");
        panel.add(buttonNewRobot, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        robotTableScrollPane = new JScrollPane();
        panel.add(robotTableScrollPane, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 6, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 1, false));
        robotTableScrollPane.setBorder(BorderFactory.createTitledBorder("Angemeldete Roboter"));
        robotTable = new JTable();
        robotTableScrollPane.setViewportView(robotTable);
        löschenButton = new JButton();
        löschenButton.setText("Löschen");
        panel.add(löschenButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        zurückButton = new JButton();
        zurückButton.setText("Zurück");
        panel.add(zurückButton, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pingButton = new JButton();
        pingButton.setText("Ping");
        panel.add(pingButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
