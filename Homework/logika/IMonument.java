package logika;

import java.util.ArrayList;

/**
 *
 * @author Bartoň & Kubík
 */

public interface IMonument
{    
   
    public ILoc getRegion();
    public ArrayList<ILoc> getRegions();
    public String[] getRegionsNames();
    
}
