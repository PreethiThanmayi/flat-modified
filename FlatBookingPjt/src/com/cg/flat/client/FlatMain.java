package com.cg.flat.client;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.flat.exception.CustomerException;
import com.cg.flat.service.CustomerServiceImpl;
import com.cg.flat.entity.CustomerEntity;
import com.cg.flat.entity.FlatEntity;
import com.cg.flat.exception.CustomerException;
import com.cg.flat.service.CustomerService;
import com.cg.flat.service.CustomerServiceImpl;

public class FlatMain {
	
	static Scanner scanner=new Scanner(System.in);
	static CustomerServiceImpl customerServiceImpl=null;
	static	CustomerService customerService=null;
	
public static void main(String[] args) throws CustomerException, ClassNotFoundException, FileNotFoundException, SQLException {
	
	CustomerEntity customerEntity=null;
	FlatEntity flatEntity=null;
	
	String cusId=null;
	String cusFlatId=null;
	String cusNum=null;
	
	int opt=0;
	while(true)
	{
		
		System.out.println("Menu");
		System.out.println("1-It Displays available Flats by flat type");
		System.out.println("2-Book your flat by entering your details");
		System.out.println("3-view Customer by customer number");
		System.out.println("4-It Displays Flat Details you have booked");
		System.out.println("5-exit");
		System.out.println("select an option");

		
	try
	{
		opt=scanner.nextInt();
		switch(opt) {
		
		case 1: 
			String type=null;
			System.out.println("enter Flat Type(1BHK/2BHK/3BHK):");
			type=scanner.next();
			
			customerServiceImpl=new CustomerServiceImpl();
		
			while (true) 
			{
				if ( customerServiceImpl.validateflatType(type)) 
				{
					break;
				} 
				else
				{
					System.err.println("Please enter correct Flat name ");
					type = scanner.next();
				}
			
			}
			if(type!=null) {
				System.out.println("Available flats are for "  + type + " are: ");
			
				flatEntity=getFlatDetails(type);
			 
				System.out.println(flatEntity);
			break;
			}	
			
			break;
		
		
		case 2:
			// flatEntity=new FlatEntity();
		
			while(customerEntity==null)
	     	{
		     
				customerEntity=retrieveCustomerEntity();
		      
		    }
			while(flatEntity==null)
			{
				flatEntity=FlatMain.flats();
			}
		try
		{
		    customerService = new CustomerServiceImpl();
		    
		    customerService.addCustomer(customerEntity,flatEntity);
		      
		    System.out.println("Flat is booked succesfully");
		    
		   
		}
	
		catch(CustomerException ce)
		{
			System.out.println(ce);
		}
		break;
		
		
		case 3:
			customerServiceImpl=new CustomerServiceImpl();
			System.out.println("Enter Customer Phone Number:");
			cusNum = scanner.next();

			/*while (true) 
			{
				if ( customerServiceImpl.validatecusNum(cusNum)) 
				{
					break;
				} 
				else
				{
					System.err.println("Please enter correct customer number ");
					cusNum = scanner.next();
				}
			}*/

			customerEntity = getCustomerDetails(cusNum);

			if (customerEntity != null)
			{
				System.out.println("Name               :"+ customerEntity.getCusName());
				System.out.println("Phone Number       :"+ customerEntity.getCusNum());
				System.out.println("Email Id           :"+ customerEntity.getCusEmail());
				System.out.println("Address            :"+ customerEntity.getCusAddress());
				System.out.println("Aadhar number      :"+ customerEntity.getCusAadhar());
				break;
			} 
			else
			{
				System.err.println("There are no details associated with your phone number "+ cusNum);
								
			}

			break;
			
		
		case 4:
			flatEntity=new FlatEntity();
			customerEntity=new CustomerEntity();
			
			System.out.println("Enter your flat Id to see your booked flat ");
			 cusFlatId=scanner.next();
			 
			 System.out.println("Enter your phone no");
			 cusNum=scanner.next();
			 
			 customerServiceImpl=new CustomerServiceImpl();
			 while (true) 
				{
					if ( customerServiceImpl.validatecusFlatId(cusFlatId)&&customerServiceImpl.validatecusNum(cusNum)) 
					{
						break;
					} 
					else
					{
						System.err.println("Please enter correct Flat Id or number");
						
						cusFlatId = scanner.next();
						cusNum=scanner.next();
						
					}
				
				}
			 
			 while(cusFlatId!=null)
			 {
					System.out.println("Your flat details are:");
				
					flatEntity=getFlatDetailsId(cusFlatId,cusNum);
					System.out.println("get");
				
				    System.out.println(flatEntity);
			 }
				
			 break;
			
		case 5: 
			System.out.print("Exit Application");
			System.exit(0);
			break;
			
		default:System.out.println("enter valid option");	
		}
		}
	catch(InputMismatchException ime)
	{
		System.out.println(ime);
	}
		
 }
	
}




private static FlatEntity flats() throws CustomerException{
FlatEntity flatEntity=new FlatEntity();
System.out.println("Enter flats details you want:");
System.out.println("enter flat type:");
flatEntity.setFlatType(scanner.next());

System.out.println("enter flat Id");
flatEntity.setCusFlatId(scanner.next());

try
{
	customerServiceImpl.flatValidation(flatEntity);
}
catch(CustomerException ce)
{
	System.out.println(ce);
}

	return null;
}




private static FlatEntity getFlatDetailsId(String cusFlatId,String cusNum) throws ClassNotFoundException, FileNotFoundException, SQLException, CustomerException {

	
	FlatEntity flatEntity=null;
	customerService = new CustomerServiceImpl();
	flatEntity=customerService.viewFlatDetailsId(cusFlatId,cusNum);
	return flatEntity;
}




private static FlatEntity getFlatDetails(String type) throws ClassNotFoundException, FileNotFoundException, SQLException, CustomerException {
	FlatEntity flatEntity=new FlatEntity();
	customerService = new CustomerServiceImpl();
	flatEntity=customerService.viewFlatDetails(type);
	return flatEntity;
}




private static CustomerEntity getCustomerDetails(String cusId) throws CustomerException, ClassNotFoundException, FileNotFoundException, SQLException {
	CustomerEntity customerEntity = null;
	customerService = new CustomerServiceImpl();

	customerEntity = customerService.viewCustomerDetails(cusId);

	customerService = null;
	return customerEntity;
}

	

private static CustomerEntity retrieveCustomerEntity() {
	
	CustomerEntity customerEntity=new CustomerEntity();
	
		
	System.out.println("enter your Name ");
	String cusName=scanner.next();
	customerEntity.setCusName(cusName);
	
	System.out.println("enter your Number ");
	String cusNum=scanner.next();
	customerEntity.setCusNum(cusNum);
	
	System.out.println("enter your Email ");
	String cusEmail=scanner.next();
	customerEntity.setCusEmail(cusEmail);
	
	System.out.println("enter your Aadhar number ");
	String cusAadhar=scanner.next();
	customerEntity.setCusAadhar(cusAadhar);
	
	System.out.println("enter your Address ");
	String cusAddress=scanner.next();
	customerEntity.setCusAddress(cusAddress);
	
	System.out.println("enter the Flat Id which you wnat to book");
	String cusFlatId=scanner.next();
	customerEntity.setCusFlatId(cusFlatId);
	
	customerServiceImpl =new CustomerServiceImpl(); 
	
	try
	{
		customerServiceImpl.validateCustomer(customerEntity);
		return customerEntity;
	}
	
	catch(CustomerException customerException)
	{
		System.err.println(customerException.getMessage());
		System.exit(0);
		
	}
	
	return customerEntity;
}
}
	
	
	
	
	/*System.out.println("Enter your required area");
	System.out.println("Menu");
	
	System.out.println("1-Shollinganallur");
	System.out.println("2-Navallur");
	System.out.println("3-siruseri");

	int area=scanner.nextInt();
	
	do {
	switch(area)
	{
	
	case 1:do {
		System.out.println("Available Flat Types are: 1BH \t 2BH \t 3BH");
		   System.out.println("Enter your required flat type");
		   String type1=scanner.next();
			
		   switch(type1) {
			case "1BH":System.out.println(" Flat area:sithalapakkam,shollinganallur");
						System.out.println("Total sq.ft:480 ");
			            System.out.println("price:19L ");break;
			case "2BH":System.out.println(" Flat area:Nexterra,shollinganallur");
						System.out.println("Total sq.ft:605 ");
						System.out.println("price:29.7L ");break;
			case "3BH":System.out.println(" Flat area:Baashyaam Pinnacle Crest,shollinganallur");
						System.out.println("Total sq.ft:736 ");
						System.out.println("price:68.98L ");break;
			default:System.out.println("wrong input");

			}
			System.out.println("do u want to go back(y/n)");
			y=scanner.nextBoolean();
			}while(y!=true);
			break;
	
	case 2:System.out.println("Available Flat Types are:2BH \t 3BH ");
	   System.out.println("Enter your required flat type");
	   String type2=scanner.next();
		do {
	   switch(type2) {
		case "3BH":System.out.println(" Flat area:Claritas,Old Mahabalipuram road,Navalur");
					System.out.println("Total sq.ft:1414 ");
		            System.out.println("price:86.86L ");break;
		case "2BH":System.out.println(" Flat area:Thalambur,Navalur");
					System.out.println("Total sq.ft:753 ");
					System.out.println("price:24.47L ");break;
		default:System.out.println("wrong input");

		
		}
		System.out.println("do u want to go back(y/n)");
		y=scanner.nextBoolean();
		}while(y!=true);
		break;
	
	case 3:System.out.println("Available Flat Types are: 1-1BH \t 2-2BH \t 3-3BH");
	   System.out.println("Enter your required flat type");
	   String type3=scanner.next();
		do {
	   switch(type3) {
		case "1BH":System.out.println(" Flat area:Shantiniketan Vega,siruseri");
					System.out.println("Total sq.ft:580 ");
		            System.out.println("price:20L ");break;
		case "2BH":System.out.println(" Flat area:semmancheri,Chennai Green apple,siruseri");
					System.out.println("Total sq.ft:654 ");
					System.out.println("price:29.43L ");break;
		case "3BH":System.out.println(" Flat area:L and T Eden Park Phase 2,siruseri");
					System.out.println("Total sq.ft:836 ");
					System.out.println("price:88.7L ");break;
					default:System.out.println("wrong input");
		}
	   System.out.println("do u want to go back(y/n)");
		y=scanner.nextBoolean();
		
		}while(y!=true);
			break;
	default:System.out.println("wrong input");

	}
	}while(area!=3);*/
	


