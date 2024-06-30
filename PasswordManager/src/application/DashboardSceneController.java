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

public class DashboardSceneController extends Controller implements Initializable{
	
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
			serviceListView.getItems().add(service.toString());
		}
	}
	
	/**
	 * Switches the scene to the password view scene, where the password for a service is displayed.
	 * @param event
	 */
	@FXML
	public void switchToPasswordViewScene(ActionEvent event) {
		
		if(serviceListView.getSelectionModel().isEmpty()) {
			System.out.println("You must select a service first.");
			return;
		}
		
		String encryptedPassword = DatabaseDataLoader.getEncryptedPassword(serviceListView.getSelectionModel().getSelectedItem());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Scenes/PasswordViewScene.fxml"));
		System.out.println(encryptedPassword);
		try {
			root = loader.load();
			PasswordViewSceneController passwordSceneController = loader.getController();
			passwordSceneController.displayPassword(encryptedPassword);
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Switches the scene to the password change scene, where the password for a service can be changed by the user.
	 * @param event
	 */
	@FXML
	public void switchToPasswordChangeScene(ActionEvent event) {
		
		if(serviceListView.getSelectionModel().isEmpty()) {
			System.out.println("You must select a service first.");
			return;
		}
		
		Service service = DatabaseDataLoader.getServiceByName(serviceListView.getSelectionModel().getSelectedItem());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Scenes/PasswordChangeScene.fxml"));
		try {
			root = loader.load();
			PasswordChangeSceneController passwordChangeSceneController = loader.getController();
			passwordChangeSceneController.setService(service);
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Switches the scene to the password change scene, where the password for a service can be changed by the user.
	 * 
	 * @param event
	 */
	@FXML
	public void switchToServiceAddScene(ActionEvent event) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Scenes/ServiceAddScene.fxml"));
		try {
			root = loader.load();
			ServiceAddSceneController serviceAddSceneController = loader.getController();
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes the currently selected service
	 * @param event
	 */
	@FXML
	public void deleteService(ActionEvent event) {
		if(serviceListView.getSelectionModel().isEmpty()) {
			System.out.println("You must select a service first.");
			return;
		}
		
		String serviceName = serviceListView.getSelectionModel().getSelectedItem();
		
		DatabaseDataPersister.deleteService(serviceName);
		returnHome(event);
	}
}
