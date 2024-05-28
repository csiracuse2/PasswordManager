package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class DashboardSceneController implements Initializable{
	
	@FXML
	private ListView<String> serviceListView;
	@FXML
	private Button viewButton, changeButton, addButton, deleteButton;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Service> serviceList = DatabaseDataLoader.loadServices();
		for(Service service : serviceList) {
			serviceListView.getItems().add(service.getName());
		}
	}
	
	/**
	 * Switches the scene to the password scene, where the password for a service will be displayed.
	 * @param event
	 */
	@FXML
	public void switchToPasswordScene(ActionEvent event) {
		
		if(serviceListView.getSelectionModel().isEmpty()) {
			System.out.println("You must select a service first.");
			return;
		}
		
		String encryptedPassword = DatabaseDataLoader.getEncryptedPassword(serviceListView.getSelectionModel().getSelectedItem());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Scenes/PasswordScene.fxml"));
		System.out.println(encryptedPassword);
		try {
			root = loader.load();
			PasswordSceneController passwordSceneController = loader.getController();
			passwordSceneController.displayPassword(encryptedPassword);
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
