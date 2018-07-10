package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.AppProperties;
import model.robot.Robot;


public class Robotlist {

    //------------------------Attribute------------------------\\
    private JPanel panel;
    private JTable robotTable;
    private JButton buttonNewRobot;
    private JScrollPane robotTableScrollPane;
    private JButton löschenButton;
    private JButton zurückButton;
    private JButton pingButton;
    private JButton insertPresetted;
    private JButton robotReset;
    private JLabel loadingLable;
    private JFrame frame;

    //------------------------Variablen------------------------\\
    private String[] columnNames = new String[]{"ID", "Robotername", "IP-Adresse"};
    private String[][] rowData;

    //-----------------------Konstruktor-----------------------\\
    public Robotlist(JFrame frame) {
        refreshList();
        this.frame = frame;

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
                new Thread(new NewRobot(Robotlist.this, false)).start();
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
                    JOptionPane.showMessageDialog(null, "Kein Roboter ausgewählt!", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        insertPresetted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new NewRobot(Robotlist.this, true)).start();
            }
        });
        robotReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow = robotTable.getSelectedRow();
                    String tempID = (String) robotTable.getValueAt(selectedRow, 0);
                    for (Robot r : Robot.getRobotList()) {
                        if (tempID.equals(String.valueOf(r.get_ID()))) {
                            r.reset(r);
                            return;
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex + "\n RobotList@löschenButton.actionPerformed");
                    JOptionPane.showMessageDialog(null, "Kein Roboter ausgewählt!", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    //-------------------------Methoden-------------------------\\
    void setBackButton(boolean state) {
        if (!state) {
            loadingLable.setText("<html><font color='red'>Bitte warten!<br>Lade Roboter...</font></html>");
            loadingLable.setVisible(true);
        }
        zurückButton.setEnabled(state);
        insertPresetted.setEnabled(state);
        buttonNewRobot.setEnabled(state);
        if (state) loadingLable.setVisible(false);
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

    void addRobot(String name, String IP) {
        Robot robot;
        try {
            robot = new Robot(IP, name);
            JOptionPane.showMessageDialog(null, "Roboter mit " + robot.getIPAdress() + " eingerichtet!", "Roboter angelegt", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Robot.getRobotList().add(robot);
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
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 2, new Insets(0, 0, 0, 0), -1, -1));
        buttonNewRobot = new JButton();
        buttonNewRobot.setText("Neuer Roboter");
        panel.add(buttonNewRobot, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        robotTableScrollPane = new JScrollPane();
        panel.add(robotTableScrollPane, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 10, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 1, false));
        robotTableScrollPane.setBorder(BorderFactory.createTitledBorder("Angemeldete Roboter"));
        robotTable = new JTable();
        robotTableScrollPane.setViewportView(robotTable);
        löschenButton = new JButton();
        löschenButton.setText("Löschen");
        panel.add(löschenButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        zurückButton = new JButton();
        zurückButton.setText("Zurück");
        panel.add(zurückButton, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pingButton = new JButton();
        pingButton.setText("Ping");
        panel.add(pingButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        insertPresetted = new JButton();
        insertPresetted.setText("<html>\nVordefinier-\n<br>\nte Roboter\n<br>\nImportieren\n</html>");
        panel.add(insertPresetted, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        robotReset = new JButton();
        robotReset.setText("Reset Roboter");
        panel.add(robotReset, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loadingLable = new JLabel();
        loadingLable.setText("");
        panel.add(loadingLable, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
