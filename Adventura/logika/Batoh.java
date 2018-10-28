/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.*;
import util.ObserverZmenyBatohu;
import util.SubjektZmenyBatohu;
/*******************************************************************************
 * Instance třídy Batoh představují seznam věci, které si hráč nese s sebou skrze mapu
 *
 * @author    Lukáš Kubík
 * @version   1.00
 */
public class Batoh implements SubjektZmenyBatohu{
    private Map<String, Vec> veciVBatohu;   //Seznam věcí v batohu.
    private static final int KAPACITA = 3;   //Maximální počet věcí v batohu.
    
    private List<ObserverZmenyBatohu> seznamPozorovatelu;
    
    /***************************************************************************
     *  Konstruktor zakládá mapu, kterou tvoří seznam věcí v batohu
     */
    public Batoh() {
        veciVBatohu = new HashMap<>();
        seznamPozorovatelu = new ArrayList<>();
        
    }
    
    /**
     * Získání odkazu na věc za použití názvu věci
     * 
     * @param název věci
     * @return odkaz na věc
     */
    public Vec getVec(String nazev) {
        return veciVBatohu.get(nazev);
    }
    
    /**
     * Vrací, zda je možné na základě kapacity do batohu umístit další věc
     * 
     * @return máme ještě místo ano/ne
     */
    public boolean neniPlny() {
        return (veciVBatohu.size() < KAPACITA);
    }
    
    /**
     * Vrací jestli věc byla, nebo nebyla vložena do baťohu
     * 
     * @param věc
     * @return vložena ano/ne
     */
    public boolean vlozVec(Vec neco) {
        if(neniPlny()){
            veciVBatohu.put(neco.getNazev(), neco);
            return true;
        }
        return false;
    }
    
    /**
     * Vrací jestli věc byla, nebo nebyla odstraněna z batohu
     * 
     * @param nazev věci
     * @return zahozena ano/ne
     */
    public boolean zahodVec(String neco) {
        boolean zahozeno = false;
            if(veciVBatohu.containsKey(neco)){
             zahozeno = true;
             veciVBatohu.remove(neco);
            }
        return zahozeno;  
    }  
    
    /**
     * Vypisuje věci, které obsahuje batoh
     * 
     * @return obsah batohu
     */
    public String obsahBatohu() {
        String obsah = "";
        for (String nazev : veciVBatohu.keySet()) {
            obsah +=  " "+nazev;
        }
        return obsah;
    }
    
    public Set<Vec> seznamVeciVBatohu () {        
        Set<Vec> veci = new HashSet<>();
        for (String nazev : veciVBatohu.keySet()) {                
            Vec v = veciVBatohu.get(nazev);
            veci.add(v);
        }
        return veci;
    }
    
    /**
     * Metoda přiřadí pozorovatele
     */
    
    @Override
        public void zaregistrujPozorovatele(ObserverZmenyBatohu pozorovatel)
      {
        seznamPozorovatelu.add(pozorovatel);
      }
        
     /**
     * Opak předchozí metody
     */

    @Override
    public void odregistrujPozorovatele(ObserverZmenyBatohu pozorovatel)
      {
        seznamPozorovatelu.remove(pozorovatel);
      }
    
    /**
     * Předá pozorovateli aktuální data
     */

    public void upozorniPozorovatele()
      {
        for (ObserverZmenyBatohu pozorovatel : seznamPozorovatelu) {
            pozorovatel.aktualizuj();
        }
      }

}