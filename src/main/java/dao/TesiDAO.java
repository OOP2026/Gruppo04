package dao;

import model.Tesi;

import java.util.List;

/**
 * Interfaccia DAO per la gestione delle tesi.
 */
public interface TesiDAO {

    /**
     * Salva una nuova tesi nel sistema.
     *
     * @param tesi tesi da salvare
     * @return true se inserita correttamente
     */
    boolean salvaTesi(Tesi tesi);

    /**
     * Restituisce tutte le tesi presenti nel sistema.
     *
     * @return lista tesi
     */
    List<Tesi> getTesi();

    /**
     * Recupera una tesi tramite chiavi identificative.
     *
     * @param fileTesi nome file tesi
     * @param studenteLogin login studente
     * @param docenteLogin login docente
     * @return tesi trovata oppure null
     */
    Tesi getTesiByChiavi(String fileTesi, String studenteLogin, String docenteLogin);

    /**
     * Approva una tesi.
     *
     * @param tesi tesi da approvare
     * @return true se aggiornata correttamente
     */
    boolean approvaTesi(Tesi tesi);
}