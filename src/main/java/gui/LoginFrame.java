package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private Controller controller;

    private JTextField txtLogin;
    private JPasswordField txtPassword;

    private JButton btnLogin;
    private JCheckBox chkMostraPassword;

    public LoginFrame(Controller controller) {

        this.controller = controller;

        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // PANEL
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        // LABEL
        JLabel lblLogin = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");

        // CAMPI
        txtLogin = new JTextField();
        txtPassword = new JPasswordField();

        // BOTTONE LOGIN
        btnLogin = new JButton("Login");

        // CHECKBOX MOSTRA PASSWORD
        chkMostraPassword = new JCheckBox("Mostra password");

        // COMPONENTI
        panel.add(lblLogin);
        panel.add(txtLogin);

        panel.add(lblPassword);
        panel.add(txtPassword);

        panel.add(new JLabel(""));
        panel.add(chkMostraPassword);

        panel.add(new JLabel(""));
        panel.add(btnLogin);

        // AGGIUNTA PANEL
        add(panel);

        // EVENTO LOGIN
        btnLogin.addActionListener(e -> eseguiLogin());

        // EVENTO MOSTRA PASSWORD
        chkMostraPassword.addActionListener(e -> mostraPassword());
    }

    private void eseguiLogin() {

        String login = txtLogin.getText();

        String password =
                new String(txtPassword.getPassword());

        JOptionPane.showMessageDialog(
                this,
                "Login inserito:\n" +
                        login + "\n" + password
        );
    }

    private void mostraPassword() {

        if (chkMostraPassword.isSelected()) {

            txtPassword.setEchoChar((char) 0);

        } else {

            txtPassword.setEchoChar('*');
        }
    }
}