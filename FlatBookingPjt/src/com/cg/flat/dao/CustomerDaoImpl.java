package com.cg.flat.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.flat.entity.CustomerEntity;
import com.cg.flat.entity.FlatEntity;
import com.cg.flat.exception.CustomerException;
import com.cg.flat.util.DBConnection;

public class CustomerDaoImpl implements ICustomerDao {
	static PreparedStatement preparedStatement;
	static Connection connection;
	
	public void addCustomer(CustomerEntity customerEntity,FlatEntity flatEntity)throws CustomerException, ClassNotFoundException{
		//String cusId=null;
		//String cusNum=null;
		//flatEntity=null;
		String type=flatEntity.getFlatType();
		String id=flatEntity.getCusFlatId();
		try {
			Connection connection=DBConnection.getConnection();
			
			PreparedStatement preparedStatement=null;		
		
			preparedStatement=connection.prepareStatement("insert into customer values(cust_seq.nextval,?,?,?,?,?,sysdate,?)");

			preparedStatement.setString(1,customerEntity.getCusName());			
			preparedStatement.setString(2,customerEntity.getCusNum());
			preparedStatement.setString(3,customerEntity.getCusEmail());
			preparedStatement.setString(4,customerEntity.getCusAadhar());
			preparedStatement.setString(5,customerEntity.getCusAddress());
			preparedStatement.setString(6,customerEntity.getCusFlatId());
			preparedStatement.executeUpdate();
			
			Statement statement=connection.createStatement();
			statement.executeUpdate("update flat set flatavbt=flatavbt-1 where cusflatid='"+id+"' and flattype='"+type+"'");
			connection.close();
			
			
		}
			catch(Exception e) 
			{
				System.out.println(e);
				e.printStackTrace();
			}

		
		
	}
		
	public FlatEntity viewFlatDetails(String type) throws ClassNotFoundException, FileNotFoundException, SQLException, CustomerException 
	{

		Connection connection=DBConnection.getConnection();
		Statement st=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultset = null;
		FlatEntity flatEntity=null;
		
		try
		{
			
			preparedStatement=connection.prepareStatement("select * from flat where FlatType=?");
			preparedStatement.setString(1, type);
	          resultset=preparedStatement.executeQuery();
		
			
			if(resultset.next())
			{
			    flatEntity=new FlatEntity();
				flatEntity.setCusFlatId(resultset.getString(1));
				flatEntity.setFlatArea(resultset.getString(2));
				flatEntity.setFlatType(resultset.getString(3));
				flatEntity.setFlatSqft(resultset.getString(4));
				flatEntity.setFlatPrice(resultset.getString(5));
				flatEntity.setFlatAbty(resultset.getInt(6));
				
				//System.out.println(resultset.getString(1) +  resultset.getString(2)+ resultset.getString(3)+  resultset.getString(4)+  resultset.getString(5));
				
				return flatEntity;
			}
			connection.close();
		}
		catch(Exception e)
		{
			
			throw new CustomerException(e.getMessage());
		}
		
		return null;
	}
	
				
	public CustomerEntity viewCustomerDetails(String cusNum) throws CustomerException, ClassNotFoundException, FileNotFoundException, SQLException
	{
		Connection connection=DBConnection.getConnection();
		
		PreparedStatement preparedStatement=null;
		ResultSet resultset = null;
		CustomerEntity customerEntity=null;
		
		try
		{
			preparedStatement=connection.prepareStatement("select * from customer where cusNum=?");
			preparedStatement.setString(1,cusNum);
			resultset=preparedStatement.executeQuery();
			
			if(resultset.next())
			{
				customerEntity = new CustomerEntity();
				
				customerEntity.setCusName(resultset.getString(2));
				customerEntity.setCusNum(resultset.getString(3));
				customerEntity.setCusEmail(resultset.getString(4));
				customerEntity.setCusAddress(resultset.getString(5));
				customerEntity.setCusAadhar(resultset.getString(6));
				
				//customerEntity.setCusBookedDate(resultset.getCusBookedDate(6));
				//System.out.println(resultset.getString(1) +  resultset.getString(2)+ resultset.getString(3)+  resultset.getString(4)+  resultset.getString(5)+  resultset.getString(6));
			}
			
		}
		catch(Exception e)
		{
			
			throw new CustomerException(e.getMessage());
		}
		
		return customerEntity;
		
	}

	
	public FlatEntity viewFlatDetailsId(String cusFlatId,String cusNum) throws ClassNotFoundException, FileNotFoundException, SQLException  {
		Connection connection=DBConnection.getConnection();
		
		PreparedStatement preparedStatement=null;
		ResultSet resultset = null;
		FlatEntity flatEntity=null;
		CustomerEntity customerEntity=null;
		
		try
		{
			preparedStatement=connection.prepareStatement("select f.flatarea ,f.flattype , f.flatsqft ,f.flatprice ,c.cusname,c.cusnum,c.cusbookeddate from flat f,customer c where f.cusflatid=? and c.cusnum=? and f.cusflatid=c.cusflatid");
			preparedStatement.setString(1,cusFlatId);
			preparedStatement.setString(2,cusNum);
			resultset=preparedStatement.executeQuery();
			while(resultset.next())
			{
			    flatEntity=new FlatEntity();
			    customerEntity = new CustomerEntity();

			    flatEntity.setFlatArea(resultset.getString(1));
				flatEntity.setFlatType(resultset.getString(2));
				flatEntity.setFlatSqft(resultset.getString(3));
				flatEntity.setFlatPrice(resultset.getString(4));
				flatEntity.setCusFlatId(resultset.getString(5));
				customerEntity.setCusName(resultset.getString(1));
				customerEntity.setCusNum(resultset.getString(2));
				customerEntity.setCusBookedDate(resultset.getDate(3));
				//System.out.println(resultset.getString(1) +  resultset.getString(2)+ resultset.getString(3)+  resultset.getString(4)+  resultset.getString(5)+  resultset.getString(6)+  resultset.getString(7)+  resultset.getString(8));
			}
			
		}
		catch(Exception e)
		{
			
			try {
				throw new CustomerException(e.getMessage());
			} catch (CustomerException e1) {
				e1.printStackTrace();
			}
		}
		
		return flatEntity;
	}

}
		
