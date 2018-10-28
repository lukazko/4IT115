package logika;

import java.util.ArrayList;

/**
 *
 * @author Bartoň & Kubík
 */

public class Monument implements IMonument
{

    private ILoc region;
    private ArrayList<ILoc> listOfRegions;
    private ArrayList<String> y;
    private String[] x;
    
    public Monument()
    {     
     
        listOfRegions = new ArrayList<ILoc>();
        
            Loc jihocesky = new Loc("Jihočeský", 
                    new String[]{"Červená Lhota", "/zdroje/jihocesky/cervena_lhota.jpg"},
                    new String[]{"Český Krumlov", "/zdroje/jihocesky/cesky_krumlov.jpg"},
                    new String[]{"Helfenburk", "/zdroje/jihocesky/helfenburk.jpg"},
                    new String[]{"Hluboká", "/zdroje/jihocesky/hluboka.jpg"},
                    new String[]{"Zvíkov", "/zdroje/jihocesky/zvikov.jpg"});   
                        
            Loc vysocina = new Loc("Vysočina", 
                    new String[]{"Chotěboř", "/zdroje/vysocina/chotebor.jpg"},
                    new String[]{"Kámen", "/zdroje/vysocina/kamen.jpg"},
                    new String[]{"Ledeč", "/zdroje/vysocina/ledec.jpg"},
                    new String[]{"Lipnice", "/zdroje/vysocina/lipnice.jpg"},
                    new String[]{"Pernštejn", "/zdroje/vysocina/pernstejn.jpg"});
            
            
            Loc zapadocesky = new Loc("Západočeský", 
                    new String[]{"Bor", "/zdroje/zapadocesky/bor.jpg"},
                    new String[]{"Kašperk", "/zdroje/zapadocesky/kasperk.jpg"},
                    new String[]{"Kynžvart", "/zdroje/zapadocesky/kynzvart.jpg"},
                    new String[]{"Loket", "/zdroje/zapadocesky/loket.jpg"},
                    new String[]{"Rabí", "/zdroje/zapadocesky/rabi.jpg"});
            
            registerRegion(jihocesky); 
            registerRegion(vysocina);
            registerRegion(zapadocesky);
    
    }
    
    public ILoc getRegion()
    {
        
        return region;
        
    }
    
    @Override
    public ArrayList<ILoc> getRegions()
    {
        
        return listOfRegions;
        
    }
    
    public void setRegion(ILoc loc)
    {
        
        region = loc;
        
    }
    
    public void registerRegion(Loc loc)
    {
        
        listOfRegions.add(loc);
        
    }
    
    @Override
    public String[] getRegionsNames()
    {
        
        y = new ArrayList<String>();
        
        for (int i = 0; i < getRegions().size(); i++)
        {
            
            ILoc item = getRegions().get(i);
            y.add(item.getName());
            
        }
        
        String[] x = new String[y.size()];
        x = y.toArray(x);
        
        return x;
        
    }
}
