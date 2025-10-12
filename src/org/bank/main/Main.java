package org.bank.main;

import java.util.Scanner;

import org.bank.service.Adminservices;
import org.bank.service.CustomerService;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Adminservices adminservices = new Adminservices();
		CustomerService customerService = new CustomerService();
		System.out.println("***** Welcome to M16 Bank *********");
		System.out.println("Enter \n1 For Customer Login \n2 For Customer Register \n3 For Admin Login");

		switch (sc.nextInt()) {
		case 1:
			System.out.println("Customer Login");
			customerService.checkforlogin();

			break;
		case 2:

			customerService.customerRegistration();

			break;
		case 3:
			System.out.println("Admin Login");
			adminservices.admnLogin();

			break;

		default:
			System.out.println("Invalid Request");
			break;
		}

	}

}
