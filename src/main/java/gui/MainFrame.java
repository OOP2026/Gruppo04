package gui;

import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private Controller controller;
    private Utente utente;

    public MainFrame(
            Controller controller,
            Utente utente
    ) {

        this.controller = controller;
        this.utente = utente;

        setTitle("Sistema Gestione Tesi");

        setSize(900,650);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        JButton logoutButton =
                new JButton("Logout");

        logoutButton.addActionListener(
                e -> logout()
        );

        JPanel topPanel =
                new JPanel(new FlowLayout(
                        FlowLayout.RIGHT));

        topPanel.add(logoutButton);

        JPanel contentPanel = new JPanel(
                new BorderLayout()
        );

        if(utente instanceof Studente) {

            contentPanel.add(
                    new StudentePanel(
                            controller,
                            (Studente) utente
                    ),
                    BorderLayout.CENTER
            );

        }

        else if(utente instanceof Docente) {

            Docente docente =
                    (Docente) utente;

            if(docente.isCoordinatore()) {

                contentPanel.add(
                        new CoordinatorePanel(
                                controller,
                                docente
                        ),
                        BorderLayout.CENTER
                );

            }

            else {

                contentPanel.add(
                        new DocentePanel(
                                controller,
                                docente
                        ),
                        BorderLayout.CENTER
                );

            }

        }

        setLayout(new BorderLayout());

        add(topPanel,BorderLayout.NORTH);

        add(contentPanel,BorderLayout.CENTER);
    }

    private void logout() {

        int scelta =
                JOptionPane.showConfirmDialog(
                        this,
                        "Vuoi effettuare il logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if(scelta == JOptionPane.YES_OPTION) {

            LoginFrame login =
                    new LoginFrame(controller);

            login.setVisible(true);

            dispose();

        }

    }

}