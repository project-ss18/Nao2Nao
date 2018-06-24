import controller.AppProperties;
import view.Menu;

import javax.swing.*;

public class GuiMain {
    public static void main(String[] args){


        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "UIManager", JOptionPane.ERROR_MESSAGE);
        }
        if (AppProperties.getApplicationMode().equals("Test")) {
            JOptionPane.showMessageDialog(null, "Die Anwendung befindet sich im Test-Modus", "Information", JOptionPane.INFORMATION_MESSAGE);
        }

        new Menu();
    }


}
