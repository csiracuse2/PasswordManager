package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PasswordViewSceneController extends Controller {
	
	@FXML
	private Label passwordLabel;
	
	//TODO: Change this to display the unencrypted password
	public void displayPassword(String password) {
		passwordLabel.setText(password);
	}
}
