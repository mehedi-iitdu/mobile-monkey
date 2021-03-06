package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class NetworkDelayController implements Initializable{
	
	HashMap<String, Integer> hmap = new HashMap<String, Integer>(); 
	
	ObservableList<String> firstItems = FXCollections.observableArrayList();
	
	@FXML
    private ListView<String> firstListView;
	
	@FXML
	ChoiceBox<String> value;
	
	@FXML
	TextField delay;
	
	@FXML
	public void handleSaveAction() throws IOException{
	
		File file = new File(FxController.networkdelay);
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    
	    for(String str : firstItems){
	    	
	    	writer.write(str+"\n");
	    }
	    
	    writer.close();
	}
	
	@FXML
	public void handleAddAction(){
		if(Integer.parseInt(delay.getText().toString()) < 0 || Integer.parseInt(delay.getText().toString()) > 10){
			
			return;
		}
		
		else{
			firstItems.add(hmap.get(value.getSelectionModel().getSelectedItem()) +
					"," + delay.getText().toString() +
							"," + "NetworkDelay");
			firstListView.setItems(firstItems);
		}
	}
	
	@FXML
	public void handleDeleteAction(){
		firstItems.remove(firstListView.getSelectionModel().getSelectedItem());
		firstListView.setItems(firstItems);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		FileInputStream fis = null;
        BufferedReader reader = null;
        
        try {
			
			fis = new FileInputStream(FxController.networkdelay);
            reader = new BufferedReader(new InputStreamReader(fis));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
        	String myLine;
        	while ( (myLine = reader.readLine()) != null){
        		firstItems.add(myLine);
        	}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		hmap.put("GSM", 0);
		hmap.put("EDGE", 1);
		hmap.put("UTMS", 2);
		hmap.put("NONE", 3);
		
        firstListView.setItems(firstItems);
		
		value.getItems().add("GSM");
		value.getItems().add("EDGE");
		value.getItems().add("UTMS");
		value.getItems().add("NONE");
	}

}
