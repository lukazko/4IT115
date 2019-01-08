package logika;

import java.util.Collection;

/**
 * Třída Zakaznik - slouží ke správě zákazníků, potomek Osoby.
 *
 * @author Lukáš
 * @created ZS 2018/2019
 */
public class Zakaznik extends Osoba {

    private Collection<Objednavka> objednavky;

    /*
     * Konstruktor třídy
     */
    public Zakaznik(int id, String jmeno, String prijmeni, String email, String telefon) {
        super(id, jmeno, prijmeni, email, telefon);
    }

    public Collection getObjednavky() {
        return objednavky;
    }

}
