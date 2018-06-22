import controller.AppProperties;
import view.Menu;

import javax.swing.*;

public class GuiMain {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        if (AppProperties.getApplicationMode().equals("Test")) {
            JOptionPane.showMessageDialog(null, "Die Anwendung befindet sich im Test-Modus", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        Menu m = new Menu();
    }


}
