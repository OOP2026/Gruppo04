import java.util.List;
    public class Argomento_Tirocinio {
        private String titolo;
        private String descrizione;
        private List<Richiesta_Tirocinio> richieste;

        public Argomento_Tirocinio(String titolo, String descrizione) {
            this.titolo = titolo;
            this.descrizione = descrizione;
        }
    }
