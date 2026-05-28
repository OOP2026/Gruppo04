package controller;

import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {

    private List<Studente> studenti;
    private List<Docente> docenti;
    private List<Tesi> tesi;
    private List<Seduta_Di_Laurea> sedute;
    private List<Argomento_Tirocinio> argomenti;
    private List<Richiesta_Tirocinio> richieste;
    private List<Tirocinio_Esterno> tirociniEsterni;

    public Controller() {
        studenti = new ArrayList<>();
        docenti = new ArrayList<>();
        tesi = new ArrayList<>();
        sedute = new ArrayList<>();
        argomenti = new ArrayList<>();
        richieste = new ArrayList<>();
        tirociniEsterni = new ArrayList<>();

        // UTENTI DI TEST PER IL LOGIN
        studenti.add(new Studente(
                "Mario", "Rossi", "mario@email.it",
                "studente", "1234", "M123"
        ));

        docenti.add(new Docente(
                "Luigi", "Verdi", "luigi@email.it",
                "docente", "1234", false
        ));

        docenti.add(new Docente(
                "Anna", "Bianchi", "anna@email.it",
                "coordinatore", "1234", true
        ));
    }

    public Utente login(String login, String password) {
        for (Studente s : studenti) {
            if (s.getLogin().equals(login) && s.getPassword().equals(password)) {
                return s;
            }
        }

        for (Docente d : docenti) {
            if (d.getLogin().equals(login) && d.getPassword().equals(password)) {
                return d;
            }
        }

        return null;
    }

    public void aggiungiStudente(Studente studente) {
        studenti.add(studente);
    }

    public void aggiungiDocente(Docente docente) {
        docenti.add(docente);
    }

    public void caricaTesi(String file, Studente studente, Docente docente) {
        Tesi nuovaTesi = new Tesi(
                file,
                "IN ATTESA",
                new Date(),
                studente,
                docente
        );

        tesi.add(nuovaTesi);
        docente.aggiungiTesi(nuovaTesi);
    }

    public void approvaTesi(Tesi t) {
        t.setStatoApprovazione("APPROVATA");
    }

    public List<Studente> getStudenti() {
        return studenti;
    }

    public List<Docente> getDocenti() {
        return docenti;
    }

    public List<Tesi> getTesi() {
        return tesi;
    }

    public List<Seduta_Di_Laurea> getSedute() {
        return sedute;
    }

    public List<Argomento_Tirocinio> getArgomenti() {
        return argomenti;
    }

    public List<Richiesta_Tirocinio> getRichieste() {
        return richieste;
    }

    public List<Tirocinio_Esterno> getTirociniEsterni() {
        return tirociniEsterni;
    }
    public void creaSeduta(String data, String ora, String luogo, List<Tesi> tesiSeduta) {
        Seduta_Di_Laurea seduta = new Seduta_Di_Laurea(data, ora, luogo, tesiSeduta);
        sedute.add(seduta);
    }

    public void aggiungiArgomento(String titolo, String descrizione) {
        Argomento_Tirocinio argomento = new Argomento_Tirocinio(titolo, descrizione);
        argomenti.add(argomento);
    }
}