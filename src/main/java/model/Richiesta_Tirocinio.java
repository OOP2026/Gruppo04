package model;

public class Richiesta_Tirocinio {
    private String stato;

    private Studente studente;
    private Argomento_Tirocinio argomento;

    public Richiesta_Tirocinio(String stato,
                              Studente studente,
                              Argomento_Tirocinio argomento) {

        this.stato = stato;
        this.studente = studente;
        this.argomento = argomento;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Studente getStudente() {
        return studente;
    }

    public Argomento_Tirocinio getArgomento() {
        return argomento;
    }
}