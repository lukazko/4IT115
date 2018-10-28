package main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.HerniPlan;
import util.ObserverZmenyProstoru;

 /**
 *  Třída OknoProstoru - definuje horní panel s mapu a tečkou.
 *
 *@author     Lukáš Kubík
 *@version    1.0
 *@created    ZS 2018
 */

public class OknoProstoru implements ObserverZmenyProstoru
{

    private HerniPlan plan;
    private AnchorPane horniAnchorPane;
    private Circle teckaCircle;

    /**
    * Konstruktor.
    * 
    * @param plan
    */
    
    public OknoProstoru(HerniPlan plan) {
        
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        nastavHorniPanel();
    
    }
    
    /**
    * Metoda vracející odkaz na horní panel GUI.
    */

    public AnchorPane getPanel() {
        
        return horniAnchorPane;
    
    }
    
    /**
    * Metoda zakládá horní panel hry, ve kterém se vyskytuje plánek a tečka ukazující pozici.
    */

    private void nastavHorniPanel() {
        
        horniAnchorPane = new AnchorPane();

        ImageView planekImageView = new ImageView(new Image(Adventura.class.getResourceAsStream("/zdroje/planek.png"), 750, 350, false, false));

        teckaCircle = new Circle(8, Paint.valueOf("red"));
        aktualizuj();

        horniAnchorPane.getChildren().add(planekImageView);
        horniAnchorPane.getChildren().add(teckaCircle);
    
    }

    /**
    * Metoda aktualizuje pozici červené tečky podle aktuálního prostoru.
    */    
    
    @Override
    public void aktualizuj() {
        
        AnchorPane.setTopAnchor(teckaCircle, plan.getAktualniProstor().getPosTop());
        AnchorPane.setLeftAnchor(teckaCircle, plan.getAktualniProstor().getPosLeft());
    
    }
    
    /**
    * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
    * 
    * @param plan
    */
    
    public void nastaveniHernihoPlanu (HerniPlan plan){
       
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        this.aktualizuj();
    
    }
}
