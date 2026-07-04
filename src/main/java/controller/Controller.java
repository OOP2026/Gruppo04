package controller;

import dao.*;
import model.*;
import postgresdao.*;

import java.util.Date;
import java.util.List;

/**
 * Controller centrale dell'applicazione.
 * Fa da intermediario tra GUI e livello DAO (database).
 * Gestisce tutte le operazioni principali del sistema.
 */
public class Controller {

    private final UtenteDAO utenteDAO;
    private final TesiDAO tesiDAO;
    private final SedutaDAO sedutaDAO;
    private final TirocinioDAO tirocinioDAO;

    /**
     * Inizializza i DAO con implementazione PostgreSQL.
     */
    public Controller() {
        this.utenteDAO = new PostgresUtenteDAO();
        this.tesiDAO = new PostgresTesiDAO(utenteDAO);
        this.sedutaDAO = new PostgresSedutaDAO(tesiDAO);
        this.tirocinioDAO = new PostgresTirocinioDAO(utenteDAO);
    }

    /** Esegue il login dell'utente */
    public Utente login(String login, String password) {
        return utenteDAO.login(login, password);
    }

    /** Registra uno studente */
    public boolean aggiungiStudente(Studente studente) {
        return utenteDAO.inserisciStudente(studente);
    }

    /** Registra un docente */
    public boolean aggiungiDocente(Docente docente) {
        return utenteDAO.inserisciDocente(docente);
    }

    /** Restituisce tutti gli studenti */
    public List<Studente> getStudenti() {
        return utenteDAO.getStudenti();
    }

    /** Restituisce tutti i docenti */
    public List<Docente> getDocenti() {
        return utenteDAO.getDocenti();
    }

    /** Carica una nuova tesi */
    public boolean caricaTesi(String file, Studente studente, Docente docente) {

        Tesi tesi = new Tesi(file, "IN ATTESA", new Date(), studente, docente);
        return tesiDAO.salvaTesi(tesi);
    }

    /** Approva una tesi */
    public boolean approvaTesi(Tesi tesi) {
        return tesiDAO.approvaTesi(tesi);
    }

    /** Lista tutte le tesi */
    public List<Tesi> getTesi() {
        return tesiDAO.getTesi();
    }

    /** Crea una seduta di laurea */
    public boolean creaSeduta(String data, String ora, String luogo, List<Tesi> tesi) {

        Seduta_Di_Laurea seduta = new Seduta_Di_Laurea(data, ora, luogo, tesi);
        return sedutaDAO.creaSeduta(seduta);
    }

    /** Lista sedute */
    public List<Seduta_Di_Laurea> getSedute() {
        return sedutaDAO.getSedute();
    }

    /** Aggiunge argomento tirocinio */
    public boolean aggiungiArgomento(String titolo, String descrizione) {

        return tirocinioDAO.inserisciArgomento(
                new Argomento_Tirocinio(titolo, descrizione)
        );
    }

    /** Invia richiesta tirocinio */
    public boolean inviaRichiestaTirocinio(Studente studente, Argomento_Tirocinio argomento) {

        return tirocinioDAO.inviaRichiesta(
                new Richiesta_Tirocinio("IN ATTESA", studente, argomento)
        );
    }

    /** Aggiunge tirocinio esterno */
    public boolean aggiungiTirocinioEsterno(String referente, String azienda, Argomento_Tirocinio argomento) {

        return tirocinioDAO.inserisciTirocinioEsterno(
                new Tirocinio_Esterno(referente, azienda, argomento)
        );
    }

    /** Lista argomenti tirocinio */
    public List<Argomento_Tirocinio> getArgomenti() {
        return tirocinioDAO.getArgomenti();
    }

    /** Lista richieste tirocinio */
    public List<Richiesta_Tirocinio> getRichieste() {
        return tirocinioDAO.getRichieste();
    }

    /** Lista tirocini esterni */
    public List<Tirocinio_Esterno> getTirociniEsterni() {
        return tirocinioDAO.getTirociniEsterni();
    }
}