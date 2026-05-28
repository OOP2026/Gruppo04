package gui;

import controller.Controller;
import model.Docente;
import model.Studente;

import javax.swing.*;
import java.awt.*;

public class StudentePanel extends JPanel {

    private Controller controller;
    private Studente studente;

    public StudentePanel(Controller controller, Studente studente) {
        this.controller = controller;
        this.studente = studente;

        setLayout(new BorderLayout());

        JLabel titolo = new JLabel(
                "Area Studente - Benvenuto " + studente.getNome() + " " + studente.getCognome(),
                SwingConstants.CENTER
        );

        JButton caricaTesiButton = new JButton("Carica Tesi");
        JButton richiestaTirocinioButton = new JButton("Richiedi Tirocinio");

        JPanel bottoniPanel = new JPanel();
        bottoniPanel.add(caricaTesiButton);
        bottoniPanel.add(richiestaTirocinioButton);

        add(titolo, BorderLayout.NORTH);
        add(bottoniPanel, BorderLayout.CENTER);

        caricaTesiButton.addActionListener(e -> caricaTesi());
        richiestaTirocinioButton.addActionListener(e -> richiediTirocinio());
    }

    private void caricaTesi() {
        String nomeFile = JOptionPane.showInputDialog(
                this,
                "Inserisci nome file tesi:"
        );

        if (nomeFile == null || nomeFile.trim().isEmpty()) {
            return;
        }

        if (controller.getDocenti().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nessun docente disponibile.");
            return;
        }

        Docente docente = controller.getDocenti().get(0);

        controller.caricaTesi(nomeFile, studente, docente);

        JOptionPane.showMessageDialog(
                this,
                "Tesi caricata correttamente.\nDocente assegnato: "
                        + docente.getNome() + " " + docente.getCognome()
        );
    }

    private void richiediTirocinio() {
        JOptionPane.showMessageDialog(
                this,
                "Richiesta tirocinio inviata correttamente."
        );
    }
}