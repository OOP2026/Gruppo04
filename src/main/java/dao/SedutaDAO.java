package dao;

import model.Seduta_Di_Laurea;

import java.util.List;

/**
 * Interfaccia DAO per la gestione delle sedute di laurea.
 */
public interface SedutaDAO {

    /**
     * Crea una nuova seduta di laurea.
     *
     * @param seduta seduta da creare
     * @return true se inserita correttamente
     */
    boolean creaSeduta(Seduta_Di_Laurea seduta);

    /**
     * Restituisce tutte le sedute di laurea.
     *
     * @return lista sedute
     */
    List<Seduta_Di_Laurea> getSedute();
}