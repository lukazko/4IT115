package logika;
import java.util.*;

import util.ObserverZmenyProstoru;
import util.SubjektZmenyProstoru;

import java.util.ArrayList;
import java.util.List;
/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Lukáš Kubík
 *@version    1.00
 */
public class HerniPlan implements SubjektZmenyProstoru {    
    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    private Map<String, Spolecnik> seznamSpolecniku;
    private Batoh batoh;
    private boolean prohraStav = false;
    private String vypis="";
    private List<ObserverZmenyProstoru> seznamPozorovatelu;
    
    /**
    *   Konstruktor
    */
    public HerniPlan() {
        
        zalozProstoryHry();
        zalozSpolecniky();
        batoh = new Batoh();
        seznamPozorovatelu = new ArrayList<>();
    }
    
    /**
    *  Vytváří jednotlivé prostory a propojuje je pomocí východů. Vytváří věci.
    */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor roklinka = new Prostor("Roklinka","Roklince, elfském útočišti", 100.0, 40.0);
        Prostor rozcesti = new Prostor("Rozcesti","rozcestí, teď si musíš vybrat, kudy dál", 30.0, 95.0);
        Prostor prusmyk = new Prostor("Prusmyk", "nebezečném horském průsmyku, kterým lze přejít hory", 120.0, 105.0);
        Prostor morie = new Prostor("Morie","Morii, starých trpaslíčích síních pod horami", 110.0, 135.0);
        Prostor zelezny_pas = new Prostor("Zelezny_pas","Železném pasu, sídle zlého čaroděje Sarumana", 60.0, 250.0);
        Prostor lorien = new Prostor("Lorien","Lórienu, lese obývaném elfským národem", 170.0, 140.0);
        Prostor reka = new Prostor("Reka","Velké řece, dopluješ po ní dále na východ do Amon Henu", 290.0, 155.0);
        Prostor rohan = new Prostor("Rohan","Rohanu, zemi pánů koní ohrožované zlým čarodějem Sarumanem", 250.0, 220.0);
        Prostor amon_hen = new Prostor("Amon_Hen","Amon Henu, západní části pahorkatiny Emyn Muil", 420.0, 210.0);
        Prostor emyn_muil = new Prostor("Emyn_Muil","Emyn Muilu, skalnatém bludiště nedaleko Mordoru, číhá v něm Glum", 470.0, 170.0);
        Prostor cerna_brana = new Prostor("Cerna_brana","Černé bráně, hlavním vchodu do Mordoru, je pečlivě hlídaný skřety", 620.0, 150.0);
        Prostor hora_osudu = new Prostor("Hora_osudu","Hoře osudu, cíli výpravy, hoď prsten do lávy!", 680.0, 230.0);
        Prostor ithilien = new Prostor("Ithilien","Ithilienu, vybydlené zemi mezi Gondorem a Mordorem", 580.0, 230.0);
        Prostor minas_morgul = new Prostor("Minas_Morgul","Minas Morgul, černokněžném městě, v jehož blízkosti se nachází tajná cesta do Mordoru", 620.0, 275.0);
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        roklinka.setVychod(rozcesti);
        rozcesti.setVychod(morie);
        rozcesti.setVychod(roklinka);
        rozcesti.setVychod(zelezny_pas);
        rozcesti.setVychod(prusmyk);
        prusmyk.setVychod(rozcesti);
        zelezny_pas.setVychod(rozcesti);
        morie.setVychod(rozcesti);
        morie.setVychod(lorien);
        lorien.setVychod(reka);
        lorien.setVychod(rohan);
        lorien.setVychod(morie);
        rohan.setVychod(lorien);
        rohan.setVychod(amon_hen);
        reka.setVychod(amon_hen);
        reka.setVychod(lorien);
        amon_hen.setVychod(reka);
        amon_hen.setVychod(emyn_muil);
        amon_hen.setVychod(ithilien);
        emyn_muil.setVychod(cerna_brana);
        cerna_brana.setVychod(hora_osudu);
        cerna_brana.setVychod(emyn_muil);
        cerna_brana.setVychod(ithilien);
        ithilien.setVychod(amon_hen);
        ithilien.setVychod(cerna_brana);
        ithilien.setVychod(minas_morgul);
        minas_morgul.setVychod(ithilien);
        minas_morgul.setVychod(hora_osudu);
        hora_osudu.setVychod(cerna_brana);
        hora_osudu.setVychod(minas_morgul);
        
