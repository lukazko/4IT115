/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.*;
/**
 * Třída PrikazSpolecenstvo
 * @author    Lukáš Kubík
 * @version   1.00
 */
public class PrikazSpolecenstvo implements IPrikaz {
    private static final String NAZEV = "spolecenstvo";
    private HerniPlan plan;
    
    /**
     * Konstruktor
     */
    public PrikazSpolecenstvo(HerniPlan plan) {
        this.plan = plan;
    }
    
    /**
     * Vypíše aktuální členy společenstva
     */
    @Override
    public String provedPrikaz(String... parametry) {       
        if(plan.getSpolecnici().equals("")) {
            return "Ajaj, ze Společenstva si zbyl jen ty :O";
        }    
        else {
            return "Společenstvo momentálně tvoří: "+plan.getSpolecnici();
        }
    }
    
    @Override
    public String getNazev() {
        return NAZEV;
    }
}