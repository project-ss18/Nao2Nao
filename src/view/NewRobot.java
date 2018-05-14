package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewRobot extends JFrame {
    private JTextField textFieldRobotName;
    private JTextField textFieldIP;
    private JButton bestätigenButton;
    private JPanel panel;

    NewRobot() {
        setPreferredSize(new Dimension(200, 150));
        setContentPane(panel);
        pack();
        setVisible(true);

        bestätigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


}
