/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
/**
 * Instance třídy PrikazZahod představují příkazy pro vyřazení věci z batohu
 * @author    Lukáš Kubík
 * @version   1.00
 */
public class PrikazZahod implements IPrikaz
{
    private static final String NAZEV = "zahod";
    private HerniPlan plan;
    private Batoh batoh;
    
    /**
     * Konstruktor
     */
    public PrikazZahod(HerniPlan plan) {
        this.plan = plan;
        this.batoh = plan.getBatoh();
    }
    
    /**
     * Vyhodí věc z batohu a zanechá na součaném místě, o svém počínání vypíše hlášení
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (název odhazované věci), tak ....
            return "Co mám zahodit? Musíš zadat jméno věci ;)";
        }

        String nazevOdhazovaneVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec odhazovanaVec = batoh.getVec(nazevOdhazovaneVeci);

        if (odhazovanaVec == null){
            return "Takovou věc v batohu nemáš ;)";            
        }
        else {
            plan.getBatoh().getVec(nazevOdhazovaneVeci).setAktivni(false);
            plan.getBatoh().zahodVec(nazevOdhazovaneVeci);
            aktualniProstor.vlozVec(odhazovanaVec);
            batoh.upozorniPozorovatele();
            //aktualniProstor.najdiVec(nazevOdhazovaneVeci).setAktivni(false);
            return "Zahodil jsi zde " + nazevOdhazovaneVeci + ". Snad ti nebude chybět :O";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}