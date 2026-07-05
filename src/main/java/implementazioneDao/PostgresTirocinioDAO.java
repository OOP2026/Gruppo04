package implementazioneDao;

import dao.TirocinioDAO;
import dao.UtenteDAO;
import database.DBConnection;
import model.Argomento_Tirocinio;
import model.Richiesta_Tirocinio;
import model.Studente;
import model.Tirocinio_Esterno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione PostgreSQL del DAO Tirocinio.
 * Gestisce argomenti, richieste e tirocini esterni.
 */
public class PostgresTirocinioDAO implements TirocinioDAO {

    private final UtenteDAO utenteDAO;

    public PostgresTirocinioDAO(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    @Override
    public boolean inserisciArgomento(Argomento_Tirocinio argomento) {

        String sql =
                "INSERT INTO argomento_tirocinio (titolo, descrizione) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, argomento.getTitolo());
            ps.setString(2, argomento.getDescrizione());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'inserimento dell'argomento di tirocinio.", e);
        }
    }

    @Override
    public boolean inviaRichiesta(Richiesta_Tirocinio richiesta) {

        String sql =
                "INSERT INTO richiesta_tirocinio (stato, studente_login, argomento_titolo) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, richiesta.getStato());
            ps.setString(2, richiesta.getStudente().getLogin());
            ps.setString(3, richiesta.getArgomento().getTitolo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'invio della richiesta di tirocinio.", e);
        }
    }

    @Override
    public boolean inserisciTirocinioEsterno(Tirocinio_Esterno tirocinioEsterno) {

        String sql =
                "INSERT INTO tirocinio_esterno (referente_aziendale, azienda_partner, argomento_titolo) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tirocinioEsterno.getReferenteAziendale());
            ps.setString(2, tirocinioEsterno.getAziendaPartner());
            ps.setString(3, tirocinioEsterno.getArgomento().getTitolo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'inserimento del tirocinio esterno.", e);
        }
    }

    @Override
    public List<Argomento_Tirocinio> getArgomenti() {

        List<Argomento_Tirocinio> lista = new ArrayList<>();
        String sql = "SELECT * FROM argomento_tirocinio ORDER BY titolo";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                lista.add(new Argomento_Tirocinio(
                        rs.getString("titolo"),
                        rs.getString("descrizione")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero degli argomenti di tirocinio.", e);
        }

        return lista;
    }

    @Override
    public List<Richiesta_Tirocinio> getRichieste() {

        List<Richiesta_Tirocinio> lista = new ArrayList<>();

        String sql = "SELECT * FROM richiesta_tirocinio ORDER BY stato";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Studente studente =
                        utenteDAO.getStudenteByLogin(rs.getString("studente_login"));

                Argomento_Tirocinio argomento =
                        getArgomentoByTitolo(rs.getString("argomento_titolo"));

                if (studente != null && argomento != null) {

                    lista.add(new Richiesta_Tirocinio(
                            rs.getString("stato"),
                            studente,
                            argomento
                    ));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero delle richieste di tirocinio.", e);
        }

        return lista;
    }

    @Override
    public List<Tirocinio_Esterno> getTirociniEsterni() {

        List<Tirocinio_Esterno> lista = new ArrayList<>();

        String sql = "SELECT * FROM tirocinio_esterno ORDER BY azienda_partner";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Argomento_Tirocinio argomento =
                        getArgomentoByTitolo(rs.getString("argomento_titolo"));

                if (argomento != null) {

                    lista.add(new Tirocinio_Esterno(
                            rs.getString("referente_aziendale"),
                            rs.getString("azienda_partner"),
                            argomento
                    ));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero dei tirocini esterni.", e);
        }

        return lista;
    }

    @Override
    public Argomento_Tirocinio getArgomentoByTitolo(String titolo) {

        String sql =
                "SELECT * FROM argomento_tirocinio WHERE titolo = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, titolo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Argomento_Tirocinio(
                        rs.getString("titolo"),
                        rs.getString("descrizione")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero dell'argomento di tirocinio.", e);
        }

        return null;
    }
}
