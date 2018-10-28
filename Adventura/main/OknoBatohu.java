package main;

import java.text.Normalizer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logika.IHra;
import logika.Vec;
import util.ObserverZmenyBatohu;

/**
 * Třída OknoBatohu - definuje část GUI, ve které se vypisuje obsah batohu
 *
 * @author      Lukáš Kubík
 * @version     1.0
 * @created     ZS 2018
 */

public class OknoBatohu extends ListView implements ObserverZmenyBatohu {
    
    private IHra hra;
    private ObservableList<String> dataBatohu = FXCollections.observableArrayList();
    
    /**
    * Konstruktor
    */
   
    public OknoBatohu (IHra hra) {
        
        super();  
        this.hra = hra;
        
        init();
    
    }
    
    /**
    * Resetuje obsah batohu při spuštění nové hry
    * @param hra 
    */
    
    public void novaHra(IHra hra) {
        
        this.hra.getHerniPlan().getBatoh().odregistrujPozorovatele(this);
        this.hra = hra;
        this.hra.getHerniPlan().getBatoh().zaregistrujPozorovatele(this);
        aktualizuj();
    
    }
    
    /**
    * Metoda inicializuje grafický obsah batohu.
    */
    
    public void init() {
        this.setItems(dataBatohu);
        this.setPrefWidth(200);
        hra.getHerniPlan().getBatoh().zaregistrujPozorovatele(this);
              
        this.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();                                                
            
            @Override
            public void updateItem(String name, boolean empty) {
                
                super.updateItem(name, empty);                                
                
                if (empty) {
                    
                    setText(null);
                    setGraphic(null);
                
                } else {
                    
                    String imageNazev = Normalizer.normalize(name, Normalizer.Form.NFD);
                    imageNazev = imageNazev.replaceAll("[^\\p{ASCII}]", "");
                    imageView.setImage(new Image("/zdroje/predmety/"+imageNazev+".jpg"));
                    setText(name);
                    setGraphic(imageView);
                
                }
            }
        });   
                
        aktualizuj();
              
    }
    
    /**
    * Metoda vyčístí obsah batohu a naplní aktuálními položkami.
    */

    @Override
    public void aktualizuj() {
        
        dataBatohu.clear();
        for(Vec vec : hra.getHerniPlan().getBatoh().seznamVeciVBatohu()) {
            
            dataBatohu.add(vec.getNazev());
        
        }                                              
    }    
}