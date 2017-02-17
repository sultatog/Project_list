package project_list;

/**
 *
 * @author sultatog
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RemoveItemFromFile 
{
    
	public static boolean removeItemFromFile(File listFile, ListItem input) 
        {
            boolean toReturn = false;
		try 
                {
                    //lineToRemove is an item
			String lineToRemove = input.getItemName();

			// Check if the file path supplied is an actual file, otherwise throw and error
			if (!listFile.isFile()) 
                        {
				System.out.println("Path is not valid!");
				return toReturn;
			}

			// Construct the new file that will later be renamed to the original filename.
			File tempFile = new File(listFile.getAbsolutePath()+ ".tmp");

                    // Create new BufferedReader br and PrintWriter pw instances to handle various file operations
                            try(BufferedReader br = new BufferedReader(new FileReader(listFile)); 
                            PrintWriter pw = new PrintWriter(new FileWriter(tempFile))) 
                    {
                        
                        // Create a string to hold the lines read by the BufferedReader.
                        String line = null;
                        
                        // Read from the original file and write to the new unless content matches data to be removed.
                        while ((line = br.readLine()) != null) 
                        {
                            
                            // Check if the item read is the same as the item name entered in removeLabel.
                            if (!line.trim().equals(lineToRemove)) 
                            {
                                
                                pw.println(line);
                                pw.flush();
                            }
                            else
                                toReturn = true;
                        }
                        
                    }

			// Delete the original file
			if (!listFile.delete()) {
				System.out.println("Error,could not delete file");
				return toReturn;
			}

			// Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(listFile))
                        {
				System.out.println("Error,could not rename file");
                        }
		}

		catch (FileNotFoundException ex) 
                {
                    System.out.println("File is not found!");
                    System.exit(1);
		}

		catch (IOException ex) 
                {
                    System.out.println("Error occured!");
                    System.exit(2);
		}
                return toReturn;
	}

}