        //tvorba věcí a jejich umístění do prostorů
        Vec prsten = new Vec("Prsten", true, false);
        Vec kamen = new Vec("Kamen", true, false);
        Vec snih = new Vec("Kopa_snehu", false, false);
        Vec plast = new Vec("Neviditelny_plast", true, true);
        Vec lembas = new Vec("Lembas", true, false);
        Vec strom = new Vec("Strom", false, false);
        Vec ryba = new Vec("Ryba", true, false);
        Vec trava = new Vec("Trava", false, false);
        Vec prach = new Vec("Prach", false, false);
        roklinka.vlozVec(prsten);
        rozcesti.vlozVec(kamen);
        prusmyk.vlozVec(snih);
        lorien.vlozVec(plast);
        lorien.vlozVec(lembas);
        lorien.vlozVec(strom);
        reka.vlozVec(ryba);
        cerna_brana.vlozVec(prach);
        rohan.vlozVec(trava);
                
        aktualniProstor = roklinka;  // hra začíná v Roklince
        viteznyProstor = hora_osudu; //hra končí v Hoře osudu
    }
    
    /**
     * Metoda vytvoří společenstvo
     */
    private void zalozSpolecniky(){
        seznamSpolecniku = new HashMap<>();
        
        Spolecnik gandalf = new Spolecnik("Gandalf");
        seznamSpolecniku.put("Gandalf", gandalf);
        Spolecnik aragorn = new Spolecnik("Aragorn");
        seznamSpolecniku.put("Aragorn", aragorn);
        Spolecnik boromir = new Spolecnik("Boromir");
        seznamSpolecniku.put("Boromir", boromir);
        Spolecnik legolas = new Spolecnik("Legolas");
        seznamSpolecniku.put("Legolas", legolas);
        Spolecnik gimli = new Spolecnik("Gimli");
        seznamSpolecniku.put("Gimli", gimli);
        Spolecnik sam = new Spolecnik("Sam");
        seznamSpolecniku.put("Sam", sam);
        Spolecnik pipin = new Spolecnik("Pipin");
        seznamSpolecniku.put("Pipin", pipin);
        Spolecnik smisek = new Spolecnik("Smíšek");
        seznamSpolecniku.put("Smíšek", smisek);   
    }
    
    /**
     * Metoda obsahuje příběh, který se přidává k vypsanému textu v určitých místnostech, je využívána příkazem jdi, zároveň definuje špatné konce hry
     */
    public String pribeh(){
        vypis = "";
        if(getAktualniProstor().getNazev().equals("Prusmyk")) {
            vypis = "Sakra v průmyku spadla lavina, přežili jste jen o chlup\n"+
                    "Tudy to dál nepůjde, musíš vybrat jinou cestu ;)\n";
        }
        else if(getAktualniProstor().getNazev().equals("Zelezny_pas")) {
            if(existujeSpolecnik("Gandalf")) {
                vypis = "Zaútočil na vás zlý čaroděj Saruman\n"+
                        "Gandalf vás zachránil, ale zaplatil za to životem.\n"+
                        "Sem bych už nechodil ;)\n";
                odstranSpolecnika("Gandalf");
            }
            else {
                vypis = "Zaútočil na vás zlý čaroděj Saruman\n"+
                        "Gandalf tu nebyl, aby vás ochránil\n"+
                        "Všichni jste umřeli :(\n";
                setProhra(true);
            }
        }
        else if(getAktualniProstor().getNazev().equals("Morie")) {
            if(existujeSpolecnik("Gandalf")){
                vypis = "Přepadla vás zrůda z dávných časů, Balrog\n"+
                        "Gandalf vás zachránil, ale zaplatil za to životem.\n"+
                        "Honem pryč odtud!\n";
                odstranSpolecnika("Gandalf");
            }
            else {
                vypis = "Přepadla vás zrůda z dávných časů, Balrog\n"+
                        "Gandalf tu nebyl, aby vás ochránil\n"+
                        "Všichni jste umřeli :(\n";
                setProhra(true);
            }    
        }
        else if(getAktualniProstor().getNazev().equals("Rohan")) {
            if(existujeSpolecnik("Aragorn")) {
                vypis = "Potkali jste rohanské jezdce s ti vás požádali o pomoc\n"+
                        "v boji proti zlému čaroději Sarumanovi.\n"+
                        "Aragorn, Gimli, Legolas a Boromir tě opustili\n";
                odstranSpolecnika("Aragorn");
                odstranSpolecnika("Gimli");
                odstranSpolecnika("Legolas");
                odstranSpolecnika("Boromir");
            }   
        }
        else if(getAktualniProstor().getNazev().equals("Amon_Hen")) {
            if(existujeSpolecnik("Aragorn")) {
                vypis = "Ach! Počíhali si tu na vás skřeti\n"+
                        "Společenstvo tě ochránilo, ale většina za to zaplatila životem\n"+
                        "Pryč odtud!\n";
                odstranSpolecnika("Aragorn");
                odstranSpolecnika("Boromir");
                odstranSpolecnika("Legolas");
                odstranSpolecnika("Gimli");
                odstranSpolecnika("Smíšek");
                odstranSpolecnika("Pipin");
            }
            else {
                vypis = "Ach! Počíhali si tu na vás skřeti\n"+
                        "Členů společenstva nebylo dostatek, aby tě ubránili.\n"+
                        "Všichni jste umřeli :(\n";
                setProhra(true);
            }    
        }
        else if(getAktualniProstor().getNazev().equals("Minas_Morgul")) {
            if(existujeSpolecnik("Sam")) {
                vypis = "V tajném průchodu vás přepadl velký pavouk Odula\n"+
                        "Sam tě ochránil, ale zemřel při tom\n"+
                        "Cíl už je blízko, honem pryč z tohoto místa!\n";
                odstranSpolecnika("Sam");
            }
            else {
                vypis = "V tajném průchodu si na tebe počíhal velký pavouk Odula\n"+
                        "jelikož si na něj byl sám, zabil tě :(\n";
                setProhra(true);
            }    
        }
        else if(getAktualniProstor().getNazev().equals("Emyn_Muil")) {
            if(existujeSpolecnik("Sam")) {
                vypis = "V noci se k vám připlížil Glum a chtěl vás okrást o Prsten\n"+
                        "Sam tě ochránil, ale zemřel při tom.\n"+
                        "Cíl už je blízko, honem pryč z tohoto místa!\n";
                odstranSpolecnika("Sam");
            }
            else {
                vypis = "V noci se k tobě připlížil Glum\n"+
                        "podžízl tě ve spánku a sebral ti prsten :(\n";
                setProhra(true);
            }    
        }
        else if(getAktualniProstor().getNazev().equals("Cerna_brana")) {
            if(getBatoh().getVec("Neviditelny_plast").jeAktivni()) {
                vypis = "V elfském plášti se ti podařilo proklouznout Černou bránou\n"+
                        "Hora osudu už je nadohle\n"+
                        "Znič Prsten!\n";
            }
            else {
                vypis = "Co sis myslel?\n"+
                        "skřeti na stráži tě uviděli a zabili :(\n";
                setProhra(true);
            }    
        }
        return vypis;
    }
    
    /**
     * Odstraní společníka ze seznamu společníků, využívá metoda příběh
     */
    public void odstranSpolecnika(String jmeno){
        if(seznamSpolecniku.containsKey(jmeno)){
             seznamSpolecniku.remove(jmeno);
        }
    }
    
    /**
     * Hledá, zda se v seznamu nachází určitý společník, využívá metoda příběh, pokud ano vrací true
     */
    public boolean existujeSpolecnik(String hledany){         
        for (String jmeno : seznamSpolecniku.keySet()) {
            if (jmeno.equals(hledany)) {
                return true;
            }
        }     
        return false;
    }
    
    /**
     * Metoda pro získání odkazu na baťoh
     */
    public Batoh getBatoh() {
        return batoh;
    }
    
    /**
     * Metoda pro vypsání seznamu společníků, využívá příkaz společenstvo
     */
    public String getSpolecnici() {
        String seznam = "";
        for (String jmeno : seznamSpolecniku.keySet()) {
            seznam +=  " "+jmeno;
        }
        return seznam;
    }
    
    /**
     * Ověřuje, zda jsou splněny podmínky výhry
     */
    public boolean jeVyhra(){
        if(aktualniProstor == viteznyProstor){
            if(aktualniProstor.obsahujeVec("Prsten")){
                return true;
           }
            else{
                return false;
            }
        }
        else{ 
            return false;
        }
    }
    
    /**
     * Nastavuje stav prohry, využívá příběh
     */
    public void setProhra(boolean prohraStav){
        this.prohraStav = prohraStav;
    }
    
    /**
     * Oznamuje zda nastala prohra, defaultně vrací false
     */
    public boolean jeProhra(){
        return prohraStav;
    }
    
    /**
     * Získá odkaz na místnost v které se hráč momentálně nachází
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     * Nastavuje aktuální prostor, využívá příkaz jdi
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       upozorniPozorovatele();
    }
    
    /**
     * Metoda přiřadí pozorovatele
     */
    
    public void zaregistrujPozorovatele(ObserverZmenyProstoru pozorovatel)
      {
        seznamPozorovatelu.add(pozorovatel);
      }
    
    /**
     * Opak předchozí metody
     */
    
    public void odregistrujPozorovatele(ObserverZmenyProstoru pozorovatel)
      {
        seznamPozorovatelu.remove(pozorovatel);
      }
    
    /**
     * Předá pozorovateli aktuální data
     */
    
    public void upozorniPozorovatele()
      {
        for (ObserverZmenyProstoru pozorovatel : seznamPozorovatelu) {
            pozorovatel.aktualizuj();
        }
      }
}
