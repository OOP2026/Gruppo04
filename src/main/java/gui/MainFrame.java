package gui;

import controller.Controller;
import model.Docente;
import model.Utente;
import model.Studente;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private Controller controller;
    private Utente utente;

    public MainFrame(Controller controller, Utente utente) {

        this.controller = controller;
        this.utente = utente;

        setTitle("Dashboard Sistema Tesi");
        setSize(1000, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // TOP BAR
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logout = new JButton("Logout");
        logout.setFocusPainted(false);
        topBar.add(logout);

        logout.addActionListener(e -> logout());

        // BODY
        JPanel body = new JPanel(new BorderLayout());

        if (utente instanceof Studente) {
            body.add(new StudentePanel(controller, (Studente) utente), BorderLayout.CENTER);
        }
        else if (utente instanceof Docente) {

            Docente d = (Docente) utente;

            if (d.isCoordinatore()) {
                body.add(new CoordinatorePanel(controller, d), BorderLayout.CENTER);
            } else {
                body.add(new DocentePanel(controller, d), BorderLayout.CENTER);
            }
        }

        add(topBar, BorderLayout.NORTH);
        add(body, BorderLayout.CENTER);
    }

    private void logout() {

        int res = JOptionPane.showConfirmDialog(
                this,
                "Confermi logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (res == JOptionPane.YES_OPTION) {
            new LoginFrame(controller).setVisible(true);
            dispose();
        }
    }
}