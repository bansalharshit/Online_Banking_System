package com.masai.Banking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import com.masai.bean.Customer;

import com.masai.dao.CustomerDao;
import com.masai.dao.CustomerDaoImpl;
import com.masai.dao.CustomerDaoImpl;
import com.masai.exceptions.CustomerException;


public class bank {
public static void main(String[] args)throws IOException {
	BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
	int choice;
	String cname="";
	String email="";
	int password;
	int balance;
	String mobile="";
	String address="";

	while(true) {
		System.out.println("--->|| Welcome to the MasaiBank ||<---");
		System.out.println();
		System.out.println("1) Customer");
		System.out.println("2) Accoutant");
		
		try {
			System.out.println("--->Press 1 for Customer or Press 2 for Accountant<---");
			choice =Integer.parseInt(sc.readLine());
			switch(choice) {
			case 1:
				while(true) {
					
				System.out.println();
					
					System.out.println("1) Create Account");
					System.out.println("2)Login Account");
					
					try {

						System.out.println("-->|| Press 1 to Login||<--");
						 choice=Integer.parseInt(sc.readLine());
						 
						switch(choice) {
						
						case 1:
							try {
								
					        	System.out.println("Enter email:");
							 email =sc.readLine();
							
							System.out.println("Enter Password:");
							 password =Integer.parseInt(sc.readLine());;
							
							CustomerDao dao=new CustomerDaoImpl();
							try {
								Customer customer =dao.LoginAccount(email, password);
								System.out.println("welcome to the masai bank :"+customer.getCname());
							} catch (CustomerException e) {
								// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							}
						} catch (Exception e) {
							System.out.println("Enter Valid Data");
							
						}
							break;
							default:
								System.out.println("Invalid Entry");
						}
						
						if(choice==3) {
							System.out.println("Thank You for Using the App");
							break;
						}
						
					} 
					catch (Exception e) {
						System.out.println("Enter Valid Entry!");
//						System.out.println(e.getMessage());
					}
					
					
				}
			sc.close();
				break;
			case 2:
//				-------------------------
				System.out.println("Operation Perform By Accountant");
				System.out.println("++++++++++++++++++++++++++++++++++++");
				System.out.println("Press 1) Add the Detail of the New Customer");
				System.out.println("Press 2) Editing Already available Account");
				System.out.println("Press 3) Removing the Account by using account Number");
				System.out.println("Press 4 Viewing particular Acccount details by giving account number");
				System.out.println("Press 5) Viewing all the account details");
				
				choice =Integer.parseInt(sc.readLine());
				
				switch(choice) {
				case 1:
					try {
						System.out.println("Enter Your Name");
						 cname=sc.readLine();
						
						System.out.println("Enter Your Email");
						 email=sc.readLine();
				         System.out.println("Enter Your Password");
				         password=Integer.parseInt(sc.readLine());
				        System.out.println("Enter Your Balance");
				        balance=Integer.parseInt(sc.readLine());
				        System.out.println("Enter Your Mobile");
				         mobile=sc.readLine();
				        System.out.println("Enter Your Address");
				         address=sc.readLine();
				        
				        Customer customers=new Customer();
				        customers.setCname(cname);
				        customers.setEmail(email);
				        customers.setPassword(password);
				        customers.setBalance(balance);
				        customers.setMobile(mobile);
				        customers.setAddress(address);
				        CustomerDao cd=new CustomerDaoImpl();
				        String result=cd.createAccount(customers);
				        
				        
				        if(result != null) {
				        	System.out.println("Account created Successfully");
				        }else {
				        	System.out.println("Account creation failed");
				        }
				        
						
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage());
						
					}
					break;
					default:
						System.out.println("Invalid Choice of Selection");
				}
				
//				------------------------
				break;
			default :
				System.out.println("Invalid Choice");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Enter the Valid Entry");
		}
	}
	
	

}
}
