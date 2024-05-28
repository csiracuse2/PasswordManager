package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class DatabaseDataLoader {
	
	/**
	 * Loads all services from the database
	 * @return
	 */
	public static ArrayList<Service> loadServices() {
		
		ArrayList<Service> serviceList = new ArrayList<>();
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		try {
			conn = ConnectionFactory.getConnection();
			
			String query = "SELECT serviceName, encryptedPassword FROM Service";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String serviceName = rs.getString("serviceName");
				String encryptedPassword = rs.getString("encryptedPassword");
				serviceList.add(new Service(serviceName, encryptedPassword));
			}
			
			rs.close();
			ps.close();
			ConnectionFactory.close(conn);
			
			return serviceList;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public static String getEncryptedPassword(String service) {
		
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		try {
			conn = ConnectionFactory.getConnection();
			
			String query = "SELECT encryptedPassword FROM Service where serviceName = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, service);
			rs = ps.executeQuery();
			
			if(!rs.next()) {
				throw new IllegalArgumentException();
			}
			
			String encryptedPassword = rs.getString("encryptedPassword");
			
			rs.close();
			ps.close();
			ConnectionFactory.close(conn);
			
			return encryptedPassword;

		} catch (SQLException e) {
			throw new RuntimeException();
		} catch (IllegalArgumentException e) {
			throw new RuntimeException();
		}
	}
}
