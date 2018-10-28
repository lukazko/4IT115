package logika;

import javafx.scene.control.ListView;

/**
 *
 * @author Bartoň & Kubík
 */

public class MonList
{
    
    private ILoc loc;
    ListView<String> list;
    
    public MonList(ILoc loc)
    {
        
        this.loc = loc;
        list = new ListView<>();

    }
    
    public ListView<String> getList()
    {
        
        return list;
        
    }
    
    public void update()
    {
        
        list.getItems().clear();
        String[][] monuments =  loc.getMonuments();
        
        for (int i = 0; i < monuments.length; i++)
        {
            
            String item = monuments[i][0];
            list.getItems().add(item);
                
        }
    }    
}
