package com.cg.flat.service;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.cg.flat.entity.CustomerEntity;
import com.cg.flat.entity.FlatEntity;
import com.cg.flat.exception.CustomerException;

public interface CustomerService {

	CustomerEntity viewCustomerDetails(String cusId) throws ClassNotFoundException, FileNotFoundException, CustomerException, SQLException;

	void addCustomer(CustomerEntity customerEntity,FlatEntity flatEntity) throws ClassNotFoundException, FileNotFoundException, CustomerException;

	FlatEntity viewFlatDetails(String name) throws ClassNotFoundException, FileNotFoundException, SQLException, CustomerException;

	FlatEntity viewFlatDetailsId(String cusFlatId,String cusNum) throws ClassNotFoundException, FileNotFoundException, SQLException, CustomerException;

}
