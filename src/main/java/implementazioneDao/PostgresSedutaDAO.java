package postgresdao;

import dao.SedutaDAO;
import dao.TesiDAO;
import database.DBConnection;
import model.Seduta_Di_Laurea;
import model.Tesi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione PostgreSQL del DAO Seduta di Laurea.
 * Gestisce creazione e recupero delle sedute.
 */
public class PostgresSedutaDAO implements SedutaDAO {

    private final TesiDAO tesiDAO;

    public PostgresSedutaDAO(TesiDAO tesiDAO) {
        this.tesiDAO = tesiDAO;
    }

    @Override
    public boolean creaSeduta(Seduta_Di_Laurea seduta) {

        String sqlSeduta =
                "INSERT INTO seduta_laurea (data_seduta, ora, luogo) VALUES (?, ?, ?)";

        String sqlLink =
                "INSERT INTO seduta_tesi (data_seduta, ora, luogo, file_tesi, studente_login, docente_login) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {

            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(sqlSeduta)) {

                ps.setString(1, seduta.getData());
                ps.setString(2, seduta.getOra());
                ps.setString(3, seduta.getLuogo());
                ps.executeUpdate();
            }

            try (PreparedStatement ps = conn.prepareStatement(sqlLink)) {

                for (Tesi t : seduta.getTesi()) {

                    ps.setString(1, seduta.getData());
                    ps.setString(2, seduta.getOra());
                    ps.setString(3, seduta.getLuogo());
                    ps.setString(4, t.getFileTesi());
                    ps.setString(5, t.getStudente().getLogin());
                    ps.setString(6, t.getDocente().getLogin());

                    ps.addBatch();
                }

                ps.executeBatch();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Seduta_Di_Laurea> getSedute() {

        List<Seduta_Di_Laurea> lista = new ArrayList<>();
        String sql = "SELECT * FROM seduta_laurea ORDER BY data_seduta, ora";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                String data = rs.getString("data_seduta");
                String ora = rs.getString("ora");
                String luogo = rs.getString("luogo");

                List<Tesi> tesi = getTesiSeduta(data, ora, luogo);

                lista.add(new Seduta_Di_Laurea(data, ora, luogo, tesi));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Recupera le tesi associate a una seduta.
     */
    private List<Tesi> getTesiSeduta(String data, String ora, String luogo) {

        List<Tesi> lista = new ArrayList<>();

        String sql =
                "SELECT * FROM seduta_tesi WHERE data_seduta = ? AND ora = ? AND luogo = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, data);
            ps.setString(2, ora);
            ps.setString(3, luogo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Tesi t = tesiDAO.getTesiByChiavi(
                        rs.getString("file_tesi"),
                        rs.getString("studente_login"),
                        rs.getString("docente_login")
                );

                if (t != null) {
                    lista.add(t);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}