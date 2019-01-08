package logika;

import java.sql.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Simona
 * @created ZS 2018/2019
 *
 * Třída, která využívá komunikace s databází. Metody této třídy slouží k
 * manipulaci s daty v databázi a v aplikaci. Jsou zde předpřipravené metody pro
 * dotazy SELECT, INSERT a UPDATE pro jednotlivé tabulky. V komentáři u metody
 * je jako příklad popsána situace, kde danou metodu použijeme. Výsledky dotazů
 * SELECT jsou ukládány do ObservableList.
 *
 */
public class Aplikace {

    private ObservableList<Zakaznik> evidenceZakazniku;

    private ObservableList<Pruvodce> evidencePruvodcu;

    private ObservableList<Objednavka> evidenceObjednavek;

    private ObservableList<Vychazka> evidenceVychazek;

    private ObservableList<String> listPruvodcu;

    private ObservableList<String> listZakazniku;

    private ObservableList<String> listVychazek;

    private ArrayList<String> seznamPruvodcu;

    private ArrayList<String> seznamZakazniku;

    private ArrayList<String> seznamVychazek;

    private ArrayList<String> seznamObjednavek;

    private Zakaznik zakaznik;

    private Pruvodce pruvodce;

    private Objednavka objednavka;

    private Vychazka vychazka;

    Databaze databaze = new Databaze();
    public static int pomIndex;

    /**
     * Konstruktor třídy aplikace.
     */
    public Aplikace() {

        evidenceObjednavek = FXCollections.observableArrayList();
        evidencePruvodcu = FXCollections.observableArrayList();
        evidenceVychazek = FXCollections.observableArrayList();
        evidenceZakazniku = FXCollections.observableArrayList();
        listPruvodcu = FXCollections.observableArrayList();
        listZakazniku = FXCollections.observableArrayList();
        listVychazek = FXCollections.observableArrayList();
        seznamObjednavek = new ArrayList<>();
        seznamPruvodcu = new ArrayList<>();
        seznamVychazek = new ArrayList<>();
        seznamZakazniku = new ArrayList<>();

    }

    /**
     * Metoda vrací seznam průvodců, kteří jsou v DB.
     *
     * @return ObservableList<Pruvodce>
     * @throws SQLException
     */
    public ObservableList<Pruvodce> getEvidencePruvodcu() throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            String query = "SELECT * FROM `pruvodci`";
            connection = databaze.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                Pruvodce pruvodce = new Pruvodce(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
                evidencePruvodcu.add(pruvodce);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return evidencePruvodcu;
    }

    /**
     * Metoda pro vložení nového průvodce do DB, volá se v založení průvodce při
     * stisknutí tlačítka pro uložení.
     *
     * @param id
     * @param jmeno
     * @param prijmeni
     * @param email
     * @param telefon
     * @param jazyk
     * @throws SQLException
     */
    public void zalozPruvodce(String id, String jmeno, String prijmeni, String email, String telefon, String jazyk) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = databaze.getConnection();
            statement = connection.createStatement();
            String query = "INSERT INTO `pruvodci` (`id`,`jmeno`,`prijmeni`,`email`,`telefon`, `jazyk`) VALUES ('"
                    + id + "', '" + jmeno + "', '" + prijmeni + "', '" + email + "', '" + telefon + "', '" + jazyk + "')";
            statement.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metoda pro úpravu průvodce, volá se v editaci průvodce při stisknutí
     * tlačítka pro uložení. ID upravit nelze.
     *
     * @param id
     * @param jmeno
     * @param prijmeni
     * @param email
     * @param telefon
     * @param jazyk
     * @throws SQLException
     */
    public void upravPruvodce(String id, String jmeno, String prijmeni, String email, String telefon, String jazyk) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = databaze.getConnection();
            statement = connection.createStatement();
            String query = "UPDATE `pruvodci` SET `id` = '" + id + "', `jmeno` = '" + jmeno + "', `prijmeni` = '" + prijmeni
                    + "', `email` = '" + email + "', `telefon` = '" + telefon + "', `jazyk` = '" + jazyk + "' WHERE `pruvodci`.`id` = " + id;
            statement.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metoda slouží pro získání údajů o konkrétním průvodci. Metoda se využíva
     * pro výpis v detailu a editaci průvodce.
     *
     * @param index
     * @return Pruvodce
     * @throws SQLException
     */
    public Pruvodce getPruvodce(int index) throws SQLException {
        evidencePruvodcu = getEvidencePruvodcu();
        pruvodce = evidencePruvodcu.get(index);
        return pruvodce;
    }

