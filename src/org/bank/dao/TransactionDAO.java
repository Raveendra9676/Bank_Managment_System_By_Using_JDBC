package org.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.bank.dto.CustomerDetails;
import org.bank.dto.TransactionDetails;
import org.bank.util.JdbcConnection;

public class TransactionDAO {

	/*
	 * Transaction_Id, Transaction_Type, Transaction_Amount, Transaction_Date,
	 * Transaction_Time, Account_Number, Balance_Amount, R_Account_Number
	 */
	private final static String insert = "insert into transaction_details(Transaction_Type, Transaction_Amount, Transaction_Date, Transaction_Time, Account_Number, Balance_Amount, R_Account_Number)"
			+ "values(?,?,?,?,?,?,?)";

	private final static String select_By_Using_Account_number = "select * from transaction_details where Account_Number=?";
	TransactionDetails transactionDetails = new TransactionDetails();

	public boolean insertTransaction(TransactionDetails transactiondetails) {
		try {
			Connection connection = JdbcConnection.mySqlConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setString(1, transactiondetails.getTransactiontype());
			preparedStatement.setDouble(2, transactiondetails.getTransactionamount());
			preparedStatement.setDate(3, Date.valueOf(transactiondetails.getTransactiondate()));
			preparedStatement.setTime(4, Time.valueOf(transactiondetails.getTransactiontime()));
			preparedStatement.setInt(5, transactiondetails.getAccountnumber());
			preparedStatement.setDouble(6, transactiondetails.getBalanceamount());
			preparedStatement.setInt(7, transactiondetails.getRaccountnumber());
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

	public List<TransactionDetails> selectByUsingAccountNumber(long accountNumber) {
		Connection connection;
		try {
			connection = JdbcConnection.mySqlConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(select_By_Using_Account_number);
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.isBeforeFirst()) {
				List<TransactionDetails> list = new ArrayList<TransactionDetails>();
				while (resultSet.next()) {
					TransactionDetails transactionDetails = new TransactionDetails();
					transactionDetails.setAccountnumber(resultSet.getInt("Account_Number"));
					transactionDetails.setTransactiontype(resultSet.getString("Transaction_Type"));
					transactionDetails.setBalanceamount(resultSet.getDouble("Transaction_Amount"));
					transactionDetails.setBalanceamount(resultSet.getDouble("Balance_Amount"));
					transactionDetails.setRaccountnumber(resultSet.getInt("R_Account_Number"));
					transactionDetails.setTransactiondate(resultSet.getDate("Transaction_Date").toLocalDate());
					transactionDetails.setTransactiontime(resultSet.getTime("Transaction_Time").toLocalTime());
					transactionDetails.setTransactionid(resultSet.getInt("Transaction_Id"));
					list.add(transactionDetails);
				}

				return list;
			} else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
