package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestisce la connessione al database PostgreSQL.
 */
public final class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/tesi_db";

    /*
     * Le credenziali vengono lette dalle variabili d'ambiente.
     * Se non sono presenti, vengono usati i valori di default.
     */
    private static final String USER =
            System.getenv().getOrDefault("DB_USER", "postgres");

    private static final String PASSWORD =
            System.getenv().getOrDefault("DB_PASSWORD", "postgres");

    private DBConnection() {
        // Impedisce l'istanziazione della classe.
    }

    /**
     * Restituisce una connessione al database.
     *
     * @return connessione JDBC
     * @throws SQLException se la connessione fallisce
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}