package main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

 /**
 *  Třída Adventura - hlavní třída projektu.
 *  Používá se pro spuštění grafické a textové verze.
 *
 *@author     Lukáš Kubík
 *@version    1.0
 *@created    ZS 2018
 */

public class Adventura extends Application
{

    private IHra hra;
    private BorderPane borderPane;
    private TextArea centerTextArea;
    private FlowPane dolniFlowPane;
    private Label zadejPrikazLabel;
    private TextField prikazTextField;
    private OknoProstoru oknoProstoru;
    private PanelVychodu panelVychodu;
    private MenuBar menuBar;
    private OknoBatohu oknoBatohu;

    /**
    * Metoda zakládá GUI a umisťuje jednotlivé prvky.
    *
    */
    
    @Override
    public void start(Stage primaryStage)
      {

        hra = new Hra();
        borderPane = new BorderPane();

        nastavTextArea();

        borderPane.setCenter(centerTextArea);
        nastavDolniPanel();
        borderPane.setBottom(dolniFlowPane);
        oknoProstoru = new OknoProstoru(hra.getHerniPlan());
        borderPane.setTop(oknoProstoru.getPanel());
        panelVychodu = new PanelVychodu(hra.getHerniPlan());
        borderPane.setRight(panelVychodu.getList());
        initMenu();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar, borderPane);
        
        oknoBatohu = new OknoBatohu(hra);
        borderPane.setLeft(oknoBatohu);

        Scene scene = new Scene(vBox, 750, 620);

        primaryStage.setTitle("Cesta do Mordoru");
        primaryStage.setScene(scene);
        prikazTextField.requestFocus();
        primaryStage.show();
      
      }

    /**
    * Metoda zakládá horní menu a jeho prvky.
    *
    */
    
    private void initMenu()
      {
          
        menuBar = new MenuBar();
        Menu menuSoubor = new Menu("Soubor");
        ImageView ikonka = new ImageView(new Image(Adventura.class.getResourceAsStream(("/zdroje/new.gif"))));

        MenuItem novaHra = new MenuItem("Nová hra", ikonka);
        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        novaHra.setOnAction(new EventHandler<ActionEvent>() {
            
            public void handle(ActionEvent t) {
                
                hra = new Hra();
                oknoProstoru.nastaveniHernihoPlanu(hra.getHerniPlan());
                panelVychodu.nastaveniHernihoPlanu(hra.getHerniPlan());
                oknoBatohu.novaHra(hra);
                centerTextArea.setText(hra.vratUvitani());
                prikazTextField.requestFocus();

              }
        });

        MenuItem konec = new MenuItem("Konec");
        konec.setOnAction(new EventHandler<ActionEvent>() {
           
            public void handle(ActionEvent t) {
                
                System.exit(0);
              
            }
        });

        menuSoubor.getItems().addAll(novaHra, new SeparatorMenuItem(), konec);
        
        Menu menuNapoveda = new Menu("Nápověda");
        
        MenuItem oProgramu = new MenuItem("O programu");
        oProgramu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                
                // obsluha události O programu
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Cesta do Mordoru");
                alert.setHeaderText("JavaFX grafická adventura");
                alert.setContentText("Vytvořil Lukáš Kubík v rámci předmětů 4IT101 a 4IT115.\n"
                        + "Aktuální verze 2.0");

                alert.showAndWait();
            }
        });

        MenuItem napovedaKAplikaci = new MenuItem("Nápověda k aplikaci");
        napovedaKAplikaci.setAccelerator(KeyCombination.keyCombination("F1"));
        napovedaKAplikaci.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                
                // obsluha události Nápověda k aplikaci
                // sekundární okno
                Stage stage = new Stage();
                stage.setTitle("Nápověda k aplikaci");
                WebView webview = new WebView();
                webview.getEngine().load(
                        Adventura.class.getResource("/zdroje/napoveda.htm").toExternalForm()
                );
                stage.setScene(new Scene(webview, 500, 500));
                stage.show();
            
            }
        });

        menuNapoveda.getItems().addAll(oProgramu,new SeparatorMenuItem(), napovedaKAplikaci);
        menuBar.getMenus().addAll(menuSoubor, menuNapoveda);
      
      }
    
    /**
    * Metoda nastavuje textovou oblast ve kterém se zobrazují výstupy hry.
    *
    */

    private void nastavTextArea()
      {
       
        centerTextArea = new TextArea();
        centerTextArea.setText(hra.vratUvitani());
        centerTextArea.setEditable(false);
        
      }
    
    /**
    * Metoda nastavuje dolní panel.
    *
    */

    private void nastavDolniPanel()
      {
        
        dolniFlowPane = new FlowPane();
        zadejPrikazLabel = new Label("Zadej příkaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        prikazTextField = new TextField();
        prikazTextField.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
              {
                String radek = prikazTextField.getText();
                centerTextArea.appendText("\n" + radek + "\n");
                String text = hra.zpracujPrikaz(radek);
                centerTextArea.appendText("\n" + text + "\n");
                prikazTextField.setText("");
                if (hra.konecHry())
                    prikazTextField.setEditable(false);

              }

        });

        dolniFlowPane.getChildren().addAll(zadejPrikazLabel, prikazTextField);
        dolniFlowPane.setAlignment(Pos.CENTER);
      }

    /**
    * @param args the command line arguments
    */
    
    public static void main(String[] args)
      {
          
        if (args.length == 0)
            launch(args);
        else if (args[0].equals("-text")) {
            IHra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        } else
            System.out.println("Neplatny parametr");
        Platform.exit();

      }
}
