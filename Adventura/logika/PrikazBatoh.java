/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
/**
 * Třída PrikazBatoh
 * @author    Lukáš Kubík
 * @version   1.00
 */
public class PrikazBatoh implements IPrikaz
{
    private static final String NAZEV = "batoh";
    private HerniPlan plan;
    
    /**
     * KOnstruktor
     */
    public PrikazBatoh(HerniPlan plan) {
        this.plan = plan;
    }
    
    /**
     * Vypíše věci v baťohu
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (plan.getBatoh().obsahBatohu().equals("")) {
            return "Tvůj batoh zeje prázdnotou :(";
        }
        else {
            return "V tvém příručním zavazadle se nachází: " +plan.getBatoh().obsahBatohu(); 
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}