package org.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bank.dto.CustomerDetails;
import org.bank.util.JdbcConnection;

public class CustomerDAO {

	private static final String insert_customer_details = "insert into customer_details(Customer_Name, Customer_Email, Customer_Mobile_Number, "
			+ "Customer_Pan_Number, Customer_Aadhar_Number, Customer_Gender, Customer_Date_Of_Birth, Customer_Address, "
			+ "Customer_Amount, Customer_Status) values(?,?,?,?,?,?,?,?,?,?)";

	private final static String select_customer_details = "select * from customer_details";

	private final static String selec_customers_byusing_status = "select * from customer_details where Customer_Status=? ";

	private static final String set_values_by_using_emailid = "update customer_details set Customer_Account_Number=?,"
			+ "Customer_Pin=?,Bank_IFSC_Code=?,Customer_Status=? where Customer_Email=?";

	private final static String select_customer_details_byUsing_accountnumber = "select * from customer_details where Customer_Account_Number=?";

	private final static String update_balance = "update customer_details set Customer_Amount=? where  Customer_Account_Number=?";

	private final static String update_pin_by_Using_account_number = "update customer_details set Customer_Pin=? where Customer_Account_Number=?";

	public Boolean insertCustomerDeteils(CustomerDetails customerDetails) {
		try {
			Connection connection = JdbcConnection.mySqlConnection();
			PreparedStatement preparestatement = connection.prepareStatement(insert_customer_details);
			preparestatement.setString(1, customerDetails.getCustomername());
			preparestatement.setString(2, customerDetails.getCustomeremail());
			preparestatement.setLong(3, customerDetails.getCustomermobilenumber());
			preparestatement.setString(4, customerDetails.getPannumber());
			preparestatement.setLong(5, customerDetails.getAadharnumber());
			preparestatement.setString(6, customerDetails.getGender());
			preparestatement.setDate(7, customerDetails.getDateofbirth());
			preparestatement.setString(8, customerDetails.getAddress());
			preparestatement.setDouble(9, customerDetails.getAmount());
			preparestatement.setString(10, "pending");

			int n = preparestatement.executeUpdate();
			if (n > 0) {
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

	public List<CustomerDetails> getAllCustomerDetails() {
		try {
			Connection connection = JdbcConnection.mySqlConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(select_customer_details);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<CustomerDetails> listofcustomers = new ArrayList<CustomerDetails>();
			if (resultSet.isBeforeFirst()) {
				while (resultSet.next()) {
					CustomerDetails customerDetails = new CustomerDetails();
					customerDetails.setCustomername(resultSet.getString("Customer_Name"));
					customerDetails.setAadharnumber(resultSet.getLong("Customer_Aadhar_Number"));
					customerDetails.setCustomermobilenumber(resultSet.getLong("Customer_Mobile_Number"));
					customerDetails.setPannumber(resultSet.getString("Customer_Pan_Number"));
					customerDetails.setCustomeremail(resultSet.getString("Customer_Email"));
					customerDetails.setStatus(resultSet.getString("Customer_Status"));
					customerDetails.setAccountnumber(resultSet.getLong("Customer_Account_Number"));
					customerDetails.setAmount(resultSet.getDouble("Customer_Amount"));
					customerDetails.setIfsccode(resultSet.getString("Bank_IFSC_Code"));
					customerDetails.setPinnumber(resultSet.getInt("Customer_Pin"));
					customerDetails.setDateofbirth(resultSet.getDate("Customer_Date_Of_Birth"));
					customerDetails.setGender(resultSet.getString("Customer_Gender"));
					customerDetails.setAddress(resultSet.getString("Customer_Address"));
					customerDetails.setCustomerid(resultSet.getInt("Customer_Id"));
					listofcustomers.add(customerDetails);

				}
				return listofcustomers;
			} else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public List<CustomerDetails> selectCustomerDetailsByUsingStatus() {
		try {
			Connection connection = JdbcConnection.mySqlConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(selec_customers_byusing_status);
			preparedStatement.setString(1, "pending");
			ResultSet resultSet = preparedStatement.executeQuery();
			List<CustomerDetails> listofcustomers = new ArrayList<CustomerDetails>();
			if (resultSet.isBeforeFirst()) {
				while (resultSet.next()) {
					CustomerDetails customerDetails = new CustomerDetails();
					customerDetails.setCustomername(resultSet.getNString("Customer_Name"));
					customerDetails.setStatus(resultSet.getString("Customer_Status"));
					customerDetails.setAddress(resultSet.getString("Customer_Address"));
					customerDetails.setDateofbirth(resultSet.getDate("Customer_Date_Of_Birth"));
					customerDetails.setGender(resultSet.getString("Customer_Gender"));
					customerDetails.setAadharnumber(resultSet.getLong("Customer_Aadhar_Number"));
					customerDetails.setCustomermobilenumber(resultSet.getLong("Customer_Mobile_Number"));
					customerDetails.setPannumber(resultSet.getString("Customer_Pan_Number"));
					customerDetails.setCustomeremail(resultSet.getString("Customer_Email"));
					listofcustomers.add(customerDetails);

				}
				return listofcustomers;
			} else {
				return null;
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Boolean updateingValuesOfAccountNumberAndPin(CustomerDetails customerDetails) {

		try {
			Connection connection = JdbcConnection.mySqlConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(set_values_by_using_emailid);
			preparedStatement.setLong(1, customerDetails.getAccountnumber());
			preparedStatement.setInt(2, customerDetails.getPinnumber());
			preparedStatement.setString(3, customerDetails.getIfsccode());
			preparedStatement.setString(4, customerDetails.getStatus());
			preparedStatement.setString(5, customerDetails.getCustomeremail());
			int res = preparedStatement.executeUpdate();
			if (res != 0) {
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

	public CustomerDetails selectCustomerDetailsByUsingAccountNumber(int accountnumber) {
		try {
			Connection connection = JdbcConnection.mySqlConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(select_customer_details_byUsing_accountnumber);
			preparedStatement.setInt(1, accountnumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				CustomerDetails customerDetails = new CustomerDetails();
				customerDetails.setAmount(resultSet.getDouble("Customer_Amount"));
				customerDetails.setCustomeremail(resultSet.getString("Customer_Email"));
				customerDetails.setCustomermobilenumber(resultSet.getLong("Customer_Mobile_Number"));
				customerDetails.setAmount(resultSet.getDouble("Customer_Amount"));
				return customerDetails;
			} else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateBalanceByUsingAccountNumber(double amount, int accountNumber) {

		try {
			Connection connection = JdbcConnection.mySqlConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(update_balance);
			preparedStatement.setDouble(1, amount);
			preparedStatement.setInt(2, accountNumber);

			int res = preparedStatement.executeUpdate();
			if (res != 0) {
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

	public void updatepin(String emailormobilen, int newpin) {
		List<CustomerDetails> list = getAllCustomerDetails().stream()
				.filter((customer) -> customer.getCustomeremail().equals(emailormobilen)
						|| (customer.getCustomermobilenumber() + "").equals(emailormobilen))
				.collect(Collectors.toList());
		CustomerDetails customerDetails = list.get(0);
		Connection connection;
		try {
			connection = JdbcConnection.mySqlConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(update_pin_by_Using_account_number);
			preparedStatement.setInt(1, newpin);
			preparedStatement.setLong(2, customerDetails.getAccountnumber());
			int res = preparedStatement.executeUpdate();
			if (res == 1) {
				System.out.println("Pin Updated Sucessfully");
			} else {
				System.out.println("error");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
