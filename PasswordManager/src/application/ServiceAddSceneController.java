package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class ServiceAddSceneController extends Controller{
	
	@FXML
	private TextField serviceNameTextField;
	
	@FXML
	private TextField passwordTextField;
	
	private Alert a = new Alert(AlertType.NONE);
	
    EventHandler<ActionEvent> event = new
                        EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            a.setAlertType(AlertType.INFORMATION);
            a.setContentText("Fields must not be blank");
            a.show();
        }
    };
	
	public void addService(ActionEvent event) {
		String serviceName = serviceNameTextField.getText();
		String password = passwordTextField.getText();
		//TODO: Encrypt the password before passing through this function
		if(!serviceName.isBlank() && !password.isBlank()) {
			DatabaseDataPersister.addService(serviceName, password);
		} else {
			this.event.handle(null);
		}
	}
}
