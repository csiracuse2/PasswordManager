module PasswordManager {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires org.apache.logging.log4j;
	requires javafx.base;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
}
