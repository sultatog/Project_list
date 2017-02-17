package project_list;

/**
 *
 * @author sultatog
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class ActionsList {

	File listFile = new File("list.txt");

	public List<ListItem> displayList() throws IOException {

		// Initialize a list to hold all items
		List<ListItem> list;
                list = new ArrayList<>();
                
                if (listFile.exists()) 
                {    
                    List<String> tmp = null;
                    // Read all items from listFile and store in list
                    tmp = FileUtils.readLines(listFile);
                    for(String str : tmp)
                    {
                        list.add(new ListItem(str));
                    }
                } 
                else 
                {
                    
                    return null;
                    
                }

                return list;
	}

	public boolean removeItem(ListItem input) {


		if (listFile.exists()) {
                        boolean itemFound = false;
			itemFound = RemoveItemFromFile.removeItemFromFile(listFile, input);
                        return itemFound;
		} else {

                        return false;
		}

	}

	public void addItem(ListItem input) throws IOException {

		String newItem = input.getItemName();

                FileUtils.write(listFile, newItem + "\n", true);
	}

	public boolean clearList() throws IOException {
            if (listFile.exists()) {
                
                // Force Delete the listFile to clear all items.
                FileUtils.forceDelete(listFile);
                return true;
            } else {
                
                return false;
            }
		
	}
	
}