package main;

import logika.MonList;
import logika.Monument;
import java.util.Random;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logika.ILoc;

/**
 *
 * @author Bartoň & Kubík
 */

public class KdeDomovMuj extends Application
{

    private BorderPane borderPane;
    private Monument region;
    private MonList monList;
    private String monument;
    private AnchorPane img;

    @Override
    public void start(Stage primaryStage) 
    {
       
        Random random = new Random(); 
        region = new Monument();
        
        Button button = new Button();
        HBox hbox = new HBox();
        borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 530, 470);
        ToggleGroup group = new ToggleGroup();
        
        Image def = new Image("/zdroje/default.jpg");
        ImageView defView = new ImageView(def);
        
        GridPane header = new GridPane();   
        img = new AnchorPane();
        borderPane.setLeft(img);
        img.getChildren().add(defView);
      
        String[] regions = region.getRegionsNames();
        RadioButton[] buttons = new RadioButton[regions.length];
        
        for (int i = 0; i < regions.length; i++)
        {
            buttons[i] = new RadioButton(regions[i]);
            buttons[i].setToggleGroup(group);
            hbox.getChildren().add(buttons[i]);
            
            img.getChildren().clear();
            img.getChildren().add(defView);
            int chosen = i;
            
            buttons[i].setOnAction(new EventHandler<ActionEvent>()
            {

                @Override
                public void handle(ActionEvent event)
                { 
                    
                    borderPane.setRight(null);
                    borderPane.setLeft(null);
                    img.getChildren().clear();
                    img.getChildren().add(defView);
                    borderPane.setLeft(img);
                    
                    ILoc list = region.getRegions().get(chosen);
                    region.setRegion(list);
                    String[][] monuments =  region.getRegion().getMonuments();
                                      
                }
            });
        }
        
        button.setText("Hádej");
        Label label2 = new Label("Vyber kraj: ");
        header.add(hbox, 1, 0);
        header.add(label2, 0, 0);
        header.add(button, 0, 1);
        hbox.setSpacing(10);
        header.setHgap(10);
        header.setVgap(10);
        header.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setTop(header);
        
        button.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                
                    monList = new MonList(region.getRegion());
                    monList.update();
                    ListView list = monList.getList();
                    
                    list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
                    {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
                        {
                            if(newValue.equals(monument))
                            {  
                                
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Výhra!");
                                alert.setHeaderText("Blahopřeji!");
                                alert.setContentText("Pravý vlastenec se hned pozná.");
                                alert.showAndWait();

                            }else
                            {     
                                
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Prohra");
                                alert.setHeaderText("Bohužel vedle");
                                alert.setContentText("Tady asi někdo zaspal na vlastivědě.");
                                alert.showAndWait();                                     
                            
                            }
                            
                            borderPane.setRight(null);
                            borderPane.setLeft(null);
                            img.getChildren().clear();
                            img.getChildren().add(defView);
                            borderPane.setLeft(img);
                            
                        }                    
                    });
                    
                    borderPane.setRight(list);
                    img.getChildren().clear();                    
                    String[][] monuments =  region.getRegion().getMonuments();
                    int  rnd = random.nextInt(region.getRegion().getMonuments().length);
                    ImageView imgView = new ImageView(new Image(KdeDomovMuj.class.getResourceAsStream(monuments[rnd][1])));
                    monument = monuments[rnd][0];
                    img.getChildren().add(imgView);
                    
            }
        });

        primaryStage.setTitle("Kde domov můj");
        primaryStage.setScene(scene);
        primaryStage.show();
    
    }
    
    public static void main(String[] args) {
        
        launch(args);
    
    }
}
