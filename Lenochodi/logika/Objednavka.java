package logika;

/**
 * Třída Objednávka - slouží ke správě objednávek.
 *
 *
 * @author Lukáš
 * @created ZS 2018/2019
 */
public class Objednavka {

    private int id;
    private String stav;
    private Vychazka vychazka;
    private Zakaznik zakaznik;
    private String vychazkaString;
    private String zakaznikString;

    public Objednavka(int id, String vychazkaString, String zakaznikString, String stav) {
        this.id = id;
        this.vychazkaString = vychazkaString;
        this.zakaznikString = zakaznikString;
        this.stav = stav;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getVychazkaString() {
        return vychazkaString;
    }

    public String getZakaznikString() {
        return zakaznikString;
    }

    public String getPopis() {
        return Integer.toString(id) + " " + vychazkaString + ", " + zakaznikString;
    }

    public void setStav(String stav) {
        this.stav = stav;
    }

    public String getStav() {
        return stav;
    }

    public void setVychazka(Vychazka vychazka) {
        if (vychazka.getZbyvajiciKapacita() > 0) {
            this.vychazka = vychazka;
        }
    }

    public Vychazka getVychazka() {
        return vychazka;
    }

    public void setZakaznik(Zakaznik zakaznik) {
        this.zakaznik = zakaznik;
    }

    public Zakaznik getZakaznik() {
        return zakaznik;
    }

}
