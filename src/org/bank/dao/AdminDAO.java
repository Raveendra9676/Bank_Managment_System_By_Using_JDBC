package org.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bank.util.JdbcConnection;

public class AdminDAO {

	private static final String admin_login = "select * from admin where Admin_Email_Id=? and Admin_Password=?";

	public boolean selectAdminDetailsByUsingEmailidAndPassword(String email, String password) {
		try {
			Connection connection = JdbcConnection.mySqlConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(admin_login);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
	}

}
