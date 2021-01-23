package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.CustomerHandlingException;
import com.app.dao.BookingRepository;
import com.app.dao.CustomerRepository;
import com.app.dao.HelpsterRepository;
import com.app.dao.ServiceRepository;
import com.app.pojos.Booking;
import com.app.pojos.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerDao;
	
	@Autowired
	private HelpsterRepository helpsterDao;

	@Autowired
	private ServiceRepository serviceDao;

	@Autowired
	private BookingRepository bookingDao;
	
	
	@Override
	public List<Customer> getAllUser() {
		// TODO Auto-generated method stub
		return customerDao.findAll();
	}

	@Override
	public Customer addCustomer(Customer u) {
		// TODO Auto-generated method stub
		return customerDao.save(u);
	}

	@Override
	public void deleteCustomer(int uId) {
		// TODO Auto-generated method stub
		Optional<Customer> optional=customerDao.findById(uId);
		if(optional.isPresent())
			customerDao.deleteById(uId);
		else
		{
			throw new CustomerHandlingException("User Not Found :Invalid User id "+uId);
		}

	}

	@Override
	public Optional<Customer> verifyLoginDetails(String email, String password) {
		// TODO Auto-generated method stub
		return customerDao.findByEmailAndPassword(email, password);
	}

	@Override
	public Customer getCustomerDetails(int id) {
		// TODO Auto-generated method stub
		Optional<Customer> optionalUser=customerDao.findById(id);
		if(optionalUser.isPresent())
			return optionalUser.get();
		throw new CustomerHandlingException("User Not found :Invalid Id "+id);
	}

	@Override
	public Customer updateCustomerInfo(Customer c) {
		// TODO Auto-generated method stub
		Optional<Customer> optionalUser=customerDao.findById(c.getCustomerId());
		if(optionalUser.isPresent())
			return customerDao.save(c);
		throw new CustomerHandlingException("User Not found :Invalid Id "+c.getCustomerId());
	}

	@Override
	public List<Booking> getCustomerBookings(String email) {
		// TODO Auto-generated method stub
		Customer c=customerDao.findByEmail(email);
		List<Booking> customerBookings=new ArrayList<>();
			 for (Booking b : bookingDao.findAll()) 
				if(b.getAssociatedCustomer().equals(c))
					customerBookings.add(b);
		 return customerBookings;
	}

	@Override
	public List<com.app.pojos.Service> getAvailableServices(com.app.pojos.Service s) {
		List<com.app.pojos.Service> availableServices=new ArrayList<>();
		
		if(s.getCategory().equals("All") && s.getLocality().equals("All"))
			availableServices=serviceDao.findByServiceNameAndCity(s.getServiceName(),s.getCity());
			
		else if(s.getCategory().equals("All"))
			availableServices=serviceDao.findByServiceNameAndCityAndLocality(s.getServiceName(),s.getCity(),s.getLocality());
		
		else if(s.getLocality().equals("All"))
			availableServices=serviceDao.findByServiceNameAndCategoryAndCity(s.getServiceName(),s.getCategory(),s.getCity());
		
		else
			availableServices=serviceDao.findByServiceNameAndCategoryAndCityAndLocality(s.getServiceName(),s.getCategory(),s.getCity(),s.getLocality());
		
		return availableServices;
	}
	

}
