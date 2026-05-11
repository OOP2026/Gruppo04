import java.util.List;

public class Seduta_Di_Laurea {
    private String data;
    private String ora;
    private String luogo;
    private List<Tesi> tesi; // aggregazione
    public Seduta_Di_Laurea(String data, String ora, String luogo, List<Tesi> tesi) {
        this.data = data;
        this.ora = ora;
        this.luogo = luogo;
        this.tesi = tesi;
    }
}

