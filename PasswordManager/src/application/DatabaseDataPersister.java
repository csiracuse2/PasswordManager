package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDataPersister {
	
	/**
	 * Updates the encrypted password of a service
	 * 
	 * @param service
	 * @param encryptedPassword
	 */
	public static void updatePassword(Service service, String encryptedPassword) {
		
		Connection conn;
		PreparedStatement ps;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String query = "UPDATE Service "
					     + "SET encryptedPassword = ? "
					     + "WHERE serviceName = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, encryptedPassword);
			ps.setString(2, service.toString());
			ps.executeUpdate();
			
			ps.close();
			ConnectionFactory.close(conn);
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} catch (IllegalStateException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * Adds service to the database
	 * 
	 * @param serviceName
	 * @param encryptedPassword
	 */
	public static void addService(String serviceName, String encryptedPassword) {
		
		Connection conn;
		PreparedStatement ps;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String query = "INSERT INTO Service(serviceName, encryptedPassword) VALUES (?, ?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, serviceName);
			ps.setString(2, encryptedPassword);
			ps.executeUpdate();
			
			ps.close();
			ConnectionFactory.close(conn);
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} catch (IllegalStateException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * Deletes the service with the given name
	 * 
	 * @param serviceName
	 */
	public static void deleteService(String serviceName) {
		Connection conn;
		PreparedStatement ps;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String query = "DELETE FROM Service WHERE serviceName = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, serviceName);
			ps.executeUpdate();
			
			ps.close();
			ConnectionFactory.close(conn);
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} catch (IllegalStateException e) {
			throw new RuntimeException();
		}
	}
}
