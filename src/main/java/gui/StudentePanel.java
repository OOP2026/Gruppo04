package gui;

import controller.Controller;
import model.Argomento_Tirocinio;
import model.Docente;
import model.Studente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentePanel extends JPanel {

    private Controller controller;
    private Studente studente;

    public StudentePanel(Controller controller, Studente studente) {

        this.controller = controller;
        this.studente = studente;

        setLayout(new BorderLayout());

        JLabel title = new JLabel(
                "Area Studente - " + studente.getNome(),
                SwingConstants.CENTER
        );
        title.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel buttons = new JPanel(new FlowLayout());

        JButton tesi = new JButton("Carica Tesi");
        JButton tirocinio = new JButton("Richiedi Tirocinio");

        tesi.setFocusPainted(false);
        tirocinio.setFocusPainted(false);

        buttons.add(tesi);
        buttons.add(tirocinio);

        add(title, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);

        tesi.addActionListener(e -> caricaTesi());
        tirocinio.addActionListener(e -> richiediTirocinio());
    }

    private void caricaTesi() {

        String file = JOptionPane.showInputDialog(this, "Nome file tesi:");
        if (file == null || file.isBlank()) return;

        List<Docente> docenti = controller.getDocenti();
        if (docenti.isEmpty()) return;

        controller.caricaTesi(file, studente, docenti.get(0));

        JOptionPane.showMessageDialog(this, "Tesi caricata");
    }

    private void richiediTirocinio() {

        List<Argomento_Tirocinio> argomenti = controller.getArgomenti();
        if (argomenti.isEmpty()) return;

        controller.inviaRichiestaTirocinio(studente, argomenti.get(0));

        JOptionPane.showMessageDialog(this, "Richiesta inviata");
    }
}