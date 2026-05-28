package gui;

import controller.Controller;
import model.Docente;
import model.Tesi;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DocentePanel extends JPanel {

    private Controller controller;
    private Docente docente;

    public DocentePanel(Controller controller, Docente docente) {

        this.controller = controller;
        this.docente = docente;

        setLayout(new BorderLayout());

        JLabel titolo = new JLabel(
                "Area Docente - Benvenuto "
                        + docente.getNome() + " "
                        + docente.getCognome(),
                SwingConstants.CENTER
        );

        JButton visualizzaTesiButton =
                new JButton("Visualizza Tesi");

        JButton approvaTesiButton =
                new JButton("Approva Tesi");

        JPanel bottoniPanel = new JPanel();

        bottoniPanel.add(visualizzaTesiButton);
        bottoniPanel.add(approvaTesiButton);

        add(titolo, BorderLayout.NORTH);
        add(bottoniPanel, BorderLayout.CENTER);

        visualizzaTesiButton.addActionListener(
                e -> visualizzaTesi()
        );

        approvaTesiButton.addActionListener(
                e -> approvaTesi()
        );
    }

    private void visualizzaTesi() {

        List<Tesi> listaTesi = controller.getTesi();

        if (listaTesi.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nessuna tesi presente."
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

            testo.append("File: ")
                    .append(t.getFileTesi())
                    .append("\n");

            testo.append("Stato: ")
                    .append(t.getStatoApprovazione())
                    .append("\n\n");
        }

        JOptionPane.showMessageDialog(
                this,
                testo.toString()
        );
    }

    private void approvaTesi() {

        List<Tesi> listaTesi = controller.getTesi();

        if (listaTesi.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Non ci sono tesi da approvare."
            );

            return;
        }

        Tesi primaTesi = listaTesi.get(0);

        controller.approvaTesi(primaTesi);

        JOptionPane.showMessageDialog(
                this,
                "Tesi approvata correttamente."
        );
    }
}