package org.bank.service;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bank.dao.AdminDAO;
import org.bank.dao.CustomerDAO;
import org.bank.dto.CustomerDetails;
import org.bank.exception.CustomerInvalidDataException;

public class Adminservices {

	Scanner sc = new Scanner(System.in);
	AdminDAO adminDAO = new AdminDAO();

	CustomerDAO customerDAO = new CustomerDAO();
	List<CustomerDetails> customerDetails = customerDAO.selectCustomerDetailsByUsingStatus();

	public void admnLogin() {
		System.out.println("Enter Admin Email");
		String emil = sc.next();
		System.out.println("Enter Password");
		String password = sc.next();
		if (adminDAO.selectAdminDetailsByUsingEmailidAndPassword(emil, password)) {
			System.out.println("Login Successful");
			System.out.println("Enter \n1 For Get All Customer Details" + "\n2 For Accept Pending Details"
					+ "\n3 For Get All Account Closing Requests");
			switch (sc.nextInt()) {
			case 1:
				System.out.println("All Customer Details");
				break;
			case 2:
				System.out.println("Accept Pending Details");
				allPendingDetails();
				acceptPendingDetails();

				break;
			case 3:
				System.out.println("Get All Account Closing Requests");
				break;

			default:
				break;
			}

		} else {
			System.out.println("Invalid details");
		}

	}

	public void allPendingDetails() {
		if (customerDetails != null) {
			Iterator<CustomerDetails> iterator = customerDetails.iterator();
			while (iterator.hasNext()) {
				CustomerDetails cd = iterator.next();
				System.out.println("Name  :" + cd.getCustomername());
				System.out.println("Email :" + cd.getCustomeremail());
				String num = "";
				num = num + cd.getCustomermobilenumber();
				System.out.println("Number:" + num.substring(0, 4) + "****" + num.substring(6, 10));
				System.out.println("Aadhar Number:" + cd.getAadharnumber());
				System.out.println("Gender:" + cd.getGender());
				System.out.println("Amount:" + cd.getAmount());
				System.out.println("Status:" + cd.getStatus());
				System.out.println("Account number:" + cd.getAccountnumber());
				System.out.println("******************************************************************");

			}
		} else {
			System.out.println("No Pending Users");
		}
	}

	public void acceptPendingDetails() {
		System.out.println("Enter Email of The User");
		String email = sc.next();

		List<CustomerDetails> list = customerDetails.stream()
				.filter((customer) -> customer.getCustomeremail().equals(email)).collect(Collectors.toList());
		CustomerDetails customerDetails = list.get(0);
		Random random = new Random();
		int accountnumber = 1000000 + random.nextInt(9000000);

		if (accountnumber < 1000000) {
			accountnumber = +1000000;
		}
		int pin = 1000 + random.nextInt(9000);
		if (pin < 1000) {
			pin = pin + 1000;
		}
		customerDetails.setAccountnumber(accountnumber);
		customerDetails.setPinnumber(pin);
		customerDetails.setIfsccode("M16BANK007");
		customerDetails.setStatus("Active");
		if (customerDAO.updateingValuesOfAccountNumberAndPin(customerDetails)) {
			System.out.println("details updated successfully");
			System.out.println("name:" + customerDetails.getCustomername());
			System.out.println("accountnumber:" + customerDetails.getAccountnumber());
			System.out.println("status:" + customerDetails.getStatus());
		}

	}

}
