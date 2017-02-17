package project_list;

/**
 *
 * @author sultatog
 */
public class ListItem 
{
    private String itemName;

    public ListItem(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() 
    {
        return itemName;
    }
    
}