    /**
     * Metoda slouží pro získání seznamu průvodců z DB. Metoda se využíva pro
     * výpis v přehledu průvodců.
     *
     * @return ArrayList<String>
     * @throws SQLException
     */
    public ArrayList<String> getSeznamPruvodcu() throws SQLException {
        evidencePruvodcu = getEvidencePruvodcu();
        for (Pruvodce pom : evidencePruvodcu) {
            seznamPruvodcu.add(pom.getCeleJmeno());
        }
        return seznamPruvodcu;
    }

    /**
     * Metoda slouží pro získání seznamu průvodců z DB. Metoda se využíva pro
     * výpis v checkBoxech.
     *
     * @return ObservableList<String>
     * @throws SQLException
     */
    public ObservableList<String> getObservableListPruvodcu() throws SQLException {
        evidencePruvodcu = getEvidencePruvodcu();
        for (Pruvodce pom : evidencePruvodcu) {
               listPruvodcu.add(pom.getCeleJmeno() + " " + pom.getJazyk()); 
        }
        return listPruvodcu;
    }

    /**
     * Metoda vrací seznam zákazníků, kteří jsou v DB.
     *
     * @return ObservableList<Zakaznik>
     * @throws SQLException
     */
    public ObservableList<Zakaznik> getEvidenceZakazniku() throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            String query = "SELECT * FROM `zakaznici`";
            connection = databaze.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                Zakaznik zakaznik = new Zakaznik(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                evidenceZakazniku.add(zakaznik);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return evidenceZakazniku;
    }

    /**
     * Metoda pro vložení nového zákazníka do DB, volá se v založení zákazníka
     * při stisknutí tlačítka pro uložení.
     *
     * @param id
     * @param jmeno
     * @param prijmeni
     * @param email
     * @param telefon
     * @throws SQLException
     */
    public void zalozZakaznika(String id, String jmeno, String prijmeni, String email, String telefon) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = databaze.getConnection();
            statement = connection.createStatement();
            String query = "INSERT INTO `zakaznici` (`id`,`jmeno`,`prijmeni`,`email`,`telefon`) VALUES ('"
                    + id + "', '" + jmeno + "', '" + prijmeni + "', '" + email + "', '" + telefon + "')";
            statement.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metoda pro úpravu zákazníka, volá se v editaci zákazníka při stisknutí
     * tlačítka pro uložení. ID upravit nelze.
     *
     * @param id
     * @param jmeno
     * @param prijmeni
     * @param email
     * @param telefon
     * @throws SQLException
     */
    public void upravZakaznika(String id, String jmeno, String prijmeni, String email, String telefon) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = databaze.getConnection();
            statement = connection.createStatement();
            String query = "UPDATE `zakaznici` SET `id` = '" + id + "', `jmeno` = '" + jmeno + "', `prijmeni` = '" + prijmeni
                    + "', `email` = '" + email + "', `telefon` = '" + telefon + "' WHERE `zakaznici`.`id` = " + id;
            statement.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metoda slouží pro získání údajů o konkrétním zákazníkovi. Metoda se
     * využíva pro výpis v detailu a editaci zakaznika.
     *
     * @param index
     * @return Zakaznik
     * @throws SQLException
     */
    public Zakaznik getZakaznika(int index) throws SQLException {
        evidenceZakazniku = getEvidenceZakazniku();
        zakaznik = evidenceZakazniku.get(index);
        return zakaznik;
    }

    /**
     * Metoda slouží pro získání seznamu zákazníků z DB. Metoda se využíva pro
     * výpis v přehledu zákazníků.
     *
     * @return ArrayList<String>
     * @throws SQLException
     */
    public ArrayList<String> getSeznamZakazniku() throws SQLException {
        evidenceZakazniku = getEvidenceZakazniku();
        for (Zakaznik pom : evidenceZakazniku) {
            seznamZakazniku.add(pom.getCeleJmeno());
        }
        return seznamZakazniku;
    }

