package model;

public class Utente  {
    protected String nome;
    protected String cognome;
    protected String email;
    protected String login;
    protected String password;

    public Utente(String nome, String cognome,
                  String email, String login, String password) {

        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}