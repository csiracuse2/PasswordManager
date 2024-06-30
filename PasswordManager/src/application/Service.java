package application;

public class Service {
	
	private String name;
	private String encryptedPassword;
	
	Service(String name, String encryptedPassword) {
		this.name = name;
		this.encryptedPassword = encryptedPassword;
	}
	
	public String toString() {
		return name;
	}
}
