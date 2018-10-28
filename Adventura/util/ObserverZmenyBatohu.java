package util;


 /**
 *  Rozhraní ObserverZmenyBatohu - pužíváno pro aktualizaci pozorovatele.
 *
 *@author     Lukáš Kubík
 *@version    1.0
 *@created    ZS 2018
 */

public interface ObserverZmenyBatohu {

    /**
    * Metoda, ve které proběhne aktualizace pozorovatele,
    * je volána prostřednictvím metody upozorniPozorovatele z rozhraní SubjektZmenyProstoru
    * 
    */
    
    public void aktualizuj();
    
}
