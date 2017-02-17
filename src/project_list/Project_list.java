package project_list;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author sultatog
 */
public class Project_list extends Application 
{
    ActionsList actionsList;
    @Override
     public void start(Stage primaryStage) 
    {
        actionsList = new ActionsList();
                
        Text text = new Text();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        Label addLabel = new Label("Add new item:");
        GridPane.setConstraints(addLabel,0,0);
        
        TextField addInput = new TextField();
        GridPane.setConstraints(addInput, 1, 0);
       
        Label removeLabel = new Label("Remove item(all duplicates):");
        GridPane.setConstraints(removeLabel,0,1);
        
        TextField removeInput = new TextField();
        GridPane.setConstraints(removeInput, 1, 1);
        
        GridPane.setConstraints(text, 0, 4);
        grid.getChildren().add(text);
        
        Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent event) -> 
        {
            try {
                text.setText("");
                actionsList.addItem(new ListItem(addInput.getText()));
                text.setText(addInput.getText()+" added to list");
                System.out.println(addInput.getText()+" added to list");
            } catch (IOException ex) {
                Logger.getLogger(Project_list.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        GridPane.setConstraints(addButton, 2, 0);
        
        
        Button removeButton = new Button("Remove");
        removeButton.setOnAction((ActionEvent event) -> 
        {
            text.setText("");
            boolean itemFound = false;
            itemFound = actionsList.removeItem(new ListItem(removeInput.getText()));
            if(itemFound == true)
            {
                text.setText(removeInput.getText()+" removed from list.");
                System.out.println(removeInput.getText()+" removed from list.");
            }
            else
            {
                text.setText("Can't remove from list.");
                System.out.println("Can't remove from list.");
            }
        });
        GridPane.setConstraints(removeButton, 2, 1);
        
        Button removeAll = new Button("Remove all items");
        removeAll.setOnAction((ActionEvent event) ->
        {
            try {
                boolean fileExist = false;
                text.setText("");
                fileExist = actionsList.clearList();
                if(fileExist)
                {
                    text.setText("List is empty now!");
                    System.out.println("List is empty now!");
                }
                else
                {
                    text.setText("The file does not exist,\nplease create a new List!");
                    System.out.println("The file does not exist,\nplease create a new List!");
                }
            } catch (IOException ex) {
                Logger.getLogger(Project_list.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        GridPane.setConstraints(removeAll, 1, 3);
        
        Button displayAll = new Button("Display all items");
        displayAll.setOnAction((ActionEvent event) ->
        {
            try {
                String str = new String();
                text.setText("");
                
                List<ListItem> tmp  = actionsList.displayList();
                if(tmp != null)
                {
                for (ListItem listItem : tmp)
			if (listItem.getItemName() != null)
				str+=listItem.getItemName()+"\n";
                
                if(str.length()>0)
                {
                    text.setText("Here's the List : \n"+str);
                    System.out.println("Here's the List : \n"+str);
                }
                else
                {
                    text.setText("Nothing to show");
                    System.out.println("Nothing to show");
                }
                }
                else
                {
                    text.setText("The file does not exist,\n please create a new List!");
                    System.out.println("The file does not exist, please create a new List!");
                }
            } catch (IOException ex) {
                Logger.getLogger(Project_list.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        GridPane.setConstraints(displayAll, 0, 3);
        
        
        grid.getChildren().addAll(addLabel,addInput,
                                  removeLabel,removeInput,
                                  addButton,removeButton,
                                  removeAll,displayAll);
        
        Scene scene = new Scene(grid, 450, 400);
        
        
        primaryStage.setTitle("My program");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
