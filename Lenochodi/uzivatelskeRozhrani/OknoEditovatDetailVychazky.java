package uzivatelskeRozhrani;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Aplikace;
import logika.Vychazka;

/**
 * OknoEditovatDetailVychazky
 *
 * Třída, která slouží jako FXML controller pro okno s editací vycházky.
 *
 * @author Lukáš, Pavel, Simona, Katerina
 * @created ZS 2018/2019
 */
public class OknoEditovatDetailVychazky implements Initializable {

    @FXML
    private Label vychazkaLabel;

    @FXML
    private ChoiceBox<String> inputPruvodce;

    @FXML
    private DatePicker datumInput;

    @FXML
    private Label vychazkaLabel311;

    @FXML
    private MenuBar menuBar1;

    @FXML
    private Label vychazkaLabel312;

    @FXML
    private TextField kapacitaInput;

    @FXML
    private Label vychazkaLabel32;

    @FXML
    private TextField cenaInput;

    @FXML
    private Label vychazkaLabel31;

    @FXML
    private Label vychazkaLabel1;

    @FXML
    private MenuItem oProgramuItem;

    @FXML
    private Label vychazkaLabel2;

    @FXML
    private Label vychazkaLabel3;

    @FXML
    private ChoiceBox<String> jazykyInput;

    @FXML
    private MenuItem zakazniciItem1;

    @FXML
    private TextField casZacatkuInput;

    @FXML
    private Button zpetButton;

    @FXML
    private Menu napoveda1;

    @FXML
    private MenuItem napovedaItem;

    @FXML
    private Menu menu1;

    @FXML
    private MenuItem pruvodciItem1;

    @FXML
    private TextField idInput;

    @FXML
    private VBox rootPane;

    @FXML
    private TextField nazevInput;

    @FXML
    private Button ulozitButton;

    @FXML
    private TextField mistoInput;

    @FXML
    private MenuItem objednavkyItem1;

    @FXML
    private MenuItem vychazkyItem1;

    private Aplikace aplikace = new Aplikace();

    private Vychazka vychazka;

    private Integer index;

