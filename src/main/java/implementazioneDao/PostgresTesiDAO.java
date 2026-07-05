package implementazioneDao;

import dao.TesiDAO;
import dao.UtenteDAO;
import database.DBConnection;
import model.Docente;
import model.Studente;
import model.Tesi;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementazione PostgreSQL del DAO Tesi.
 * Gestisce salvataggio, lettura e approvazione delle tesi.
 */
public class PostgresTesiDAO implements TesiDAO {

    private final UtenteDAO utenteDAO;

    public PostgresTesiDAO(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    @Override
    public boolean salvaTesi(Tesi tesi) {

        String sql = "INSERT INTO tesi " +
                "(file_tesi, stato_approvazione, data_caricamento, studente_login, docente_login) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tesi.getFileTesi());
            ps.setString(2, tesi.getStatoApprovazione());
            ps.setDate(3, new java.sql.Date(tesi.getDataCaricamento().getTime()));
            ps.setString(4, tesi.getStudente().getLogin());
            ps.setString(5, tesi.getDocente().getLogin());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il salvataggio della tesi.", e);
        }
    }

    @Override
    public List<Tesi> getTesi() {

        List<Tesi> lista = new ArrayList<>();
        String sql = "SELECT * FROM tesi ORDER BY data_caricamento DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapTesi(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero delle tesi.", e);
        }

        return lista;
    }

    @Override
    public Tesi getTesiByChiavi(String fileTesi,
                                String studenteLogin,
                                String docenteLogin) {

        String sql = "SELECT * FROM tesi WHERE file_tesi = ? AND studente_login = ? AND docente_login = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fileTesi);
            ps.setString(2, studenteLogin);
            ps.setString(3, docenteLogin);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapTesi(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero della tesi.", e);
        }

        return null;
    }

    @Override
    public boolean approvaTesi(Tesi tesi) {

        String sql = "UPDATE tesi SET stato_approvazione = 'APPROVATA' " +
                "WHERE file_tesi = ? AND studente_login = ? AND docente_login = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tesi.getFileTesi());
            ps.setString(2, tesi.getStudente().getLogin());
            ps.setString(3, tesi.getDocente().getLogin());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'approvazione della tesi.", e);
        }
    }

    /**
     * Mappa una riga del ResultSet in un oggetto Tesi.
     */
    private Tesi mapTesi(ResultSet rs) throws SQLException {

        Studente studente =
                utenteDAO.getStudenteByLogin(rs.getString("studente_login"));

        Docente docente =
                utenteDAO.getDocenteByLogin(rs.getString("docente_login"));

        Date dataCaricamento =
                new Date(rs.getDate("data_caricamento").getTime());

        return new Tesi(
                rs.getString("file_tesi"),
                rs.getString("stato_approvazione"),
                dataCaricamento,
                studente,
                docente
        );
    }
}
