package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe di utilità per la gestione della connessione al database PostgreSQL.
 * Fornisce un punto centralizzato per ottenere connessioni JDBC.
 */
public class DBConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/tesi_db";

    private static final String USER = "postgres";

    private static final String PASSWORD = "postgres";

    /**
     * Restituisce una connessione al database PostgreSQL.
     *
     * @return Connection attiva
     * @throws SQLException in caso di errore di connessione
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}