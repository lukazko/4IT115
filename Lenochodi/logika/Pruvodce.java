package logika;

import java.util.Collection;

/**
 * Třída Průvodce - slouží ke správě průvodců, potomek Osoby.
 *
 * @author Lukáš
 * @created ZS 2018/2019
 */
public class Pruvodce extends Osoba {

    private String jazyk;
    private Collection<Vychazka> vychazky;

    /*
     * Konstruktor třídy
     */
    public Pruvodce(int id, String jmeno, String prijmeni, String email, String telefon, String jazyk) {
        super(id, jmeno, prijmeni, email, telefon);
        this.jazyk = jazyk;
    }

    public void setJazyk(String jazyk) {
        this.jazyk = jazyk;
    }

    public String getJazyk() {
        return jazyk;
    }

    public Collection getVychazky() {
        return vychazky;
    }

}