    /**
     * Metoda slouží pro získání seznamu zákazníků z DB. Metoda se využíva pro
     * výpis v checkBoxech.
     *
     * @return ObservableList<String>
     * @throws SQLException
     */
    public ObservableList<String> getObservableListZakazniku() throws SQLException {
        evidenceZakazniku = getEvidenceZakazniku();
        for (Zakaznik pom : evidenceZakazniku) {
            listZakazniku.add(pom.getCeleJmeno());
        }
        return listZakazniku;
    }

    /**
     * Metoda vrací seznam vycházek, které jsou v DB.
     *
     * @return ObservableList<Vychazka>
     * @throws SQLException
     */
    public ObservableList<Vychazka> getEvidenceVychazek() throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            String query = "SELECT * FROM `vychazky`";
            connection = databaze.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                Vychazka vychazka = new Vychazka(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getString(9));
                evidenceVychazek.add(vychazka);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return evidenceVychazek;
    }

    /**
     * Metoda pro vložení nové vycházky do DB, volá se v založení vycházky při
     * stisknutí tlačítka pro uložení.
     *
     * @param id
     * @param nazev
     * @param datum
     * @param casZacatek
     * @param mistoZacatek
     * @param jazyk
     * @param kapacita
     * @param cena
     * @param pruvodce
     * @throws SQLException
     */
    public void zalozVychazku(String id, String nazev, String datum, String casZacatek, String mistoZacatek, String jazyk, String kapacita, String cena, String pruvodce) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = databaze.getConnection();
            statement = connection.createStatement();
            String query = "INSERT INTO `vychazky` (`id`,`nazev`,`datum`,`casZacatek`,`mistoZacatek`,`jazyk`,`kapacita`,`cena`,`pruvodce`) VALUES ('"
                    + id + "', '" + nazev + "', '" + datum + "', '" + casZacatek + "', '" + mistoZacatek + "', '" + jazyk + "', '" + kapacita + "', '" + cena + "', '" + pruvodce + "')";
            statement.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metoda pro úpravu vycházky, volá se v editaci vycházky při stisknutí
     * tlačítka pro uložení. ID upravit nelze.
     *
     * @param id
     * @param nazev
     * @param datum
     * @param casZacatek
     * @param mistoZacatek
     * @param jazyk
     * @param kapacita
     * @param cena
     * @param pruvodce
     * @throws SQLException
     */
    public void upravVychazku(String id, String nazev, String datum, String casZacatek, String mistoZacatek, String jazyk, String kapacita, String cena, String pruvodce) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = databaze.getConnection();
            statement = connection.createStatement();
            String query = "UPDATE `vychazky` SET `id` = '" + id + "', `nazev` = '" + nazev + "', `datum` = '" + datum
                    + "', `casZacatek` = '" + casZacatek + "', `mistoZacatek` = '" + mistoZacatek + "', `jazyk` = '" + jazyk
                    + "', `kapacita` = '" + kapacita + "', `cena` = '" + cena + "', `pruvodce` = '" + pruvodce + "' WHERE `vychazky`.`id` = " + id;
            statement.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metoda slouží pro získání údajů o konkrétní vycházce. Metoda se využíva
     * pro výpis v detailu a editaci vycházky.
     *
     * @param index
     * @return Vychazka
     * @throws SQLException
     */
    public Vychazka getVychazku(int index) throws SQLException {
        evidenceVychazek = getEvidenceVychazek();
        vychazka = evidenceVychazek.get(index);
        return vychazka;
    }

    /**
     * Metoda slouží pro získání seznamu vycházek z DB. Metoda se využíva pro
     * výpis v přehledu vycházek.
     *
     * @return ArrayList<String>
     * @throws SQLException
     */
    public ArrayList<String> getSeznamVychazek() throws SQLException {
        evidenceVychazek = getEvidenceVychazek();
        for (Vychazka pom : evidenceVychazek) {
            seznamVychazek.add(pom.getNazev());
        }
        return seznamVychazek;
    }

    /**
     * Metoda slouží pro získání seznamu vycházek z DB. Metoda se využíva pro
     * výpis v checkBoxech.
     *
     * @return ObservableList<String>
     * @throws SQLException
     */
    public ObservableList<String> getObservableListVychazek() throws SQLException {
        evidenceVychazek = getEvidenceVychazek();
        for (Vychazka pom : evidenceVychazek) {
            listVychazek.add(pom.getNazev());
        }
        return listVychazek;
    }

    /**
     * Metoda vrací seznam objednávek, které jsou v DB.
     *
     * @return ObservableList<Objednavka>
     * @throws SQLException
     */
    public ObservableList<Objednavka> getEvidenceObjednavek() throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            String query = "SELECT * FROM `objednavky`";
            connection = databaze.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                Objednavka objednavka = new Objednavka(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                evidenceObjednavek.add(objednavka);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return evidenceObjednavek;
    }

    /**
     * Metoda pro vložení nové objednávky do DB, volá se v založení objednávky
     * při stisknutí tlačítka pro uložení.
     *
     * @param id
     * @param vychazka
     * @param zakaznik
     * @param stav
     * @throws SQLException
     */
    public void zalozObjednavku(String id, String vychazka, String zakaznik, String stav) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = databaze.getConnection();
            statement = connection.createStatement();
            String query = "INSERT INTO `objednavky` (`id`,`vychazka`,`zakaznik`,`stav`) VALUES ('"
                    + id + "', '" + vychazka + "', '" + zakaznik + "', '" + stav + "')";
            statement.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metoda pro úpravu objednávky, volá se v editaci objednávky při stisknutí
     * tlačítka pro uložení. ID upravit nelze.
     *
     * @param id
     * @param vychazka
     * @param zakaznik
     * @param stav
     * @throws SQLException
     */
    public void upravObjednavku(String id, String vychazka, String zakaznik, String stav) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = databaze.getConnection();
            statement = connection.createStatement();
            String query = "UPDATE `objednavky` SET `id` = '" + id + "', `vychazka` = '" + vychazka + "', `zakaznik` = '" + zakaznik
                    + "', `stav` = '" + stav + "' WHERE `objednavky`.`id` = " + id;
            statement.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metoda slouží pro získání údajů o konkrétní objednávce. Metoda se využíva
     * pro výpis v detailu a editaci objednávky.
     *
     * @param index
     * @return Objednavka
     * @throws SQLException
     */
    public Objednavka getObjednavku(int index) throws SQLException {
        evidenceObjednavek = getEvidenceObjednavek();
        objednavka = evidenceObjednavek.get(index);
        return objednavka;
    }

    /**
     * Metoda slouží pro získání seznamu objednávek z DB. Metoda se využíva pro
     * výpis v přehledu objednávek.
     *
     * @return ArrayList<String>
     * @throws SQLException
     */
    public ArrayList<String> getSeznamObjednavek() throws SQLException {
        evidenceObjednavek = getEvidenceObjednavek();
        for (Objednavka pom : evidenceObjednavek) {
            seznamObjednavek.add(pom.getPopis());
        }
        return seznamObjednavek;
    }

    /**
     * Metoda slouží pro získání zbylé kapacity u vycházky.
     *
     * @param nazev
     * @return int
     * @throws SQLException
     */
    public int getZbylaKapacita(String nazev) throws SQLException {
        evidenceObjednavek = getEvidenceObjednavek();
        evidenceVychazek = getEvidenceVychazek();
        int id = 0;
        for (Vychazka vychazkaPom : evidenceVychazek) {
            if (vychazkaPom.getNazev().equals(nazev)) {
                id = vychazkaPom.getId();
                break;
            }
        }
        vychazka = evidenceVychazek.get(id-1);
        int kapacita = vychazka.getKapacita();
        for (Objednavka objednavkaPom : evidenceObjednavek) {
            if (objednavkaPom.getVychazkaString().equals(nazev)) {
                kapacita--;
            }
        }
        return kapacita;
    }
    
    /**
     * Metoda slouží pro získání indexu zvolené položky pro potřeby získání detailních údajů.
     *
     * @param index
     */
    public void getIndex(int index){
    pomIndex = index;
    }
    
    /**
     * Metoda vrací index zvolené položky.
     *
     * @return int
     */
    public int getPomIndex(){
    return pomIndex;
    }
}
