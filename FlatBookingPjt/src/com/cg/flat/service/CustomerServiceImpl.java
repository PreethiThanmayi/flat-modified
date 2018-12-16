package com.cg.flat.service;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.flat.dao.CustomerDaoImpl;
import com.cg.flat.dao.ICustomerDao;
import com.cg.flat.entity.CustomerEntity;
import com.cg.flat.entity.FlatEntity;
import com.cg.flat.exception.CustomerException;

public class CustomerServiceImpl implements CustomerService{

	ICustomerDao customerDao;
	
	public void addCustomer(CustomerEntity customerEntity,FlatEntity flatEntity) throws ClassNotFoundException, FileNotFoundException, CustomerException {
		customerDao=new CustomerDaoImpl();	
			String cust;
			cust= customerDao.addCustomer(customerEntity,flatEntity);
			//return cust; 
		}
		
	

	public CustomerEntity viewCustomerDetails(String cusId) throws ClassNotFoundException, FileNotFoundException, CustomerException, SQLException {

		customerDao=new CustomerDaoImpl();	
		CustomerEntity customerEntity=null;
		customerEntity=customerDao.viewCustomerDetails(cusId);
		return customerEntity;
	}
	
	
	public FlatEntity viewFlatDetails(String type) throws ClassNotFoundException, FileNotFoundException, SQLException, CustomerException {
		
		customerDao=new CustomerDaoImpl();	
		FlatEntity flatEntity=new FlatEntity();
		flatEntity=customerDao.viewFlatDetails(type);
		return flatEntity;
	}
	 
	
	
	public FlatEntity viewFlatDetailsId(String cusFlatId,String cusNum) throws ClassNotFoundException, FileNotFoundException, SQLException ,CustomerException
	{
			customerDao=new CustomerDaoImpl();	
			FlatEntity flatEntity=null;
			flatEntity=customerDao.viewFlatDetailsId(cusFlatId,cusNum);
			System.out.println("get2");
			return flatEntity;
		
    }

	


	public void validateCustomer(CustomerEntity customerEntity) throws CustomerException {
		if(!(isValidName(customerEntity.getCusName()))) 
		{
			System.out.println("\n  Name Should Be In Alphabets and minimum 3 characters long ! \n");
		}
		
		//Validating address
		if(!(isValidAddress(customerEntity.getCusAddress())))
		{
			System.out.println("\n Address Should Be Greater Than 5 Characters \n");
		}
		
		//Validating Phone Number
		if(!(isValidPhoneNumber(customerEntity.getCusNum())))
		{
			System.out.println("\n Phone Number Should be in 10 digit \n");
		}
		
		//Validating Donation Amount
		if(!(isValidEmail(customerEntity.getCusEmail())))
		{
			System.out.println("\n Email Id must not contain spaces and should be valid format \n" );
		}
		
		if(!(isValidAadhar(customerEntity.getCusAadhar())))
		{
			System.out.println("\n Aadhar number must be 12 digit number \n" );
		}
		
		
		
		/*if(!validationErrors.isEmpty())
			throw new DonorException(validationErrors +"");
	}*/
		
	}


	private boolean isValidName(String cusName) {

		Pattern namePattern=Pattern.compile("^[A-Za-z]{3,}$");
		Matcher nameMatcher=namePattern.matcher(cusName);
		return nameMatcher.matches();
		
		
	}

	private boolean isValidAadhar(String cusAadhar) {

		Pattern phonePattern=Pattern.compile("^[0-9]{12}");
		Matcher aadharMatcher=phonePattern.matcher(cusAadhar);
		return aadharMatcher.matches();
		
		
	}
	
	private boolean isValidEmail(String cusEmail) {
		Pattern p3=Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
		Matcher emailMatcher=p3.matcher(cusEmail);
		return emailMatcher.matches();
		
	}

	private boolean isValidPhoneNumber(String cusNum) {
		Pattern phonePattern=Pattern.compile("^[1-9]{1}[0-9]{9}$");
		Matcher phoneMatcher=phonePattern.matcher(cusNum);
		return phoneMatcher.matches();
		
		
	}


	private boolean isValidAddress(String cusAddress) {
		
		return (cusAddress.length() > 6);
		
	}


	public boolean validatecusId(String cusId) {
		
		Pattern idPattern = Pattern.compile("[0-9]{1,4}");
		Matcher idMatcher = idPattern.matcher(cusId);
		
		if(idMatcher.matches())
			return true;
		else
			return false;		
	}



	public boolean validateflatType(String type) {
		Pattern namePattern=Pattern.compile("[1-3]+BHK");
		Matcher flatNameMatcher=namePattern.matcher(type);
		return flatNameMatcher.matches();
			
	}


	public boolean validatecusFlatId(String cusFlatId) {
		
		Pattern phonePattern=Pattern.compile("[0-9]{1,}");
		Matcher idMatcher=phonePattern.matcher(cusFlatId);
		return idMatcher.matches();
		
		
	}
	public boolean validatecusNum(String cusNum) {
		Pattern phonePattern=Pattern.compile("^[1-9]{1}[0-9]{9}$");
		Matcher phoneMatcher=phonePattern.matcher(cusNum);
		return phoneMatcher.matches();
		
	}



	public void flatValidation(FlatEntity flatEntity) throws CustomerException {
		List validationerror=new ArrayList();
		if(!validateflatType(flatEntity.getFlatType()))
		{
			validationerror.add("Flat not available");
		}
		if(!validationerror.isEmpty())
		{
			throw new CustomerException(validationerror+"\n");
		}
		
	}

	
	}


