package gui;

import controller.Controller;
import model.Docente;
import model.Tesi;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Pannello docente: visualizza e approva tesi.
 */
public class DocentePanel extends JPanel {

    private Controller controller;
    private Docente docente;

    private JList<Tesi> listaTesiJList;
    private DefaultListModel<Tesi> listModel;

    public DocentePanel(Controller controller, Docente docente) {

        this.controller = controller;
        this.docente = docente;

        setLayout(new BorderLayout());

        JLabel titolo = new JLabel(
                "Area Docente - Benvenuto " +
                        docente.getNome() + " " +
                        docente.getCognome(),
                SwingConstants.CENTER
        );

        JButton aggiornaButton = new JButton("Aggiorna lista tesi");
        JButton approvaButton = new JButton("Approva selezionata");

        JPanel top = new JPanel();
        top.add(aggiornaButton);
        top.add(approvaButton);

        listModel = new DefaultListModel<>();
        listaTesiJList = new JList<>(listModel);
        listaTesiJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(listaTesiJList);

        add(titolo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(top, BorderLayout.SOUTH);

        aggiornaButton.addActionListener(e -> caricaTesi());
        approvaButton.addActionListener(e -> approvaTesi());
    }

    private void caricaTesi() {

        listModel.clear();

        List<Tesi> tesiList = controller.getTesi();

        if (tesiList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nessuna tesi presente.");
            return;
        }

        for (Tesi t : tesiList) {
            listModel.addElement(t);
        }
    }

    private void approvaTesi() {

        Tesi selezionata = listaTesiJList.getSelectedValue();

        if (selezionata == null) {
            JOptionPane.showMessageDialog(this, "Seleziona una tesi!");
            return;
        }

        controller.approvaTesi(selezionata);

        JOptionPane.showMessageDialog(this, "Tesi approvata correttamente.");

        caricaTesi();
    }
}