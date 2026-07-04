package gui;

import controller.Controller;
import model.Docente;
import model.Tesi;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Area coordinatore:
 * - gestione tesi (visualizzazione e filtro)
 * - creazione sedute di laurea
 * - gestione argomenti tirocinio
 */
public class CoordinatorePanel extends JPanel {

    private Controller controller;
    private Docente coordinatore;

    private DefaultListModel<Tesi> modelList;
    private JList<Tesi> listaTesi;

    public CoordinatorePanel(Controller controller, Docente coordinatore) {

        this.controller = controller;
        this.coordinatore = coordinatore;

        setLayout(new BorderLayout());

        JLabel titolo = new JLabel(
                "Area Coordinatore - " + coordinatore.getNome() + " " + coordinatore.getCognome(),
                SwingConstants.CENTER
        );

        JButton btnTesi = new JButton("Gestisci Tesi");
        JButton btnSeduta = new JButton("Crea Seduta");
        JButton btnTirocini = new JButton("Gestisci Tirocini");

        JPanel top = new JPanel();
        top.add(btnTesi);
        top.add(btnSeduta);
        top.add(btnTirocini);

        add(titolo, BorderLayout.NORTH);
        add(top, BorderLayout.CENTER);

        btnTesi.addActionListener(e -> apriGestioneTesi());
        btnSeduta.addActionListener(e -> creaSeduta());
        btnTirocini.addActionListener(e -> gestisciTirocini());
    }

    private void apriGestioneTesi() {

        JDialog dialog = new JDialog();
        dialog.setTitle("Gestione Tesi");
        dialog.setSize(700, 400);
        dialog.setLocationRelativeTo(this);

        modelList = new DefaultListModel<>();
        listaTesi = new JList<>(modelList);

        JButton aggiorna = new JButton("Aggiorna");
        JButton soloAttesa = new JButton("Solo IN ATTESA");

        aggiorna.addActionListener(e -> caricaTesi(null));
        soloAttesa.addActionListener(e -> caricaTesi("IN ATTESA"));

        JPanel bottoni = new JPanel();
        bottoni.add(aggiorna);
        bottoni.add(soloAttesa);

        dialog.setLayout(new BorderLayout());
        dialog.add(new JScrollPane(listaTesi), BorderLayout.CENTER);
        dialog.add(bottoni, BorderLayout.SOUTH);

        caricaTesi(null);

        dialog.setVisible(true);
    }

    private void caricaTesi(String filtro) {

        modelList.clear();

        List<Tesi> lista = controller.getTesi();

        for (Tesi t : lista) {

            if (filtro == null || t.getStatoApprovazione().equalsIgnoreCase(filtro)) {
                modelList.addElement(t);
            }
        }
    }

    private void creaSeduta() {

        List<Tesi> listaTesi = controller.getTesi();

        if (listaTesi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nessuna tesi disponibile.");
            return;
        }

        String data = JOptionPane.showInputDialog("Data seduta:");
        String ora = JOptionPane.showInputDialog("Ora seduta:");
        String luogo = JOptionPane.showInputDialog("Luogo seduta:");

        if (data == null || ora == null || luogo == null) return;

        controller.creaSeduta(data, ora, luogo, listaTesi);

        JOptionPane.showMessageDialog(this, "Seduta creata correttamente.");
    }

    private void gestisciTirocini() {
        JOptionPane.showMessageDialog(this, "Funzione già gestita nel sistema tirocinio.");
    }
}