package com.masai.Banking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
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
	int caccount;
	String acUserName="";
	int acPassword;
int ch = 0;
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
					
//					System.out.println("1) Create Account");
					System.out.println("1)Login Account");
					
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
						
						if(choice==39) {
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
				System.out.println("Enter the Login Details of Accountant");
				System.out.println("========================================");
				System.out.println("\n");
				System.out.println("Enter the UserName Of Accountant");
				acUserName=sc.readLine();
				System.out.println("Enter the Password of Accountant");
				acPassword=Integer.parseInt(sc.readLine());
				if(acUserName.equals("bansalharshit") && acPassword==987654) {
					System.out.println("Accountant Login Successfully!");
					System.out.println("\n");
//					--------------------------------------------
					while(true) {
						System.out.println("Operation Perform By Accountant");
						System.out.println("==================================");
						System.out.println("\n");
						System.out.println("Press 1) Add the Detail of the New Customer");
						System.out.println("Press 2) Editing Already available Account");
						System.out.println("Press 3) Removing the Account by using account Number");
						System.out.println("Press 4 Viewing particular Acccount details by giving account number");
						System.out.println("Press 5) Viewing all the account details");
						System.out.println("Press 6) Exit From the App");
						
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
						        int mn=Integer.parseInt(sc.readLine());
						        mobile=String.valueOf(mn);
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
						        int result=cd.createAccount(customers);
						        
						        
						        if(result >0) {
						        	System.out.println("Account created Successfully"+result);
						        	System.out.println("Your Account Number is "+result);
						        }else {
						        	System.out.println("Account creation failed");
						        }
						        
								
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e.getMessage());
								
							}
							break;
						case 2:
		                        try {
								System.out.println("Enter customer Account Number:");
							         caccount=Integer.parseInt(sc.readLine());
								System.out.println("Enter the New Mobile Number");
								mobile=sc.readLine();
								System.out.println("Enter the New Address");
								address=sc.readLine();
								CustomerDao dao=new CustomerDaoImpl();
								
								
								try {
									String customer = dao.UpdateCustomerDetailByAccount(caccount,mobile,address);
									System.out.println(customer);
								} catch (CustomerException se) {
								
									System.out.println(se.getMessage());
								}
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e.getMessage());
							}
							break;
						case 3:
							try {
								
								
								System.out.println("Enter customer Account Number:");
							         caccount=Integer.parseInt(sc.readLine());
								
								CustomerDao dao=new CustomerDaoImpl();
								
								
								try {
									String customer = dao.DeleteCustomerDetailByAccount(caccount);
									System.out.println(customer);
								} catch (CustomerException se) {
								
									System.out.println(se.getMessage());
								}
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e.getMessage());
							}
							break;
						case 4:
							try {
								
								
								System.out.println("Enter customer Account Number:");
							         caccount=Integer.parseInt(sc.readLine());
								
								CustomerDao dao=new CustomerDaoImpl();
								
								
								try {
									Customer customer = dao.getCustomerdetailByAccount(caccount);
									System.out.println(customer);
								} catch (CustomerException se) {
								
									System.out.println(se.getMessage());
								}
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e.getMessage());
							}
							break;
						case 5:
							CustomerDao dao=new CustomerDaoImpl();
							try {
								List<Customer> customers=dao.getAllCustomerDetails();
								customers.forEach(s->{
									System.out.println("Customer Account :"+s.getCaccount());
									System.out.println("Customer Name:"+s.getCname());
									System.out.println("Customer Email :"+s.getEmail());
									System.out.println("Customer Balance :"+s.getBalance());
									System.out.println("Customer Mobile :"+s.getMobile());
									System.out.println("Customer Address :"+s.getAddress());
									System.out.println("=======================");
									
								});
							} catch (CustomerException e) {
								System.out.println(e.getMessage());
							}
							break;
						case 6:
							break;
							default:
								System.out.println("Invalid Choice of Selection");
						}
				if(choice==6) {
					System.out.println("thanks for Using the application");
					break;
				}
					}
					
			
//					---------------------------------------------
					
				}else {
					System.out.println("Either the UserName or Password is Wrong");
				}
//				
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
