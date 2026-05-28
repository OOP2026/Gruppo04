package gui;

import controller.Controller;
import model.Utente;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private Controller controller;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame(Controller controller) {

        this.controller = controller;

        setTitle("Login");
        setSize(420,280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,2,10,10));

        JLabel usernameLabel = new JLabel("Username:");

        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");

        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        JCheckBox mostraPassword = new JCheckBox("Mostra Password");

        panel.add(usernameLabel);
        panel.add(usernameField);

        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(mostraPassword);
        panel.add(new JLabel());

        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);

        mostraPassword.addActionListener(e -> {

            if(mostraPassword.isSelected()) {
                passwordField.setEchoChar((char)0);
            }
            else {
                passwordField.setEchoChar('•');
            }

        });

        loginButton.addActionListener(e -> login());
    }

    private void login() {

        String username = usernameField.getText();

        String password =
                new String(passwordField.getPassword());

        Utente utente =
                controller.login(username,password);

        if(utente != null) {

            MainFrame main =
                    new MainFrame(controller,utente);

            main.setVisible(true);

            dispose();

        }
        else {

            JOptionPane.showMessageDialog(
                    this,
                    "Credenziali non valide."
            );

        }

    }

}