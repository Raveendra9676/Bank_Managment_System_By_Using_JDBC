package org.bank.service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bank.dao.CustomerDAO;
import org.bank.dao.TransactionDAO;
import org.bank.dto.CustomerDetails;
import org.bank.dto.TransactionDetails;

public class TransactionServices {
	Scanner sc = new Scanner(System.in);
	TransactionDAO transactionDAO = new TransactionDAO();
	CustomerDAO customerDAO = new CustomerDAO();

	public void addTransaction(TransactionDetails transactionDetails) {
		if (transactionDAO.insertTransaction(transactionDetails)) {
			System.out.println("inserted");
		} else {
			System.out.println("server 500");
		}
	}

	public void checkStatement(String emailOrMobile) {
		List<CustomerDetails> customerDetails = customerDAO.getAllCustomerDetails();

		List<CustomerDetails> list = customerDetails.stream()
				.filter((customer) -> customer.getCustomeremail().equals(emailOrMobile)
						|| (customer.getCustomermobilenumber() + "").equals(emailOrMobile))
				.collect(Collectors.toList());
		long accountNumber = list.get(0).getAccountnumber();
		List<TransactionDetails> list2 = transactionDAO.selectByUsingAccountNumber(accountNumber);
		if (list2 != null) {
			list2.stream().forEach((e) -> System.out.println(e));
		} else {
			System.out.println("No");
		}

	}

}
