package dao;

import model.Docente;
import model.Studente;
import model.Utente;

import java.util.List;

/**
 * Interfaccia DAO per la gestione degli utenti.
 * Definisce le operazioni di accesso ai dati relativi a studenti e docenti.
 */
public interface UtenteDAO {

    /**
     * Esegue il login di un utente.
     *
     * @param login username
     * @param password password
     * @return utente autenticato oppure null se non valido
     */
    Utente login(String login, String password);

    /**
     * Inserisce uno studente nel sistema.
     *
     * @param studente studente da inserire
     * @return true se inserito correttamente
     */
    boolean inserisciStudente(Studente studente);

    /**
     * Inserisce un docente nel sistema.
     *
     * @param docente docente da inserire
     * @return true se inserito correttamente
     */
    boolean inserisciDocente(Docente docente);

    /**
     * Restituisce tutti gli studenti.
     *
     * @return lista studenti
     */
    List<Studente> getStudenti();

    /**
     * Restituisce tutti i docenti.
     *
     * @return lista docenti
     */
    List<Docente> getDocenti();

    /**
     * Restituisce uno studente tramite login.
     *
     * @param login login studente
     * @return studente trovato oppure null
     */
    Studente getStudenteByLogin(String login);

    /**
     * Restituisce un docente tramite login.
     *
     * @param login login docente
     * @return docente trovato oppure null
     */
    Docente getDocenteByLogin(String login);

    /**
     * Restituisce un utente generico tramite login.
     *
     * @param login login utente
     * @return utente trovato oppure null
     */
    Utente getUtenteByLogin(String login);
}