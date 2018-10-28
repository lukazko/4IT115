package logika;
import java.util.*;
/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * @author Lukáš Kubík
 * @version 1.00
 */
public class Prostor {
    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veci; //věci v jednotlivých prostorech
    //private Collection<Vec> neprozkoumaneVeci;
    private double posLeft;
    private double posTop;
    
    /**
     * Konstruktor, vytvoření prostoru se zadaným názvem, popisem, seznamem východů a věcí v něm
     */
    public Prostor(String nazev, String popis, double posLeft, double posTop) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<Prostor>();
        veci = new HashMap<>();
        this.posLeft = posLeft;
        this.posTop = posTop;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      
    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v " + popis + ".\n"
                + popisVychodu() +"\n";
                //+ popisVeci();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    public String popisVychodu() {
        String vracenyText = "východy:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }
    
    /*private String popisVeci() {
        String vracenyText = "věci:";
        for (Vec vec : veci) {
            if(vec.jeProzkoumana() == true){
                vracenyText += veci ;
            }
            else{
                vracenyText += " Nejprve to tu prouzkoumej ;)"; 
            }
        }
        
        return vracenyText;
    }*/
    //seznam neprozkoumaných věcí v místnosti
    public String getNeprozkoumaneVeci() {
        String neprozkoumaneVeci = "";
        for (String nazev : veci.keySet()) {
            if(veci.get(nazev).jeProzkoumana()==false) {
               neprozkoumaneVeci += " "+nazev;
            }
        }

        return neprozkoumaneVeci;
    }
    
    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        if (nazevSouseda == null) {
            return null;
        }
        for ( Prostor sousedni : vychody ){
            if (sousedni.getNazev().equals(nazevSouseda)) {
                return sousedni;
            }
        }
        return null;  // prostor nenalezen
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    /**
     * Přidá věc do místnosti a po přidání vrátí true
     * 
     * @param vkládaná věc
     * @return zda byla vec vlozena
     */
    public boolean vlozVec(Vec neco) {
       veci.put(neco.getNazev(), neco);
       return true;
    }
    
    /**
     * Hleda vec v místnosti, vrátí odkaz na ní
     * 
     * @param jméno hledané věci
     * @return odkaz na věc
     */
    public Vec najdiVec(String nazevVeci) {    
        return veci.get(nazev);
    }
    
    /**
     * Prozkoumava mistnost jestli se v ni nachází hledaná věc, pokud ano vrátí true
     * 
     * @param jmeno hledane
     * @return existence veci v mistnosti
     */
    public boolean obsahujeVec(String hledana) {         
        for (String nazev : veci.keySet()) {
            if (nazev.equals(hledana)) {
                return true;
            }
        }     
        return false;
    }
    
    /**
     * Metoda odebere podle názvu věc z místnosti a vrátí odkaz na ni
     * 
     * @param jmeno veci
     * @return odkaz na odebranou vec, nebo null
     */
    public Vec odeberVec(String neco) {
        Vec odebirana = null;
        for (String nazev : veci.keySet()) {
            if (nazev.equals(neco)& veci.get(nazev).jePrenositelna()){
                odebirana = veci.get(nazev);
                veci.remove(nazev);
                break;
            }    
        }
        
        return odebirana;
    }
    
    public double getPosLeft() {
        return posLeft ;
    }
    
    public double getPosTop() {
        return posTop;
    }
}
