package org.bank.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bank.dao.CustomerDAO;
import org.bank.dto.CustomerDetails;
import org.bank.dto.TransactionDetails;
import org.bank.exception.CustomerInvalidDataException;

public class CustomerService {

	Scanner sc = new Scanner(System.in);

	CustomerDAO customerDAO = new CustomerDAO();
	TransactionServices transactionServices = new TransactionServices();

	public void customerRegistration() {
		List<CustomerDetails> allcustomerdetails = customerDAO.getAllCustomerDetails();
		CustomerDetails customerdetails = new CustomerDetails();
		System.out.println("Enter Customer Name");
		String cname = sc.next();
		if (cname.matches("[a-zA-Z]+"))
			System.out.println("Enter The Customer Email");
		while (true) {
			String cemail = sc.next();
			try {
				if (cemail.length() > 10 && cemail.endsWith("@gmail.com")) {
					Long emailcoount = allcustomerdetails.stream()
							.filter((customer) -> customer.getCustomeremail().equalsIgnoreCase(cemail)).count();
					try {
						if (emailcoount > 0) {
							throw new CustomerInvalidDataException("Email is existed");
						} else {
							customerdetails.setCustomeremail(cemail);
							break;

						}
					} catch (CustomerInvalidDataException e) {
						System.out.println(e);
						System.out.println("re enter the emilid");
					}

				} else {
					throw new CustomerInvalidDataException("Email is Invalid");
				}

			}

			catch (CustomerInvalidDataException e) {
				System.out.println(e);
				System.out.println("enter the email id");
			}
		}

//		while (true) {
//			String cemail = sc.next();
//			if (cemail.length() > 10 && cemail.endsWith("@gmail.com")) {
//				Long emailcoount = allcustomerdetails.stream()
//						.filter((customer) -> customer.getCustomeremail().equalsIgnoreCase(cemail)).count();
//				try {
//					if (emailcoount > 0) {
//						throw new CustomerInvalidDataException("Email is existed");
//					} else {
//						customerdetails.setCustomeremail(cemail);
//						break;
//
//					}
//				}
//				catch (CustomerInvalidDataException e) {
//					System.out.println(e);
//					System.out.println("re enter the email");
//					
//
//				}
//				
//
//			}
//			}

		System.out.println("Enter Customer Mobile Number");

		long cmobilenumber = sc.nextLong();

		if (!(cmobilenumber >= 6000000000l && cmobilenumber <= 9999999999l)) {
			throw new CustomerInvalidDataException("Invalid Mobile Number");

		} else {
			Long cmobilecount = allcustomerdetails.stream()
					.filter((customer) -> customer.getCustomermobilenumber() == cmobilenumber).count();
			if (cmobilecount > 0) {
				throw new CustomerInvalidDataException("Mobile Number is existed");
			} else {
				customerdetails.setCustomermobilenumber(cmobilenumber);
			}
		}
		System.out.println("Enter Customer Aadhar Number");
		long caadharnumber = sc.nextLong();
		if (caadharnumber >= 100000000000l && caadharnumber <= 999999999999l) {
			customerdetails.setAadharnumber(caadharnumber);

		} else {
			throw new CustomerInvalidDataException("Invalid Aadhar Number");
		}
		System.out.println("Enter Customer PAN Number");
		String cpannumber = sc.next();
		if (cpannumber.length() == 10) {
			for (int i = 0; i <= 4; i++) {
				char ch = cpannumber.charAt(i);

				if (ch < 'A' || ch > 'Z') {
					throw new CustomerInvalidDataException("Invalid Pan Number");
				}
			}
			for (int i = 5; i <= cpannumber.length() - 2; i++) {
				char ch = cpannumber.charAt(i);

				if (ch < '0' || ch > '9') {
					throw new CustomerInvalidDataException("Invalid Pan Number");
				}
			}
			char last = cpannumber.charAt(9);

			if (last < 'A' && last > 'Z') {
				throw new CustomerInvalidDataException("Invalid Pan Number");
			}
			customerdetails.setPannumber(cpannumber);

		} else {
			throw new CustomerInvalidDataException("Invalid Pan Number");
		}
		System.out.println("Enter Customer Gender");
		String cgender = sc.next();
		if ((cgender.equalsIgnoreCase("Male") || cgender.equalsIgnoreCase("female")
				|| cgender.equalsIgnoreCase("others"))) {
			customerdetails.setGender(cgender);

		} else {
			throw new CustomerInvalidDataException("Invalid Gender");
		}

		System.out.println("Enter Customer Date Of Birth");
		String dob = sc.next();
		System.out.println("Enter Customer Address");
		String caddress = sc.next();
		System.out.println("Enter Amount");
		double amount = sc.nextDouble();
		if (amount <= 0) {
			throw new CustomerInvalidDataException("Invalid Amount");

		} else {
			customerdetails.setAmount(amount);
		}

		customerdetails.setCustomername(cname);
		customerdetails.setDateofbirth(Date.valueOf(dob));
		customerdetails.setAddress(caddress);
		if (customerDAO.insertCustomerDeteils(customerdetails)) {
			System.out.println("Registraction Sucessfull");
		} else {
			System.out.println("error 500");
		}

	}

