/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
/**
 * Instance třídy PrikazPouzij aktivaci jednotlivých předmětů v batohu
 * @author    Lukáš Kubík
 * @version   1.00
 */
public class PrikazPouzij implements IPrikaz
{
    private static final String NAZEV = "pouzij";
    private HerniPlan plan;
    private Batoh batoh;
    
    /**
     * Konstruktor
     */
    public PrikazPouzij(HerniPlan plan) {
        this.plan = plan;
        this.batoh = plan.getBatoh();
    }
    
    /**
     * Pokud je to to možné, aktivuje vybranou věc z batohu
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (název odhazované věci), tak ....
            return "Co mám použít? Musíš zadat jméno věci ;)";
        }

        String nazevAktivovaneVeci = parametry[0];
        Vec aktivovanaVec = batoh.getVec(nazevAktivovaneVeci);

        if (aktivovanaVec == null) {
            return "Nemůžeš použít něco, co nemáš ;)";            
        }
        else if(aktivovanaVec.jeAktivni()) {
            return "Tuto věc už používáš ;)";
        }
        else if(aktivovanaVec.getMoznostPouzit()==false) {
            return "Tahle věc bohužel nejde použít :(";
        }
        else {
            plan.getBatoh().getVec(nazevAktivovaneVeci).setAktivni(true);
            return "Aktivoval jsi " + nazevAktivovaneVeci + ". Teď si na tebe jen tak nepřijdou ;)";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}