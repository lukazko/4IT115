/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.*;
/*******************************************************************************
 * Instance třídy PrikazProzkoumej představují 
 *
 * @author    Lukáš Kubík
 * @version   1.00
 */
public class PrikazProzkoumej implements IPrikaz {
    private static final String NAZEV = "prozkoumat";
    private HerniPlan plan;

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazProzkoumej(HerniPlan plan)
    {
        this.plan = plan;
    }
    
    /**
     * Vypíše jaké předměty se nacházejí v místnosti
     */
    @Override
    public String provedPrikaz(String...parametry){
        String vracenyText;
        String veci = plan.getAktualniProstor().getNeprozkoumaneVeci();
        
        if (veci.equals("")) {
            vracenyText = "Nic jsi nenašel :(";
        }
        else {
            vracenyText = "Našel jsi:" +veci+". To by se mohlo hodit, ne?";
        }
       
        return vracenyText;
    }
    
    @Override
    public String getNazev() {
         return NAZEV;
    }
}