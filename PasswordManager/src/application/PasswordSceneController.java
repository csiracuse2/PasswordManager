package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PasswordSceneController {
	
	@FXML
	private Label passwordLabel;
	
	//TODO: Change this to display the unencrypted password
	public void displayPassword(String password) {
		passwordLabel.setText(password);
	}
}
