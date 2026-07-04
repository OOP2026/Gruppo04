package dao;

import model.Argomento_Tirocinio;
import model.Richiesta_Tirocinio;
import model.Tirocinio_Esterno;

import java.util.List;

/**
 * Interfaccia DAO per la gestione dei tirocini e argomenti.
 */
public interface TirocinioDAO {

    /**
     * Inserisce un nuovo argomento di tirocinio.
     *
     * @param argomento argomento da inserire
     * @return true se inserito correttamente
     */
    boolean inserisciArgomento(Argomento_Tirocinio argomento);

    /**
     * Invia una richiesta di tirocinio.
     *
     * @param richiesta richiesta da inviare
     * @return true se inserita correttamente
     */
    boolean inviaRichiesta(Richiesta_Tirocinio richiesta);

    /**
     * Inserisce un tirocinio esterno.
     *
     * @param tirocinioEsterno tirocinio esterno
     * @return true se inserito correttamente
     */
    boolean inserisciTirocinioEsterno(Tirocinio_Esterno tirocinioEsterno);

    /**
     * Restituisce tutti gli argomenti di tirocinio.
     *
     * @return lista argomenti
     */
    List<Argomento_Tirocinio> getArgomenti();

    /**
     * Restituisce tutte le richieste di tirocinio.
     *
     * @return lista richieste
     */
    List<Richiesta_Tirocinio> getRichieste();

    /**
     * Restituisce tutti i tirocini esterni.
     *
     * @return lista tirocini esterni
     */
    List<Tirocinio_Esterno> getTirociniEsterni();

    /**
     * Recupera un argomento tramite titolo.
     *
     * @param titolo titolo argomento
     * @return argomento trovato oppure null
     */
    Argomento_Tirocinio getArgomentoByTitolo(String titolo);
}