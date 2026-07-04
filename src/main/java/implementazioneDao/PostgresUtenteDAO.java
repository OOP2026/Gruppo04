package postgresdao;

import dao.UtenteDAO;
import database.DBConnection;
import model.Docente;
import model.Studente;
import model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione PostgreSQL del DAO Utente.
 * Gestisce tutte le operazioni CRUD sugli utenti nel database.
 */
public class PostgresUtenteDAO implements UtenteDAO {

    @Override
    public Utente login(String login, String password) {

        String sql = "SELECT * FROM utente WHERE login = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapUtente(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean inserisciStudente(Studente studente) {

        String sql = "INSERT INTO utente " +
                "(nome, cognome, email, login, password, tipo, matricola, coordinatore) " +
                "VALUES (?, ?, ?, ?, ?, 'STUDENTE', ?, false)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studente.getNome());
            ps.setString(2, studente.getCognome());
            ps.setString(3, studente.getEmail());
            ps.setString(4, studente.getLogin());
            ps.setString(5, studente.getPassword());
            ps.setString(6, studente.getMatricola());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean inserisciDocente(Docente docente) {

        String sql = "INSERT INTO utente " +
                "(nome, cognome, email, login, password, tipo, matricola, coordinatore) " +
                "VALUES (?, ?, ?, ?, ?, 'DOCENTE', NULL, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, docente.getNome());
            ps.setString(2, docente.getCognome());
            ps.setString(3, docente.getEmail());
            ps.setString(4, docente.getLogin());
            ps.setString(5, docente.getPassword());
            ps.setBoolean(6, docente.isCoordinatore());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Studente> getStudenti() {

        List<Studente> lista = new ArrayList<>();
        String sql = "SELECT * FROM utente WHERE tipo = 'STUDENTE'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapStudente(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<Docente> getDocenti() {

        List<Docente> lista = new ArrayList<>();
        String sql = "SELECT * FROM utente WHERE tipo = 'DOCENTE'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapDocente(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Studente getStudenteByLogin(String login) {

        String sql = "SELECT * FROM utente WHERE login = ? AND tipo = 'STUDENTE'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapStudente(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Docente getDocenteByLogin(String login) {

        String sql = "SELECT * FROM utente WHERE login = ? AND tipo = 'DOCENTE'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapDocente(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Utente getUtenteByLogin(String login) {

        String sql = "SELECT * FROM utente WHERE login = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapUtente(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ===================== MAPPER =====================

    /**
     * Converte una riga ResultSet in un Utente.
     */
    private Utente mapUtente(ResultSet rs) throws SQLException {
        String tipo = rs.getString("tipo");

        if ("STUDENTE".equalsIgnoreCase(tipo)) {
            return mapStudente(rs);
        }

        return mapDocente(rs);
    }

    /**
     * Converte una riga ResultSet in uno Studente.
     */
    private Studente mapStudente(ResultSet rs) throws SQLException {
        return new Studente(
                rs.getString("nome"),
                rs.getString("cognome"),
                rs.getString("email"),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("matricola")
        );
    }

    /**
     * Converte una riga ResultSet in un Docente.
     */
    private Docente mapDocente(ResultSet rs) throws SQLException {
        return new Docente(
                rs.getString("nome"),
                rs.getString("cognome"),
                rs.getString("email"),
                rs.getString("login"),
                rs.getString("password"),
                rs.getBoolean("coordinatore")
        );
    }
}