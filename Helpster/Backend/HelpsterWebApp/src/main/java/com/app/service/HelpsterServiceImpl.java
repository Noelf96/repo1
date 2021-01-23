package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.CustomerHandlingException;
import com.app.dao.BookingRepository;
import com.app.dao.HelpsterRepository;
import com.app.dao.ServiceRepository;
import com.app.pojos.Booking;
import com.app.pojos.Customer;
import com.app.pojos.Helpster;

@Service
@Transactional
public class HelpsterServiceImpl implements IHelpsterService {

	@Autowired
	private HelpsterRepository helpsterDao;

	@Autowired
	private ServiceRepository serviceDao;

	@Autowired
	private BookingRepository bookingDao;

	@Override
	public Helpster registerNewHelpster(Helpster h) {
		return helpsterDao.save(h);
	}

	@Override
	public List<Helpster> getAllHelpsters() {
		return helpsterDao.findAll();
	}

	@Override
	public Optional<Helpster> verifyLoginDetails(String email, String password) {
		return helpsterDao.findByEmailAndPassword(email, password);
	}

	@Override
	public com.app.pojos.Service addService(com.app.pojos.Service newService,String helpsterEmail) {
		Helpster h=helpsterDao.findByEmail(helpsterEmail).get();
		newService.setAssociatedHelpster(h);
		return serviceDao.save(newService);
	}

	@Override
	public List<com.app.pojos.Service> getAllServices() {
		return serviceDao.findAll();
	}

	@Override
	public List<com.app.pojos.Service> getHelpsterServices(String email) {
		
		  Helpster h = helpsterDao.findByEmail(email).get(); 
		  return serviceDao.findByAssociatedHelpster(h);
		
	}

	
	
	 @Override 
	 public Helpster acceptBooking(Booking b, String email) 
	 {
		 Helpster h = helpsterDao.findByEmail(email).get();	
		 h.setAvailable(false);
		 return helpsterDao.save(h); 
	 }
	 

	
	 @Override 
	 public List<Booking> getHelpsterBookings(String email) {
		 List<com.app.pojos.Service> helpsterServices=getHelpsterServices(email);
		 List<Booking> helpsterBookings=new ArrayList<>();
		 for(com.app.pojos.Service s:helpsterServices) 
			 for (Booking b : bookingDao.findAll()) 
				if(b.getAssociatedService().equals(s))
					helpsterBookings.add(b);
		 return helpsterBookings;
	 }
	 
	 @Override
		public Helpster updateHelpsterInfo(Helpster h) {
			// TODO Auto-generated method stub
			Optional<Helpster> optHelpster=helpsterDao.findById(h.getHId());
			if(optHelpster.isPresent())
				return helpsterDao.save(h);
			throw new CustomerHandlingException("User Not found :Invalid Id ");
		}
	 
}
