import com.formdev.flatlaf.FlatLightLaf;
import controller.Controller;
import gui.LoginFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // 🎨 UI MODERNA (FlatLaf)
        try {
            FlatLightLaf.setup();

            // opzionale: migliora rendering font UI
            UIManager.put("Button.arc", 12);
            UIManager.put("Component.arc", 12);
            UIManager.put("TextComponent.arc", 12);

        } catch (Exception e) {
            System.out.println("Errore avvio tema UI: " + e.getMessage());
        }

        // 🎮 Avvio Controller
        Controller controller = new Controller();

        // 🖥️ Login iniziale
        LoginFrame loginFrame = new LoginFrame(controller);
        loginFrame.setVisible(true);
    }
}