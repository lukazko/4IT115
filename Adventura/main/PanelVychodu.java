package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import logika.HerniPlan;
import logika.Prostor;
import util.ObserverZmenyProstoru;

 /**
 *  Třída PanelVychodu - obsahuje definici bočního panelu, který zobrazuje sousední východy.
 *
 *@author     Lukáš Kubík
 *@version    1.0
 *@created    ZS 2018
 */

public class PanelVychodu implements ObserverZmenyProstoru {

    private HerniPlan plan;
    ListView<String> list;
    ObservableList<String> data;
    
    /**
    * Konstruktor.
    *
    *@param plan
    */

    public PanelVychodu(HerniPlan plan)
      {
          
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        init();
        
      }
    
    /**
    * Metoda nastavuje obsah seznamu východů.
    */

    private void init()
      {
          
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(150);
        aktualizuj();
        
      }

    /**
    * Metoda vrací aktuální východy.
    */    

    @Override
    public void aktualizuj() {
          
        data.clear();
        for (Prostor vychod : plan.getAktualniProstor().getVychody()) {
            data.add(vychod.getNazev());
        }
        
    }
    
    /**
    * Metoda získá seznam východů.
    */

    public ListView<String> getList() {
          
        return list;
        
    }
    
    /**
    * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
    * @param plan
    */
    
    public void nastaveniHernihoPlanu (HerniPlan plan){
        
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        this.aktualizuj();
    
    }
}
