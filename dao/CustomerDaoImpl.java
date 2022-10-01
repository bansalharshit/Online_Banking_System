package com.masai.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.masai.bean.Customer;

import com.masai.exceptions.CustomerException;

import com.masai.utility.DBUtil;
import com.mysql.cj.xdevapi.DbDoc;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public int createAccount(Customer customer) {
		int message=0;
		try(Connection conn= DBUtil.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("insert into customer(cname,email,password,balance,mobile,address) values(?,?,?,?,?,?)");
			
			ps.setString(1, customer.getCname());
			ps.setString(2, customer.getEmail());
			ps.setInt(3, customer.getPassword());
			ps.setInt(4, customer.getBalance());
			ps.setString(5, customer.getMobile());
			ps.setString(6, customer.getAddress());
			
			int x=ps.executeUpdate();
			if(x>0)
			{
				
//			message=" Account created  Successfully !";
			PreparedStatement ps1=conn.prepareStatement("select caccount from customer where email=?");
			ps1.setString(1, customer.getEmail());
			
			ResultSet rs=ps1.executeQuery();
			if(rs.next()) {
				message=	rs.getInt(1);
			}
		
//			String temp=String.valueOf(rs.getInt(1));
		
			}
			
		} catch (SQLException e) {
//			message=e.getMessage();
			System.out.println(e.getMessage());
		}
		
		
		return message;
	}

	@Override
	public Customer LoginAccount(String email, int password) throws CustomerException {
		Customer customer=null;
		try(Connection conn=DBUtil.provideConnection()) {
			if(email=="" || password == 0) {
				System.out.println("All Fields are Required");
				
			}
			PreparedStatement ps=conn.prepareStatement("select * from customer where email=? and password=?");
			ps.setString(1, email);
			ps.setInt(2, password);
			ResultSet rs=ps.executeQuery();
			BufferedReader scan=new BufferedReader(new InputStreamReader(System.in));
			if(rs.next()) {
				int a=rs.getInt("caccount");
				String n=rs.getString("cname");
				String e=rs.getString("email");
				int p=rs.getInt("password");
				int b=rs.getInt("balance");
				String m=rs.getString("mobile");
				String ad=rs.getString("address");
				
				customer=new Customer(a,n,e,p,b,m,ad);
				// Apply Transfer Money logic and View Balance and logout logic
				int choice=5;
				int transferamount=0;
				int senderaccount=rs.getInt("caccount");
				int BenificiaryAccount;
				while(true){
					try {

						System.out.println("Welcome "+rs.getString("cname"));
//						System.out.println("0) Enter Your Detail For creating an account");
						System.out.println("1) Transfer Money");
						System.out.println("2) View Balance");
						System.out.println("3) Logout");
						System.out.println("Press 1 to money Tranfer or Press 2 to view Blance or Press 3 to Logout");
						choice=Integer.parseInt(scan.readLine());
//						if(choice==0) {
//							System.out.println("Enter Your Name");
//							String cname=scan.readLine();
//							
//							System.out.println("Enter Your Email");
//							String email1=scan.readLine();
//					         System.out.println("Enter Your Password");
//					         int password1=Integer.parseInt(scan.readLine());
//					        System.out.println("Enter Your Balance");
//					        int balance=Integer.parseInt(scan.readLine());
//					        System.out.println("Enter Your Mobile");
//					        String mobile=scan.readLine();
//					        System.out.println("Enter Your Address");
//					         String address=scan.readLine();
//					        
//					        Customer customers=new Customer();
//					        customers.setCname(cname);
//					        customers.setEmail(email1);
//					        customers.setPassword(password1);
//					        customers.setBalance(balance);
//					        customers.setMobile(mobile);
//					        customers.setAddress(address);
//						}
						if(choice==1) {
							System.out.println("Enter Beneficiary or Receiver Account Number ");
							BenificiaryAccount=Integer.parseInt(scan.readLine());
							System.out.println("Enter Transfer Amount");
							transferamount=Integer.parseInt(scan.readLine());
						    CustomerDao dao=new CustomerDaoImpl();
						    if(dao.TransferMoney(senderaccount, BenificiaryAccount, transferamount)) {
						    	System.out.println("Money transfer SuccessFully to account number "+BenificiaryAccount);
						    }else {
						    	System.out.println("Money tranfer Fail");
						    }
						}else if(choice==2) {
							CustomerDao dao=new CustomerDaoImpl();
							dao.getBalance(senderaccount);
						}else if(choice==3) {
							
							break;
						}else {
							System.out.println("Enter the Valid Input Field");
						}
					} catch (Exception e2) {
						// TODO: handle exception
					System.out.println(e2.getMessage());
					}
				}
				
				
				// End Of Logic
			}else {
				throw new CustomerException("User does not exist with the given Mail Id or Password"); 
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new CustomerException(e.getMessage());
			
		}
		return customer;
	}

	@Override
	public boolean TransferMoney(int senderaccount, int BenificiaryAccount, int transferamount) throws CustomerException {
		try(Connection conn=DBUtil.provideConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement ps =conn.prepareStatement("select * from customer where caccount=?");
			ps.setInt(1, senderaccount);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				if(rs.getInt("balance")<transferamount) {
					System.out.println("Insufficient Balance");
					return false;
				}
			}
			Statement st= conn.createStatement();
			// Debit part
			conn.setSavepoint();
			PreparedStatement ps1= conn.prepareStatement("update customer set balance=balance-? where caccount=?");
			ps1.setInt(1, transferamount);
			ps1.setInt(2, senderaccount);
			if(ps1.executeUpdate()>0) {
				System.out.println("Amount has been Debited from Your bank Account");
			}
			// Credit Part
			PreparedStatement ps2= conn.prepareStatement("update customer set balance=balance+? where caccount=?");
			ps2.setInt(1, transferamount);
			ps2.setInt(2, BenificiaryAccount);
			if(ps2.executeUpdate()>0) {
				System.out.println("Amount has been Credited to the benificiary bank Account");
			}
			conn.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
//			conn.rollback();
		}
		return false;
		
	}

	@Override
	public void getBalance(int acNo) throws CustomerException {
		try(Connection conn=DBUtil.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("select * from customer where caccount=?");
			ps.setInt(1, acNo);
			ResultSet rs=ps.executeQuery();
			System.out.println("-----------------------------------------------");
			System.out.printf("%12s %10s %10s\n" ,"Account NO","Name","Balance");
			while(rs.next()) {
				System.out.printf("%12d %10s %10d.00\n",rs.getInt("caccount"),rs.getString("cname"),rs.getInt("balance"));
				System.out.println("-------------------------------------------");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public Customer getCustomerdetailByAccount(int caccount) throws CustomerException {
		Customer customer=null;
		
		try(Connection conn=DBUtil.provideConnection()){
			
			PreparedStatement ps=conn.prepareStatement("select * from customer where caccount=?");
					ps.setInt(1, caccount);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				int a=rs.getInt("caccount");
				String n =rs.getString("cname");
				String e=rs.getString("email");
				int p=rs.getInt("password");
				int b=rs.getInt("balance");
				String m=rs.getString("mobile");
				String ad=rs.getString("address");
				
				
				customer=new Customer(a,n,e,p,b,m,ad);
			}else {
				throw new CustomerException("Customer does not exist with Account no "+caccount);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CustomerException(e.getMessage());
		}		
				
	    
		
		
		return customer;
	}

	@Override
	public List<Customer> getAllCustomerDetails() throws CustomerException {
		List<Customer> customers =new ArrayList<>();
		
		try(Connection conn=DBUtil.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("select * from customer");
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				int a=rs.getInt("caccount");
				String n =rs.getString("cname");
				String e=rs.getString("email");
				int p=rs.getInt("password");
				int b=rs.getInt("balance");
				String m=rs.getString("mobile");
				String ad=rs.getString("address");
				Customer customer=new Customer(a,n,e,p,b,m,ad);
				customers.add(customer);
			}
		} catch (SQLException e) {
		
			throw new CustomerException(e.getMessage());
		}
		
		if(customers.size()==0) {
			throw new CustomerException("No Customer found...");
		}
		
		return customers;
	}

	@Override
	public String DeleteCustomerDetailByAccount(int caccount) throws CustomerException {
		String message="Given Account Number Does Not Exist to Delete";
		try(Connection conn= DBUtil.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("delete from customer where caccount=?");
			
			ps.setInt(1, caccount);
			
			
			int x=ps.executeUpdate();
			if(x>0)
			{
				
			message=" Account Deleted  Successfully !";
			
			}
			
		} catch (SQLException e) {
			message=e.getMessage();
		}
		
		
		return message;
	}

	@Override
	public String UpdateCustomerDetailByAccount(int caccount,String mobile,String address) throws CustomerException {
		String message="unable to update your account details";
		try(Connection conn=DBUtil.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("update customer set mobile=?,address=? where caccount=?");
			ps.setString(1,mobile);
			ps.setString(2, address);
			ps.setInt(3, caccount);
			int y=ps.executeUpdate();
			if(y>0) {
				message="Account Details has been Updated Successfully";
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return message;
	}

}
