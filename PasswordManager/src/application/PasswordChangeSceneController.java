package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PasswordChangeSceneController extends Controller {
	
	private Service service;
	
	@FXML
	private Button saveButton;
	
	@FXML
	private TextField newPasswordTextField;
	
	public void setService(Service service) {
		this.service = service;
	}
	
	public void changePassword(ActionEvent event) {
		DatabaseDataPersister.updatePassword(service,  Cipher.encrypt(newPasswordTextField.getText()));
	}
}
