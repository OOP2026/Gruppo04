package gui;

import controller.Controller;
import model.Docente;
import model.Tesi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CoordinatorePanel extends JPanel {

    private Controller controller;
    private Docente coordinatore;

    public CoordinatorePanel(Controller controller, Docente coordinatore) {
        this.controller = controller;
        this.coordinatore = coordinatore;

        setLayout(new BorderLayout());

        JLabel titolo = new JLabel(
                "Area Coordinatore - Benvenuto "
                        + coordinatore.getNome() + " "
                        + coordinatore.getCognome(),
                SwingConstants.CENTER
        );

        JButton gestisciTesiButton = new JButton("Gestisci Tesi");
        JButton creaSedutaButton = new JButton("Crea Seduta di Laurea");
        JButton gestisciTirociniButton = new JButton("Gestisci Tirocini");

        JPanel bottoniPanel = new JPanel();
        bottoniPanel.add(gestisciTesiButton);
        bottoniPanel.add(creaSedutaButton);
        bottoniPanel.add(gestisciTirociniButton);

        add(titolo, BorderLayout.NORTH);
        add(bottoniPanel, BorderLayout.CENTER);

        gestisciTesiButton.addActionListener(e -> gestisciTesi());
        creaSedutaButton.addActionListener(e -> creaSeduta());
        gestisciTirociniButton.addActionListener(e -> gestisciTirocini());
    }

    private void gestisciTesi() {
        List<Tesi> listaTesi = controller.getTesi();

        if (listaTesi.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nessuna tesi presente nel sistema."
            );
            return;
        }

        StringBuilder testo = new StringBuilder();

        for (Tesi t : listaTesi) {
            testo.append("Studente: ")
                    .append(t.getStudente().getNome())
                    .append(" ")
                    .append(t.getStudente().getCognome())
                    .append("\n");

            testo.append("Docente: ")
                    .append(t.getDocente().getNome())
                    .append(" ")
                    .append(t.getDocente().getCognome())
                    .append("\n");

            testo.append("File: ")
                    .append(t.getFileTesi())
                    .append("\n");

            testo.append("Stato: ")
                    .append(t.getStatoApprovazione())
                    .append("\n\n");
        }

        JOptionPane.showMessageDialog(
                this,
                testo.toString(),
                "Elenco Tesi",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void creaSeduta() {
        List<Tesi> listaTesi = controller.getTesi();

        if (listaTesi.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Non ci sono tesi da inserire in una seduta."
            );
            return;
        }

        String data = JOptionPane.showInputDialog(
                this,
                "Inserisci data seduta:"
        );

        if (data == null || data.trim().isEmpty()) {
            return;
        }

        String ora = JOptionPane.showInputDialog(
                this,
                "Inserisci ora seduta:"
        );

        if (ora == null || ora.trim().isEmpty()) {
            return;
        }

        String luogo = JOptionPane.showInputDialog(
                this,
                "Inserisci luogo seduta:"
        );

        if (luogo == null || luogo.trim().isEmpty()) {
            return;
        }

        List<Tesi> tesiSeduta = new ArrayList<>(listaTesi);

        controller.creaSeduta(data, ora, luogo, tesiSeduta);

        JOptionPane.showMessageDialog(
                this,
                "Seduta di laurea creata correttamente.\n"
                        + "Data: " + data + "\n"
                        + "Ora: " + ora + "\n"
                        + "Luogo: " + luogo + "\n"
                        + "Numero tesi inserite: " + tesiSeduta.size()
        );
    }

    private void gestisciTirocini() {
        String titolo = JOptionPane.showInputDialog(
                this,
                "Inserisci titolo argomento tirocinio:"
        );

        if (titolo == null || titolo.trim().isEmpty()) {
            return;
        }

        String descrizione = JOptionPane.showInputDialog(
                this,
                "Inserisci descrizione argomento:"
        );

        if (descrizione == null || descrizione.trim().isEmpty()) {
            return;
        }

        controller.aggiungiArgomento(titolo, descrizione);

        JOptionPane.showMessageDialog(
                this,
                "Argomento tirocinio aggiunto correttamente.\n"
                        + "Titolo: " + titolo
        );
    }
}