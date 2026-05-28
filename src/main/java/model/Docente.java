package model;

import java.util.ArrayList;
import java.util.List;

public class Docente extends Utente {
    private boolean isCoordinatore;
    private List<Tesi> tesiSeguite;

    public Docente(String nome, String cognome,
                   String email, String login,
                   String password, boolean isCoordinatore) {

        super(nome, cognome, email, login, password);

        this.isCoordinatore = isCoordinatore;
        this.tesiSeguite = new ArrayList<>();
    }

    public boolean isCoordinatore() {
        return isCoordinatore;
    }

    public List<Tesi> getTesiSeguite() {
        return tesiSeguite;
    }

    public void aggiungiTesi(Tesi tesi) {
        tesiSeguite.add(tesi);
    }
}