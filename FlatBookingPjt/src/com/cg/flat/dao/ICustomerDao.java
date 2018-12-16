package com.cg.flat.dao;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.cg.flat.entity.CustomerEntity;
import com.cg.flat.entity.FlatEntity;
import com.cg.flat.exception.CustomerException;

public interface ICustomerDao {

	void  addCustomer(CustomerEntity customerEntity,FlatEntity flatEntity) throws CustomerException, ClassNotFoundException, FileNotFoundException;
	
	CustomerEntity viewCustomerDetails(String cusId) throws CustomerException, ClassNotFoundException, FileNotFoundException, SQLException;

	FlatEntity viewFlatDetails(String type) throws ClassNotFoundException, FileNotFoundException, SQLException, CustomerException;

	FlatEntity viewFlatDetailsId(String cusFlatId,String cusNum) throws ClassNotFoundException, FileNotFoundException, SQLException, CustomerException;

}
