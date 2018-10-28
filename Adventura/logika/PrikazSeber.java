/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
/*******************************************************************************
 * Instance třídy PrikazSeber představují příkazy pro sebrání věci z místnosti
 *
 * @author    Lukáš Kubík
 * @version   1.00
 */
public class PrikazSeber implements IPrikaz
{
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Batoh batoh;

    /***************************************************************************
     *  Konstruktor 
     */
    public PrikazSeber(HerniPlan plan)
    {
        this.plan = plan;
        this.batoh = plan.getBatoh();
    }
    
    /**
     * Pokud je to možné sebere požadovanou věc a vrátí text o svém počínání
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Co mám sebrat? Musíš zadat název věci ;)";
        }

        String nazevSbiraneVeci = parametry[0];  
        Prostor aktualniProstor = plan.getAktualniProstor();
        //Vec sbiranaVec  = aktualniProstor.najdiVec(nazevSbiraneVeci);
        //Batoh batoh = plan.getBatoh();
        
        if (aktualniProstor.obsahujeVec(nazevSbiraneVeci)) {
            Vec sbiranaVec = aktualniProstor.odeberVec(nazevSbiraneVeci);
            if(sbiranaVec == null) {
                return "Tohle ale nejde sebrat :(";
            }
            else {
                if(batoh.vlozVec(sbiranaVec)) {
                    batoh.upozorniPozorovatele();
                    return "Sebral si "+ nazevSbiraneVeci+". Snad ti bude k užitku :)";                  
                }
                else{
                  aktualniProstor.vlozVec(sbiranaVec);
                  return "Do batohu už toho nejde nacpat více :(";
                  
                }
            }
        }    
        else {
            return "Taková věc se tady nenachází :("; 
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
