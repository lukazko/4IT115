package uzivatelskeRozhrani;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Aplikace;
import logika.Objednavka;

/**
 * OknoZalozitObjednavku
 *
 * Třída, která slouží jako FXML controller pro okno se založením objednávky.
 *
 * @author Lukáš, Simona
 * @created ZS 2018/2019
 */
public class OknoZalozitObjednavku implements Initializable {

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
    private ChoiceBox<String> vychazkaInput;

    @FXML
    private MenuItem objednavkyItem1;

    @FXML
    private Label vychazkaLabel1;

    @FXML
    private Label vychazkaLabel2;

    @FXML
    private ChoiceBox<String> zakaznikInput;

    @FXML
    private Label vychazkaLabel3;

    @FXML
    private MenuItem zakazniciItem1;

    @FXML
    private MenuItem napovedaItem;

    @FXML
    private Menu napoveda1;

    @FXML
    private MenuItem oProgramuItem;

    @FXML
    private Menu menu1;

    @FXML
    private Button PotvrditButton;

    @FXML
    private MenuItem vychazkyItem1;

    private Aplikace aplikace = new Aplikace();

    private Objednavka objednavka;

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
    public void potvrditObjednavku(ActionEvent event) throws Exception {

        if (isInteger(idInput)) {
            if (idInput.getText().trim().equals("") || stavInput.getText().trim().equals("") || vychazkaInput.getValue().trim().equals("") || zakaznikInput.getValue().trim().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Chyba");
                alert.setHeaderText(null);
                alert.setContentText("Neočekávaná chyba ve vstupních datech");
                alert.showAndWait();
            } else {
                aplikace.zalozObjednavku(idInput.getText(), vychazkaInput.getValue(), zakaznikInput.getValue(), stavInput.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Objednávka založena");
                alert.setHeaderText(null);
                alert.setContentText("Objednávka byla úspěšně založena");
                alert.showAndWait();
                VBox pane = FXMLLoader.load(getClass().getResource("/zdroje/OknoObjednavka.fxml"));
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
     * Metoda naplňující grafické prvky daty z databáze při vytvoření scény.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            vychazkaInput.setItems(aplikace.getObservableListVychazek());
            zakaznikInput.setItems(aplikace.getObservableListZakazniku());
        } catch (SQLException ex) {
            Logger.getLogger(OknoZalozitObjednavku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
