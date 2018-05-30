package view;

import controller.InterviewPlayer;
import model.interview.Interview;
import model.robot.Robot;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.channels.FileChannel;

public class InterviewList {
    private JButton buttonTest;
    private JPanel panel;
    private JButton zurueckButton;
    private JButton openFileButton;
    private JLabel messageLabel;
    private JTable interviewTable;
    private JScrollPane interviewScrollPane;
    private JButton interviewEntfernenButton;
    private JButton interviewAbspielenButton;
    private JButton aktualisierenButton;

    //private static ArrayList<Interview> interviewArrayList = new ArrayList<Interview>();
    private static List<InterviewPlayer> interviewList = InterviewPlayer.getAllInterviews();
    private static JFileChooser openFileChooser;
    private static final String COPY_FILE_TO = "C:/Users/Manu/Desktop/Nao2Nao/res/manu.xml";
    private File target = new File(COPY_FILE_TO);
    //File source = new File("C:/Users/Manu/Desktop/manu.xml");

    private String[] columnNames = new String[]{"ID", "Interviewname", "Anzahl Fragen", "Anzahl Roboter"};
    private String[][] rowData;


    public InterviewList(JFrame frame) {

        refreshList();
        frame.setContentPane(panel);
        frame.setPreferredSize(new Dimension(700, 400));
        frame.pack();
        frame.repaint();
        frame.setResizable(false);
        frame.setVisible(true);

        openFileChooser = new JFileChooser();
        openFileChooser.setFileFilter(new FileNameExtensionFilter("XML-Dateien", "xml"));
        //openFileChooser.setCurrentDirectory(new File("./res/"));


        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Menu();
            }
        });

        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = openFileChooser.showOpenDialog(openFileButton);
                if (returnValue == JFileChooser.APPROVE_OPTION) {

                    File currentXML = openFileChooser.getSelectedFile();
                    messageLabel.setText("XML file successfully loaded!");
                    try {
                        copyFileUsingChannel(openFileChooser.getSelectedFile(), target);
                        refreshList();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    messageLabel.setText("No file chosen!");
                }
            }
        });

    }

    public void refreshList() {
        interviewList = InterviewPlayer.getAllInterviews();
        rowData = new String[interviewList.size()][];
        for (int i = 0; i < interviewList.size(); i++) {
            rowData[i] = interviewList.get(i).interview.toStringArray();
        }
        interviewTable = new JTable(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        interviewTable.setPreferredScrollableViewportSize(new Dimension(300, 400));
        interviewTable.setRowHeight(25);
        interviewScrollPane.setViewportView(interviewTable);
    }


    private static void copyFileUsingChannel(File source, File dest) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } finally {
            sourceChannel.close();
            destChannel.close();
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
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(8, 3, new Insets(0, 0, 0, 0), -1, -1));
        interviewScrollPane = new JScrollPane();
        panel.add(interviewScrollPane, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 7, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        interviewTable = new JTable();
        interviewScrollPane.setViewportView(interviewTable);
        messageLabel = new JLabel();
        messageLabel.setText("");
        panel.add(messageLabel, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        zurueckButton = new JButton();
        zurueckButton.setText("Zurück");
        panel.add(zurueckButton, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        aktualisierenButton = new JButton();
        aktualisierenButton.setText("Aktualisieren");
        panel.add(aktualisierenButton, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        openFileButton = new JButton();
        openFileButton.setText("Interview öffnen...");
        panel.add(openFileButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        interviewAbspielenButton = new JButton();
        interviewAbspielenButton.setText("Interview Abspielen");
        panel.add(interviewAbspielenButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        interviewEntfernenButton = new JButton();
        interviewEntfernenButton.setText("Interview entfernen");
        panel.add(interviewEntfernenButton, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        messageLabel.setLabelFor(interviewScrollPane);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
