package gui;

import controller.Controller;
import model.Utente;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private Controller controller;

    private JTextField loginField;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton showPasswordButton;

    private boolean passwordVisible = false;

    public LoginFrame(Controller controller) {

        this.controller = controller;

        setTitle("Sistema Gestione Tesi");
        setSize(420, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setResizable(false);

        initUI();
    }

    private void initUI() {

        // 🌟 contenitore principale centrato
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(new Color(245, 247, 250));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("LOGIN", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        loginField = new JTextField(18);
        passwordField = new JPasswordField(18);

        loginButton = new JButton("Accedi");
        showPasswordButton = new JButton("👁");

        // 🔵 stile bottone login
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        // 👁 password toggle
        showPasswordButton.setPreferredSize(new Dimension(50, 25));
        showPasswordButton.addActionListener(e -> {
            if (passwordVisible) {
                passwordField.setEchoChar('•');
                passwordVisible = false;
            } else {
                passwordField.setEchoChar((char) 0);
                passwordVisible = true;
            }
        });

        loginButton.addActionListener(e -> doLogin());

        getRootPane().setDefaultButton(loginButton);

        // 🔷 layout

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        root.add(title, c);

        c.gridy++;
        root.add(loginField, c);

        c.gridy++;
        root.add(passwordField, c);

        c.gridy++;
        JPanel passPanel = new JPanel(new BorderLayout());
        passPanel.add(passwordField, BorderLayout.CENTER);
        passPanel.add(showPasswordButton, BorderLayout.EAST);
        root.add(passPanel, c);

        c.gridy++;
        root.add(loginButton, c);

        add(root);

        SwingUtilities.invokeLater(() -> loginField.requestFocusInWindow());
    }

    private void doLogin() {

        String login = loginField.getText();
        String password = new String(passwordField.getPassword());

        if (login.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserisci login e password");
            return;
        }

        Utente utente = controller.login(login, password);

        if (utente == null) {
            JOptionPane.showMessageDialog(this, "Credenziali errate");
            return;
        }

        MainFrame main = new MainFrame(controller, utente);
        main.setVisible(true);

        dispose();
    }
}