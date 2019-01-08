package uzivatelskeRozhrani;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Aplikace;
import logika.Objednavka;

/**
 * OknoEditovatDetailObjednavky
 *
 * Třída, která slouží jako FXML controller pro okno s editací objednávky.
 *
 * @author Lukáš, Pavel, Simona
 * @created ZS 2018/2019
 */
public class OknoEditovatDetailObjednavky implements Initializable {

    @FXML
    private MenuItem pruvodciItem1;

    @FXML
    private TextField stavInput;

    @FXML
    private Label vychazkaLabel;

    @FXML
    private TextField idInput;

    @FXML
    private VBox rootPane;

    @FXML
    private MenuBar menuBar1;

    @FXML
    private Button ulozitButton;

    @FXML
    private ChoiceBox<String> zakaznikInput1;

    @FXML
    private MenuItem objednavkyItem1;

    @FXML
    private Label vychazkaLabel1;

    @FXML
    private MenuItem oProgramuItem;

    @FXML
    private Label vychazkaLabel2;

    @FXML
    private Label vychazkaLabel3;

    @FXML
    private MenuItem zakazniciItem1;

    @FXML
    private Menu napoveda1;

    @FXML
    private MenuItem napovedaItem;

    @FXML
    private ChoiceBox<String> vychazkaInput1;

    @FXML
    private Menu menu1;

    @FXML
    private MenuItem vychazkyItem1;

    @FXML
    private Button zpetButton;

    private Aplikace aplikace = new Aplikace();

    private Objednavka objednavka;

    private Integer index;

    /**
     * Metoda naplňující grafické prvky daty z databáze při vytvoření scény.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        index = aplikace.getPomIndex();
        try {
            objednavka = new Objednavka(aplikace.getObjednavku(index).getId(), aplikace.getObjednavku(index).getVychazkaString(), aplikace.getObjednavku(index).getZakaznikString(), aplikace.getObjednavku(index).getStav());
        } catch (SQLException ex) {
            Logger.getLogger(OknoDetailPruvodce.class.getName()).log(Level.SEVERE, null, ex);
        }
        idInput.setText(Integer.toString(objednavka.getId()));
        idInput.setEditable(false);
        try {
            vychazkaInput1.setItems(aplikace.getObservableListVychazek());
            zakaznikInput1.setItems(aplikace.getObservableListZakazniku());
        } catch (SQLException ex) {
            Logger.getLogger(OknoZalozitObjednavku.class.getName()).log(Level.SEVERE, null, ex);
        }
        vychazkaInput1.setValue(objednavka.getVychazkaString());
        zakaznikInput1.setValue(objednavka.getZakaznikString());
        stavInput.setText(objednavka.getStav());
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
     * zvolené objednávky.
     *
     * @param event událost při které se má metoda provést
     * @throws Exception
     */
    public void zpet(ActionEvent event) throws Exception {
        VBox pane = FXMLLoader.load(getClass().getResource("/zdroje/OknoDetailObjednavky.fxml"));
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
     * Metoda voláná před ukládáním dat do databáze. Kontroluje vstupy, zda
     * splňují podmínky pro uložení, zobrazuje modální okna s informacemi o
     * průběhu ukládání.
     *
     * @param event událost při které se má metoda provést
     * @throws Exception
     */
    @FXML
    public void ulozitObjednavku(ActionEvent event) throws Exception {

        if (aplikace.getZbylaKapacita(vychazkaInput1.getValue()) >= 0) {
            if (stavInput.getText().trim().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Chyba");
                alert.setHeaderText(null);
                alert.setContentText("Neočekávaná chyba ve vstupních datech");
                alert.showAndWait();
            } else {
                aplikace.upravObjednavku(idInput.getText(), vychazkaInput1.getValue(), zakaznikInput1.getValue(), stavInput.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Objednávka uložena");
                alert.setHeaderText(null);
                alert.setContentText("Změny byly úspěšně uloženy");
                alert.showAndWait();
                VBox pane = FXMLLoader.load(getClass().getResource("/zdroje/OknoDetailObjednavky.fxml"));
                rootPane.getChildren().setAll(pane);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chyba");
            alert.setHeaderText(null);
            alert.setContentText("Neočekávaná chyba ve vstupních datech");
            alert.showAndWait();
        }
    }

}
