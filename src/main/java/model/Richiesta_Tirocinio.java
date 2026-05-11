public class Richiesta_Tirocinio {
        private String stato;
        private Studente studente;
        private ArgomentoTirocinio argomento;

        public Richiesta_Tirocinio(String stato, Studente studente, ArgomentoTirocinio argomento) {
            this.stato = stato;
            this.studente = studente;
            this.argomento = argomento;
        }
    }
