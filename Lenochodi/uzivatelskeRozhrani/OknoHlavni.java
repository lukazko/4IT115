package uzivatelskeRozhrani;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * OknoHlavni
 *
 * Třída, která slouží jako FXML controller pro startovní okno aplikace.
 *
 * @author Lukáš, Pavel
 * @created ZS 2018/2019
 */
public class OknoHlavni implements Initializable {

    @FXML
    private VBox rootPane;

    @FXML
    private MenuItem zakazniciItem;

    @FXML
    private Button zakazniciButton;

    @FXML
    private MenuItem pruvodciItem;

    @FXML
    private MenuItem objednavkyItem;

    @FXML
    private Button objednavkyButton;

    @FXML
    private Button pruvodciButton;

    @FXML
    private Menu menu;

    @FXML
    private Button vychazkyButton;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu napoveda;

    @FXML
    private MenuItem oProgramuItem;

    @FXML
    private ImageView pozadi;

    @FXML
    private MenuItem vychazkyItem;

    @FXML
    private MenuItem napovedaItem;

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
    }
}
