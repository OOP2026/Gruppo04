package model;

public class Tirocinio_Esterno {
    private String referenteAziendale;
    private String aziendaPartner;

    private Argomento_Tirocinio argomento;

    public Tirocinio_Esterno(String referenteAziendale,
                             String aziendaPartner,
                             Argomento_Tirocinio argomento) {

        this.referenteAziendale = referenteAziendale;
        this.aziendaPartner = aziendaPartner;
        this.argomento = argomento;
    }

    public String getReferenteAziendale() {
        return referenteAziendale;
    }

    public String getAziendaPartner() {
        return aziendaPartner;
    }

    public Argomento_Tirocinio getArgomento() {
        return argomento;
    }
}