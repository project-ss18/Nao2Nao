import controller.AppProperties;
import view.Menu;

import javax.swing.*;

public class Nao2Nao {
    public static void main(String[] args){


        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "UIManager", JOptionPane.ERROR_MESSAGE);
        }
        if(!(AppProperties.getApplicationMode() == null)) {
            if (AppProperties.getApplicationMode().equals("Test")) {
                JOptionPane.showMessageDialog(null, "Die Anwendung befindet sich im Test-Modus", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            }else {
            return;
        }

        new Menu();
    }


}
