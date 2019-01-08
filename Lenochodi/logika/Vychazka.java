package logika;

import java.util.Collection;

/**
 * Třída Vychazka - slouží ke správě vycházek.
 *
 * @author Lukáš
 * @created ZS 2018/2019
 */
public class Vychazka {

    private int id;
    private String nazev;
    private String datum;
    private String casZacatek;
    private String mistoZacatek;
    private String jazyk;
    private int kapacita;
    private int cena;
    private Pruvodce pruvodce;
    private String pruvodceJmeno;
    private Collection<Objednavka> seznamObjednavek;

    /*
     * Konstruktor třídy
     */
    public Vychazka(int id, String nazev, String datum, String casZacatek, String mistoZacatek, String jazyk, int kapacita, int cena, String pruvodceJmeno) {
        this.id = id;
        this.nazev = nazev;
        this.datum = datum;
        this.casZacatek = casZacatek;
        this.mistoZacatek = mistoZacatek;
        this.jazyk = jazyk;
        this.kapacita = kapacita;
        this.cena = cena;
        this.pruvodceJmeno = pruvodceJmeno;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPruvodce(Pruvodce pruvodce) {
        if (pruvodce.getJazyk() == jazyk) {
            this.pruvodce = pruvodce;
        }
    }

    public Pruvodce getPruvodce() {
        return pruvodce;
    }

    public String getPruvodceJmeno() {
        return pruvodceJmeno;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getNazev() {
        return nazev;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getDatum() {
        return datum;
    }

    public void setCasZacatek(String casZacatek) {
        this.casZacatek = casZacatek;
    }

    public String getCasZacatek() {
        return casZacatek;
    }

    public void setMistoZacatek(String mistoZacatek) {
        this.mistoZacatek = mistoZacatek;
    }

    public String getMistoZacatek() {
        return mistoZacatek;
    }

    public void setJazyk(String jazyk) {
        this.jazyk = jazyk;
    }

    public String getJazyk() {
        return jazyk;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getCena() {
        return cena;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }

    public int getKapacita() {
        return kapacita;
    }

    public int getZbyvajiciKapacita() {
        int pocetObjednavek = seznamObjednavek.size();
        int zbyvajiciKapacita = kapacita - pocetObjednavek;

        return zbyvajiciKapacita;
    }

    public Collection<Objednavka> getSeznamObjednavek() {
        return seznamObjednavek;
    }

}
