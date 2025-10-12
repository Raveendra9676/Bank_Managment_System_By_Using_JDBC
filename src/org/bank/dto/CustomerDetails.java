package org.bank.dto;

import java.io.Serializable;
import java.sql.Date;

public class CustomerDetails {

	private int customerid;
	private String customername;
	private String customeremail;
	private long customermobilenumber;
	private long aadharnumber;
	private String pannumber;
	private long accountnumber;
	private int pinnumber;
	private String gender;
	private String address;
	private Date dateofbirth;
	private String ifsccode;
	private double amount;
	private String status;

	public CustomerDetails() {
	}

	public CustomerDetails(int customerid, String customername, String customeremail, long customermobilenumber,
			long aadharnumber, String pannumber, long accountnumber, int pinnumber, String gender, String address,
			Date dateofbirth, String ifsccode, double amount, String status) {

		this.customerid = customerid;
		this.customername = customername;
		this.customeremail = customeremail;
		this.customermobilenumber = customermobilenumber;
		this.aadharnumber = aadharnumber;
		this.pannumber = pannumber;
		this.accountnumber = accountnumber;
		this.pinnumber = pinnumber;
		this.gender = gender;
		this.address = address;
		this.dateofbirth = dateofbirth;
		this.ifsccode = ifsccode;
		this.amount = amount;
		this.status = status;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomeremail() {
		return customeremail;
	}

	public void setCustomeremail(String customeremail) {
		this.customeremail = customeremail;
	}

	public long getCustomermobilenumber() {
		return customermobilenumber;
	}

	public void setCustomermobilenumber(long customermobilenumber) {
		this.customermobilenumber = customermobilenumber;
	}

	public long getAadharnumber() {
		return aadharnumber;
	}

	public void setAadharnumber(long aadharnumber) {
		this.aadharnumber = aadharnumber;
	}

	public String getPannumber() {
		return pannumber;
	}

	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}

	public long getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}

	public int getPinnumber() {
		return pinnumber;
	}

	public void setPinnumber(int pinnumber) {
		this.pinnumber = pinnumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerDetails [customerid=" + customerid + ", customername=" + customername + ", customeremail="
				+ customeremail + ", customermobilenumber=" + customermobilenumber + ", aadharnumber=" + aadharnumber
				+ ", pannumber=" + pannumber + ", accountnumber=" + accountnumber + ", pinnumber=" + pinnumber
				+ ", gender=" + gender + ", address=" + address + ", dateofbirth=" + dateofbirth + ", ifsccode="
				+ ifsccode + ", amount=" + amount + ", status=" + status + "]";
	}

}
