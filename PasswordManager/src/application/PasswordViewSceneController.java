package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PasswordViewSceneController extends Controller {
	
	@FXML
	private Label passwordLabel;
	
	public void displayPassword(String encryptedPassword) {
		passwordLabel.setText(Cipher.decrypt(encryptedPassword));
	}
}
