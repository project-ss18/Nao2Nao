package view;

import controller.AppProperties;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Menu {
    //------------------------Attribute------------------------\\
    private JFrame frame;
    private JPanel panel;
    private JButton roboterlisteButton;
    private JButton interviewlisteButton;
    private JButton hilfeButton;
    private JButton schliessenButton;
    private Dimension frameDimension;

    //-----------------------Konstruktor-----------------------\\
    public Menu() {

        frame = new JFrame("Nao2Nao");
        roboterlisteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Robotlist(frame);
            }
        });
        interviewlisteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new InterviewList(frame);
            }
        });

        frameDimension = new Dimension(250, 300);
        frame.setPreferredSize(frameDimension);
        frame.setSize(frameDimension);

        try {
            //https://www.iconspng.com/image/90792/head-of-nao-robot
            ImageIcon imageIcon = new ImageIcon(new ImageIcon("icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
            frame.setIconImage(ImageIO.read(new File("res/head-of-nao-robot.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        frame.setContentPane(panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centerFrame();
        frame.repaint();
        frame.pack();
        frame.setVisible(true);
        //__Look_AND_Feel
        hilfeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Help();
            }
        });
        schliessenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    //-------------------------Methoden-------------------------\\
    private void centerFrame() {
        Dimension windowSize = this.frame.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = (centerPoint.x - windowSize.width / 2);
        int dy = (centerPoint.y - windowSize.height / 2);
        this.frame.setLocation(dx, dy);
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
        panel.setLayout(new GridBagLayout());
        roboterlisteButton = new JButton();
        roboterlisteButton.setMaximumSize(new Dimension(99, 35));
        roboterlisteButton.setPreferredSize(new Dimension(99, 35));
        roboterlisteButton.setText("Roboterliste");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(roboterlisteButton, gbc);
        hilfeButton = new JButton();
        hilfeButton.setPreferredSize(new Dimension(115, 35));
        hilfeButton.setText("Hilfe");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(hilfeButton, gbc);
        schliessenButton = new JButton();
        schliessenButton.setPreferredSize(new Dimension(61, 35));
        schliessenButton.setText("Exit");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(schliessenButton, gbc);
        interviewlisteButton = new JButton();
        interviewlisteButton.setMaximumSize(new Dimension(105, 35));
        interviewlisteButton.setPreferredSize(new Dimension(105, 35));
        interviewlisteButton.setText("Interview einleiten");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(interviewlisteButton, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(spacer3, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}