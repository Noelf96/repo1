package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.pojos.Booking;
import com.app.pojos.Customer;
import com.app.pojos.Helpster;
import com.app.pojos.Service;

public interface IHelpsterService {
	
	Helpster registerNewHelpster(Helpster h);
	List<Helpster> getAllHelpsters();
	Optional<Helpster> verifyLoginDetails(String email,String password);
	Service addService(Service newService,String helpsterEmail);
	List<Service> getAllServices();
	List<Service> getHelpsterServices(String email);
	List<Booking> getHelpsterBookings(String helpsterEmail);
	Helpster acceptBooking(Booking b, String helpsterEmail);
	Helpster updateHelpsterInfo(Helpster h);

}
