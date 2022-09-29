package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.masai.bean.Customer;
import com.masai.utility.DBUtil;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public String createAccount(Customer customer) {
		String message="Account Not created";
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
				
			message=" Account created  Successfully !";
			
			}
			
		} catch (SQLException e) {
			message=e.getMessage();
		}
		
		
		return message;
	}

}