	public void checkforlogin() {
		System.out.println("Enter Your Email or Mobile Number ");
		String emailOrMobile = sc.next();

		List<CustomerDetails> allcustomerdetails = customerDAO.getAllCustomerDetails();
		Boolean cudtomer_exist = false;
		for (CustomerDetails cd : allcustomerdetails) {

			if (emailOrMobile.equals(cd.getCustomeremail())
					|| emailOrMobile.equals(cd.getCustomermobilenumber() + "")) {
				cudtomer_exist = true;

				if (!(cd.getStatus().equalsIgnoreCase("pending"))) {
					System.out.println("enter the pin");
					int pin = sc.nextInt();
					if (cd.getPinnumber() == pin) {
						System.out.println("Account Login Sucessfully");
						customerOperations(emailOrMobile);
					} else {
						System.out.println("Invalid Pin Number");
					}

				} else {
					System.out.println("Account is still in Pending ");
				}
			}

		}
		if (!cudtomer_exist) {
			System.out.println("invalid Email or Mobile");
		}

	}

	public void customerOperations(String emailOrMobile) {

		while (true) {
			System.out.println(
					"Select one option\n1 fro Debit \n2For Credit \n3 For Check Balance \n4 For Check Statement \n5 For Mobile Transition \n6 For Change Pin \n7 For Closing Account");
			switch (sc.nextInt()) {
			case 1:
				System.out.println("Debit");
				debitOperation(emailOrMobile);

				break;
			case 2:
				System.out.println("Credit");
				creditOperetion(emailOrMobile);

				break;
			case 3:
				System.out.println("Check Balance");

				break;
			case 4:
				System.out.println("Check Statement");
				transactionServices.checkStatement(emailOrMobile);

				break;
			case 5:
				System.out.println("Mobile Transation");

				break;
			case 6:
				System.out.println("Change For Pin ");
				changePin(emailOrMobile);

				break;
			case 7:
				System.out.println("For Closing Account");

				break;
			default:
				System.out.println("select Valid Option");
				break;
			}

			System.out.println("do you want to continoue press 1");
			if (sc.nextInt() != 1) {
				break;
			}
		}
	}

	public void debitOperation(String emailOrMobile) {
		System.out.println("Enter Account Number");
		int accountNumber = sc.nextInt();
		CustomerDetails customerDetails = customerDAO.selectCustomerDetailsByUsingAccountNumber(accountNumber);

		if (customerDetails != null && (customerDetails.getCustomeremail().equals(emailOrMobile)
				|| ((customerDetails.getCustomermobilenumber() + "").equals(emailOrMobile))))

		{
			System.out.println("Enter Amount");
			double uamount = sc.nextDouble();
			double databaseamount = customerDetails.getAmount();
			if (uamount <= databaseamount) {
				double balance = databaseamount - uamount;
				if (customerDAO.updateBalanceByUsingAccountNumber(balance, accountNumber)) {
					System.out.println("Amount Debit Sucessfully");
					TransactionDetails transactionDetails = new TransactionDetails();
					transactionDetails.setTransactiontype("Debit");
					transactionDetails.setAccountnumber(accountNumber);
					transactionDetails.setTransactionamount(uamount);
					transactionDetails.setTransactiondate(LocalDate.now());
					transactionDetails.setTransactiontime(LocalTime.now());
					transactionDetails.setBalanceamount(balance);
					transactionDetails.setRaccountnumber(accountNumber);
					transactionServices.addTransaction(transactionDetails);
				} else {
					System.out.println("server issue");

				}

			} else {
				System.out.println("Insuffiecent Funds");
			}

		} else {
			System.out.println("Account Number is Invalid");
		}
	}

	public void creditOperetion(String emailOrMobile) {
		System.out.println("Enter Account Number");
		int accountNumber = sc.nextInt();
		CustomerDetails customerDetails = customerDAO.selectCustomerDetailsByUsingAccountNumber(accountNumber);

		if (customerDetails != null && (customerDetails.getCustomeremail().equals(emailOrMobile)
				|| ((customerDetails.getCustomermobilenumber() + "").equals(emailOrMobile)))) {
			System.out.println("Enter The Amount");
			double uamount = sc.nextDouble();
			if (uamount > 0) {
				double balance = uamount + customerDetails.getAmount();
				if (customerDAO.updateBalanceByUsingAccountNumber(balance, accountNumber)) {
					System.out.println("Amount Credited Sucessfully");
					TransactionDetails transactionDetails = new TransactionDetails();
					transactionDetails.setTransactiontype("Credit");
//					transactionDetails.setTransactionamount(uamount);
					transactionDetails.setAccountnumber(accountNumber);
					transactionDetails.setTransactionamount(uamount);
					transactionDetails.setTransactiondate(LocalDate.now());
					transactionDetails.setTransactiontime(LocalTime.now());
					transactionDetails.setBalanceamount(balance);
					transactionDetails.setRaccountnumber(accountNumber);
					transactionServices.addTransaction(transactionDetails);
				} else {
					System.out.println("Pending.....................");
				}
			} else {
				System.out.println("enter valid amount ");
			}

		}

	}

	public void changePin(String emailOrMobile) {
		System.out.println("enter pin");
		int pin = sc.nextInt();
		System.out.println("Enter Confirm Pin");
		int cpin = sc.nextInt();
		if (pin == cpin) {
			customerDAO.updatepin(emailOrMobile, cpin);

		} else {
			System.out.println("new pin and Confirm Pin not Matched");
		}

	}

}
