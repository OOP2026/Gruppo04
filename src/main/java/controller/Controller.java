package controller;


import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

    public class Controller {

        private List<Studente> studenti;
        private List<Docente> docenti;
        private List<Tesi> tesi;
        private List<Seduta_Di_Laurea> sedute;
        private List<Argomento_Tirocinio> argomenti;
        private List<Richiesta_Tirocinio> richieste;
        private List<Tirocinio_Esterno> tirociniEsterni;

        public Controller() {

            studenti = new ArrayList<>();
            docenti = new ArrayList<>();
            tesi = new ArrayList<>();
            sedute = new ArrayList<>();
            argomenti = new ArrayList<>();
            richieste = new ArrayList<>();
            tirociniEsterni = new ArrayList<>();
        }

        // LOGIN

        public Utente login(String login, String password) {

            for (Studente s : studenti) {
                if (s.getLogin().equals(login)
                        && s.getPassword().equals(password)) {
                    return s;
                }
            }

            for (Docente d : docenti) {
                if (d.getLogin().equals(login)
                        && d.getPassword().equals(password)) {
                    return d;
                }
            }

            return null;
        }

        // STUDENTI

        public void aggiungiStudente(Studente studente) {
            studenti.add(studente);
        }

        public List<Studente> getStudenti() {
            return studenti;
        }

        // DOCENTI

        public void aggiungiDocente(Docente docente) {
            docenti.add(docente);
        }

        public List<Docente> getDocenti() {
            return docenti;
        }

        // TESI

        public void caricaTesi(String file,
                               Studente studente,
                               Docente docente) {

            Tesi t = new Tesi(
                    file,
                    "IN ATTESA",
                    new Date(),
                    studente,
                    docente
            );

            tesi.add(t);
            docente.aggiungiTesi(t);
        }

        public void approvaTesi(Tesi tesi) {
            tesi.setStatoApprovazione("APPROVATA");
        }

        public List<Tesi> getTesi() {
            return tesi;
        }

        // SEDUTE

        public void creaSeduta(String data,
                               String ora,
                               String luogo,
                               List<Tesi> tesiSeduta) {

            Seduta_Di_Laurea seduta =
                    new Seduta_Di_Laurea(data, ora, luogo, tesiSeduta);

            sedute.add(seduta);
        }

        public List<Seduta_Di_Laurea> getSedute() {
            return sedute;
        }

        // ARGOMENTI TIROCINIO

        public void aggiungiArgomento(String titolo,
                                      String descrizione) {

            Argomento_Tirocinio a =
                    new Argomento_Tirocinio(titolo, descrizione);

            argomenti.add(a);
        }

        public List<Argomento_Tirocinio> getArgomenti() {
            return argomenti;
        }

        // RICHIESTE TIROCINIO

        public void inviaRichiestaTirocinio(
                Studente studente,
                Argomento_Tirocinio argomento) {

            Richiesta_Tirocinio richiesta =
                    new Richiesta_Tirocinio(
                            "IN ATTESA",
                            studente,
                            argomento
                    );

            richieste.add(richiesta);

            argomento.aggiungiRichiesta(richiesta);
        }

        public List<Richiesta_Tirocinio> getRichieste() {
            return richieste;
        }

        // TIROCINI ESTERNI

        public void aggiungiTirocinioEsterno(
                String referente,
                String azienda,
                Argomento_Tirocinio argomento) {

            Tirocinio_Esterno t =
                    new Tirocinio_Esterno(
                            referente,
                            azienda,
                            argomento
                    );

            tirociniEsterni.add(t);
        }

        public List<Tirocinio_Esterno> getTirociniEsterni() {
            return tirociniEsterni;
        }
    }
