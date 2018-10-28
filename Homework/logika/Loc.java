package logika;

/**
 *
 * @author Bartoň & Kubík
 */

public class Loc implements ILoc
{

    private String name;    
    private String[][] monuments;    
    
    Loc(String name, String[]... itemNames)
    {
        
        this.name = name;
        this.monuments = itemNames;
          
    }
       
    @Override
    public String getName()
    {
        
        return name;
        
    }      

    @Override  
    public String[][] getMonuments()
    {
        
        return monuments;
        
    }
}
