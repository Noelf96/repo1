package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.pojos.Booking;
import com.app.pojos.Customer;
import com.app.pojos.Service;

public interface ICustomerService {
	
	List<Customer> getAllUser();
	
	Customer addCustomer(Customer u);
	
	void deleteCustomer(int uId);
	
	Optional<Customer> verifyLoginDetails(String name,String password);
	
	Customer getCustomerDetails(int id);
	
	Customer updateCustomerInfo(Customer u);

	List<Booking> getCustomerBookings(String email);
	
	List<Service> getAvailableServices(Service s);
 }
