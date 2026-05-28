package model;

import java.util.ArrayList;
import java.util.List;

public class Argomento_Tirocinio {

    private String titolo;
    private String descrizione;

    private List<Richiesta_Tirocinio> richieste;

    public Argomento_Tirocinio(String titolo, String descrizione) {

        this.titolo = titolo;
        this.descrizione = descrizione;
        this.richieste = new ArrayList<>();
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public List<Richiesta_Tirocinio> getRichieste() {
        return richieste;
    }

    public void aggiungiRichiesta(Richiesta_Tirocinio richiesta) {
        richieste.add(richiesta);
    }
}

