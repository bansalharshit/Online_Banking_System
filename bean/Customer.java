package com.masai.bean;

public class Customer {
private int caccount;
private String cname;
private String email;
private int password;
private int balance;
private String mobile;
private String address;
public Customer() {
	super();
}
public Customer(int caccount, String cname, String email, int password, int balance, String mobile, String address) {
	super();
	this.caccount = caccount;
	this.cname = cname;
	this.email = email;
	this.password = password;
	this.balance = balance;
	this.mobile = mobile;
	this.address = address;
}
public int getCaccount() {
	return caccount;
}
public void setCaccount(int caccount) {
	this.caccount = caccount;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getPassword() {
	return password;
}
public void setPassword(int password) {
	this.password = password;
}
public int getBalance() {
	return balance;
}
public void setBalance(int balance) {
	this.balance = balance;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
@Override
public String toString() {
	return "CustomerDao [caccount=" + caccount + ", cname=" + cname + ", email=" + email + ", password=" + password
			+ ", balance=" + balance + ", mobile=" + mobile + ", address=" + address + "]";
}

}
