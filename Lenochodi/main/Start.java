package main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Start
 *
 * Hlavní třída, která spouští aplikaci a nastavuje výchozí scénu.
 *
 * @author Kateřina, Lukáš, Marko, Pavel, Simona
 * @created ZS 2018/2019
 */
public class Start extends Application {

    /**
     * Metoda, která vykreslí startovní okno aplikace
     *
     * @param stage zakládaná scéna
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/zdroje/OknoHlavni.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Vycházky by LenochodiSoft");
        stage.show();
    }

    /**
     * Spouštěcí metoda
     *
     * @param args případné argumenty v příkazové řádce
     */
    public static void main(String[] args) {
        launch(args);
    }
}
