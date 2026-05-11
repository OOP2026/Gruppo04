import java.util.Date;

public class Tesi {
    private String fileTesi;
    private String statoApprovazione;
    private Date dataCaricamento;
    private Studente studente;
    private Docente docente;
    public Tesi(String fileTesi, String statoApprovazione, Date dataCaricamento,
                Studente studente, Docente docente) {
        this.fileTesi = fileTesi;
        this.statoApprovazione = statoApprovazione;
        this.dataCaricamento = dataCaricamento;
        this.studente = studente;
        this.docente = docente;
    }
}
