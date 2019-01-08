package logika;

/**
 * Třída Osoba - slouží ke správě osob.
 *
 * @author Lukáš Kubík
 * @created ZS 2018/2019
 */
public class Osoba {

    private int id;
    private String jmeno;
    private String prijmeni;
    private String email;
    private String telefon;

    /*
     * Konstruktor třídy
     */
    public Osoba(int id, String jmeno, String prijmeni, String email, String telefon) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.email = email;
        this.telefon = telefon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public String getCeleJmeno() {
        return jmeno + " " + prijmeni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTelefon() {
        return telefon;
    }

}
