package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void returnHome(ActionEvent event) {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Scenes/DashboardScene.fxml"));
		try {
			root = loader.load();
			DashboardSceneController dashboardSceneController = loader.getController();
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