    /**
     * Metoda naplňující grafické prvky daty z databáze při vytvoření scény.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jazykyInput.setItems(FXCollections.observableArrayList("CZ", "EN", "DE"));
        try {
            inputPruvodce.setItems(aplikace.getObservableListPruvodcu());
        } catch (SQLException ex) {
            Logger.getLogger(OknoEditovatDetailVychazky.class.getName()).log(Level.SEVERE, null, ex);
        }
        index = aplikace.getPomIndex();
        try {
            vychazka = new Vychazka(aplikace.getVychazku(index).getId(), aplikace.getVychazku(index).getNazev(), aplikace.getVychazku(index).getDatum(), aplikace.getVychazku(index).getCasZacatek(), aplikace.getVychazku(index).getMistoZacatek(), aplikace.getVychazku(index).getJazyk(), aplikace.getVychazku(index).getKapacita(), aplikace.getVychazku(index).getCena(), aplikace.getVychazku(index).getPruvodceJmeno());
        } catch (SQLException ex) {
            Logger.getLogger(OknoDetailPruvodce.class.getName()).log(Level.SEVERE, null, ex);
        }
        idInput.setText(Integer.toString(vychazka.getId()));
        idInput.setEditable(false);
        nazevInput.setText(vychazka.getNazev());
        datumInput.setValue(LocalDate.parse(vychazka.getDatum()));
        casZacatkuInput.setText(vychazka.getCasZacatek());
        mistoInput.setText(vychazka.getMistoZacatek());
        jazykyInput.setValue(vychazka.getJazyk());
        kapacitaInput.setText(Integer.toString(vychazka.getKapacita()));
        cenaInput.setText(Integer.toString(vychazka.getCena()));
        inputPruvodce.setValue(vychazka.getPruvodceJmeno());
    }

    /**
     * Metoda, která po zavolání vykreslí scénu s výpisem všech vycházek.
     *
     * @param event událost při které se má metoda provést
     * @throws Exception
     */
    @FXML
    public void loadVychazky(ActionEvent event) throws Exception {
        VBox pane = FXMLLoader.load(getClass().getResource("/zdroje/OknoVychazka.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Metoda, která po zavolání vykreslí scénu s výpisem všech objednávek.
     *
     * @param event událost při které se má metoda provést
     * @throws Exception
     */
    @FXML
    public void loadObjednavky(ActionEvent event) throws Exception {
        VBox pane = FXMLLoader.load(getClass().getResource("/zdroje/OknoObjednavka.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Metoda, která po zavolání vykreslí scénu s výpisem všech průvodců.
     *
     * @param event událost při které se má metoda provést
     * @throws Exception
     */
    @FXML
    public void loadPruvodci(ActionEvent event) throws Exception {
        VBox pane = FXMLLoader.load(getClass().getResource("/zdroje/OknoPruvodce.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Metoda, která po zavolání vykreslí scénu s výpisem všech zákazníků.
     *
     * @param event událost při které se má metoda provést
     * @throws Exception
     */
    @FXML
    public void loadZakaznici(ActionEvent event) throws Exception {
        VBox pane = FXMLLoader.load(getClass().getResource("/zdroje/OknoZakaznik.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Metoda, která po zavolání vrátí uživatele o krok zpět, tedy na detail
     * zvolené vycházky.
     *
     * @param event událost při které se má metoda provést
     * @throws Exception
     */
    public void detail(ActionEvent event) throws Exception {
        VBox pane = FXMLLoader.load(getClass().getResource("/zdroje/OknoDetailVychazky.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Metoda při kliknutí na příslušnou položku zobrazí modální okno s
     * informacemi o programu.
     *
     * @param t událost při které se má metoda provést
     */
    @FXML
    public void zobrazInfo(ActionEvent t) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Vycházky by LenochodiSoft");
        alert.setHeaderText("JavaFX grafická aplikace pro správu vycházek");
        alert.setContentText("Vytvořil tým Lenochodi v rámci předmětu 4IT115.\n"
                + "Aktuální verze 1.0");

        alert.showAndWait();
    }

    /**
     * Metoda při kliknutí na příslušnou položku zobrazí nové okno s html
     * nápovědou.
     *
     * @param t událost při které se má metoda provést
     */
    @FXML
    public void zobrazNapovedu(ActionEvent t) {

        Stage stage = new Stage();
        stage.setTitle("Nápověda k aplikaci");
        WebView webview = new WebView();
        webview.getEngine().load(
                getClass().getResource("/zdroje/napoveda.html").toExternalForm()
        );
        stage.setScene(new Scene(webview, 500, 500));
        stage.show();

    }

    /**
     * Metoda kontroluje, zda je hodnota v příslušeném poli typu integer.
     *
     * @param t textové pole s porovnávanou hodnotou
     * @return true/false zda porovnávané pole obsahuje int číslo
     */
    public static boolean isInteger(TextField t) {
        int x;
        try {
            x = Integer.parseInt(t.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Metoda voláná před ukládáním dat do databáze. Kontroluje vstupy, zda
     * splňují podmínky pro uložení, zobrazuje modální okna s informacemi o
     * průběhu ukládání.
     *
     * @param event událost při které se má metoda provést
     * @throws Exception
     */
    @FXML
    public void ulozitVychazku(ActionEvent event) throws Exception {
        if (isInteger(idInput) && isInteger(kapacitaInput) && isInteger(cenaInput)) {
            if (idInput.getText().trim().equals("")
                    || nazevInput.getText().trim().equals("")
                    || datumInput.getValue().toString().trim().equals("")
                    || casZacatkuInput.getText().trim().equals("")
                    || mistoInput.getText().trim().equals("")
                    || kapacitaInput.getText().trim().equals("")
                    || cenaInput.getText().trim().equals("")
                    || jazykyInput.getValue().trim().equals("")
                    || inputPruvodce.getValue().trim().equals("")) {
                zavolejAlert("Chyba", "Neočekávaná chyba ve vstupních datech");
            } else {
                String jazykPruvodce = inputPruvodce.getValue().substring(inputPruvodce.getValue().length() - 2);
                if (jazykyInput.getValue().equals(jazykPruvodce)) {
                    aplikace.upravVychazku(idInput.getText(), nazevInput.getText(), datumInput.getValue().toString(),
                            casZacatkuInput.getText(), mistoInput.getText(), jazykyInput.getValue(), kapacitaInput.getText(),
                            cenaInput.getText(), inputPruvodce.getValue());
                    zavolejAlert("Vycházka založena", "Vycházka byla úspěšně založena");
                    VBox pane = FXMLLoader.load(getClass().getResource("/zdroje/OknoDetailVychazky.fxml"));
                    rootPane.getChildren().setAll(pane);
                } else {
                    zavolejAlert("Chyba", "Jazyk průvodce se musí odpovídat jazyku vycházky");
                }
            }
        } else {
            zavolejAlert("Chyba", "Neočekávaná chyba ve vstupních datech");
        }
    }

    /**
     * Metoda pro volání Alertu aby v tom nebyl guláš a nebyli tu tisíckrát ty
     * samé řádky dokola
     *
     * @param title nadpis
     * @param content chyba
     */
    public void zavolejAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
