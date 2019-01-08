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
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Aplikace;
import logika.Pruvodce;

/**
 * OknoDetailPruvodce
 *
 * Třída, která slouží jako FXML controller pro okno se zobrazením detailu
 * zvoleného průvodce.
 *
 * @author Lukáš, Pavel, Simona
 * @created ZS 2018/2019
 */
public class OknoDetailPruvodce implements Initializable {

    @FXML
    private MenuItem pruvodciItem1;

    @FXML
    private TextField telefonInput;

    @FXML
    private TextField prijmeniInput;

    @FXML
    private VBox rootPane;

    @FXML
    private Label vychazkaLabel311;

    @FXML
    private MenuBar menuBar1;

    @FXML
    private Button editovatButton;

    @FXML
    private Label vychazkaLabel21;

    @FXML
    private Label vychazkaLabel32;

    @FXML
    private Label vychazkaLabel11;

    @FXML
    private MenuItem objednavkyItem1;

    @FXML
    private Label vychazkaLabel31;

    @FXML
    private TextField emailInput;

    @FXML
    private Label vychazkaLabel1;

    @FXML
    private MenuItem oProgramuItem;

    @FXML
    private MenuItem zakazniciItem1;

    @FXML
    private TextField jmenoInput;

    @FXML
    private Button zpetButton;

    @FXML
    private Menu napoveda1;

    @FXML
    private TextField jazykyInput;

    @FXML
    private MenuItem napovedaItem;

    @FXML
    private Menu menu1;

    @FXML
    private TextField idInput1;

    @FXML
    private MenuItem vychazkyItem1;

    private Aplikace aplikace = new Aplikace();

    private Pruvodce pruvodce;

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
            pruvodce = new Pruvodce(aplikace.getPruvodce(index).getId(), aplikace.getPruvodce(index).getJmeno(), aplikace.getPruvodce(index).getPrijmeni(), aplikace.getPruvodce(index).getEmail(), aplikace.getPruvodce(index).getTelefon(), aplikace.getPruvodce(index).getJazyk());
        } catch (SQLException ex) {
            Logger.getLogger(OknoDetailPruvodce.class.getName()).log(Level.SEVERE, null, ex);
        }
        idInput1.setText(Integer.toString(pruvodce.getId()));
        jmenoInput.setText(pruvodce.getJmeno());
        prijmeniInput.setText(pruvodce.getPrijmeni());
        emailInput.setText(pruvodce.getEmail());
        telefonInput.setText(pruvodce.getTelefon());
        jazykyInput.setText(pruvodce.getJazyk());
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
     * Metoda při kliknutí na příslušné tlačítko změní scénu, aby šel příslušný
     * průvodce editovat
     *
     * @param event událost při které se má metoda provést
     * @throws Exception
     */
    @FXML
    public void editovatPruvodce(ActionEvent event) throws Exception {
        VBox pane = FXMLLoader.load(getClass().getResource("/zdroje/OknoEditovatDetailPruvodce.fxml"));
        rootPane.getChildren().setAll(pane);
    }

}
