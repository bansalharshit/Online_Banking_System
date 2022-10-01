package com.masai.dao;

import java.util.List;

import com.masai.bean.Customer;

import com.masai.exceptions.CustomerException;



public interface CustomerDao {
public int createAccount(Customer customer);
public Customer LoginAccount(String email,int password)throws CustomerException;
public boolean TransferMoney(int senderaccount,int BenificiaryAccount,int transferamount )throws CustomerException;
public void getBalance(int acNo)throws CustomerException;
public Customer getCustomerdetailByAccount(int caccount)throws CustomerException;
public List<Customer> getAllCustomerDetails()throws CustomerException;
public String DeleteCustomerDetailByAccount(int caccount)throws CustomerException;
public String UpdateCustomerDetailByAccount(int caccount,String mobile,String address)throws CustomerException;
}
