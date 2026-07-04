package gui;

import controller.Controller;
import model.Tesi;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Pannello dedicato alla gestione globale delle tesi.
 */
public class GestioneTesiPanel extends JPanel {

    private Controller controller;
    private DefaultListModel<Tesi> model;
    private JList<Tesi> list;

    public GestioneTesiPanel(Controller controller) {

        this.controller = controller;

        setLayout(new BorderLayout());

        JLabel titolo = new JLabel("Gestione Tesi", SwingConstants.CENTER);

        model = new DefaultListModel<>();
        list = new JList<>(model);

        JButton aggiorna = new JButton("Aggiorna");

        aggiorna.addActionListener(e -> load());

        add(titolo, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(aggiorna, BorderLayout.SOUTH);

        load();
    }

    private void load() {

        model.clear();

        List<Tesi> tesi = controller.getTesi();

        for (Tesi t : tesi) {
            model.addElement(t);
        }
    }
}