package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAOImpl implements UserDAO {

	private static Connection con;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");

			con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "blackthorne");
			System.out.println("Connection established");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean login(User user) {

		try {
			PreparedStatement pst = con.prepareStatement("select password from login where username = ?");
			pst.setString(1, user.getUsername());
			ResultSet rs = pst.executeQuery();
			return (rs.next() && rs.getString(1).equals(user.getPassword())) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean register(User user) {
		try {
			PreparedStatement pst = con.prepareStatement("insert into login values(?,?)");
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			return (pst.executeUpdate() == 1) ? true : false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
