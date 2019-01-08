package uzivatelskeRozhrani;

import java.net.URL;
import java.sql.SQLException;
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
 * OknoZalozitVychazku
 *
 * Třída, která slouží jako FXML controller pro okno se založením vycházky.
 *
 * @author Lukáš, Simona, Katerina
 * @created ZS 2018/2019
 */
public class OknoZalozitVychazku implements Initializable {

    @FXML
    private VBox rootPane;

    @FXML
    private ChoiceBox<String> inputPruvodce;

    @FXML
    private Label vychazkaLabel;

    @FXML
    private DatePicker datumInput;

    @FXML
    private MenuItem pruvodciItem;

    @FXML
    private Label vychazkaLabel311;

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
    private MenuBar menuBar;

    @FXML
    private Label vychazkaLabel1;

    @FXML
    private MenuItem oProgramuItem;

    @FXML
    private Label vychazkaLabel2;

    @FXML
    private Label vychazkaLabel3;

    @FXML
    private ChoiceBox<String> jazykInput;

    @FXML
    private TextField casZacatkuInput;

    @FXML
    private MenuItem vychazkyItem;

    @FXML
    private MenuItem napovedaItem;

    @FXML
    private MenuItem zakazniciItem;

    @FXML
    private TextField idInput;

    @FXML
    private TextField nazevInput;

    @FXML
    private MenuItem objednavkyItem;

    @FXML
    private TextField mistoInput;

    @FXML
    private Menu menu;

    @FXML
    private Menu napoveda;

    @FXML
    private VBox vychazky;

    @FXML
    private Button PotvrditButton;

    private Aplikace aplikace = new Aplikace();

    private Vychazka vychazka;

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
    public void potvrditVychazku(ActionEvent event) throws Exception 
    {
        if (isInteger(idInput) && isInteger(kapacitaInput) && isInteger(cenaInput)) 
        {
            if (idInput.getText().trim().equals("") 
                    || nazevInput.getText().trim().equals("") 
                    || datumInput.getValue().toString().trim().equals("") 
                    || casZacatkuInput.getText().trim().equals("") 
                    || mistoInput.getText().trim().equals("") 
                    || kapacitaInput.getText().trim().equals("") 
                    || cenaInput.getText().trim().equals("") 
                    || jazykInput.getValue().toString().trim().equals("") 
                    || inputPruvodce.getValue().toString().trim().equals("")) 
            {
                zavolejAlert("Chyba","Neočekávaná chyba ve vstupních datech");
            } 
            else 
            {
                String jazykPruvodce = inputPruvodce.getValue().substring(inputPruvodce.getValue().length() - 2);
                if(jazykInput.getValue().equals(jazykPruvodce))
                {
                aplikace.zalozVychazku(idInput.getText(), nazevInput.getText(), datumInput.getValue().toString()
                ,casZacatkuInput.getText(), mistoInput.getText(), jazykInput.getValue(), kapacitaInput.getText() 
                ,cenaInput.getText(), inputPruvodce.getValue());
                
                zavolejAlert("Vycházka založena","Vycházka byla úspěšně založena");
                VBox pane = FXMLLoader.load(getClass().getResource("/zdroje/OknoVychazka.fxml"));
                rootPane.getChildren().setAll(pane);
                }
                else
                {
                    zavolejAlert("Chyba","Jazyk průvodce se musí odpovídat jazyku vycházky");
                }
            }
        } 
        else 
        {
            zavolejAlert("Chyba","Neočekávaná chyba ve vstupních datech");
        }
    }
    
    /**
     * Metoda pro volání Alertu aby v tom nebyl guláš
     * a nebyli tu tisíckrát ty samé řádky dokola
     *
     * @param nadpis a chyba
     */
    public void zavolejAlert(String title,String content)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
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
        jazykInput.setItems(FXCollections.observableArrayList("CZ", "EN", "DE"));
        try {
            inputPruvodce.setItems(aplikace.getObservableListPruvodcu());
        } catch (SQLException ex) {
            Logger.getLogger(OknoZalozitVychazku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
