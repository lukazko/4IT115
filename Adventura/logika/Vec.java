/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.*;
/*******************************************************************************
 * Instance třídy Vec představují věci, které je možné potkat v jednotlivých prostorech.
 *
 * @author    Lukáš Kubík
 * @version   1.00
 */
public class Vec {
    private String nazev; 
    private boolean prenositelnost;
    private boolean prozkoumanost = false; 
    private boolean moznostPouzit;
    private boolean aktivni = false;
    
    /***************************************************************************
     *  Konstruktor nastavujeme nazev věci, zda je přenositelná a zda pro ní jde použít příkaz 'použij'
     */
    public Vec(String nazev, boolean prenositelnost, boolean moznostPouzit) {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.moznostPouzit = moznostPouzit;
    }
    
    /**
     * Metoda pro získání názvu věci
     * 
     * @return nazev věci
     */
    public String getNazev(){
        return nazev;
    }
    
    /**
     * Metoda, která vrátí, zda je věc přenositelná
     * 
     * @return přenositelnost ano/ne
     */
    public boolean jePrenositelna(){
        return prenositelnost;
    }
    
    /**
     * Metoda vracející zda je věc prozkoumana 
     * 
     * @return prozkoumanost ano/ne
     */
    public boolean jeProzkoumana() {
        return prozkoumanost;
    }
    
    /**
     * Metoda vracející zda je věc možno použít jako parametr v příkazu použij
     * 
     * @return použitelnost ano/ne
     */
    public boolean getMoznostPouzit(){
        return moznostPouzit;
    }
    
    /**
     * Metoda vracející, zda je vec momentalně používána na základě příkazu použij
     * 
     * @return aktivnost ano/ne
     */
    public boolean jeAktivni(){
        return aktivni;
    }
    
    /**
     * Metoda nastavujicí používání věci, využívá příkaz použij
     * 
     * @param aktivnost ano/ne
     */
    public void setAktivni(boolean aktivni){
        this.aktivni = aktivni;
    }
}