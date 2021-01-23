package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.custom_exception.CustomerHandlingException;
import com.app.pojos.Booking;
import com.app.pojos.Customer;
import com.app.pojos.Helpster;
import com.app.pojos.Service;
import com.app.service.IHelpsterService;

@RestController
@RequestMapping("/helpster")
//@CrossOrigin(origins = { "http://localhost:4200" })
public class HelpsterController {

	@Autowired
	private IHelpsterService service;

	@PostMapping
	public ResponseEntity<?> registerNewHelpster(@RequestBody Helpster newHelpster) {
		System.out.println("register new helpster " + newHelpster);// transient
		try {
			Helpster helpster = service.registerNewHelpster(newHelpster);
			// helpster.setRating(4.4f);

			return new ResponseEntity<>(helpster, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in register helpster" + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	  @GetMapping 
	  public List<Helpster> getAllHelpsters() {
		  System.out.println("in get all Helpsters"); 
		  return service.getAllHelpsters();
	  }
	  
	  @GetMapping("/services") 
	  public List<Service> getAllServices() {
		  System.out.println("in get all Helpsters"); 
		  return service.getAllServices();
	  }
	  
	 
	@GetMapping("/login/{email}/{password}")
	public Optional<Helpster> loginHelpster(@PathVariable String email, @PathVariable String password) {
		System.out.println("in login Helpsters");
		return service.verifyLoginDetails(email, password);
	}
	
	@PostMapping("/{helpsterEmail}/postService")
	public ResponseEntity<?> addService(@RequestBody Service newService,@PathVariable String helpsterEmail) {
		System.out.println("add new service " + newService);// transient
		try {
			service.addService(newService,helpsterEmail);
			return new ResponseEntity<>(newService, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add new service" + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{helpsterEmail}/getServices")
	public List<Service> getHelpsterServices(@PathVariable String helpsterEmail) {
		System.out.println("in get Helpster services");
		return service.getHelpsterServices(helpsterEmail);
	}
	
	
	  @GetMapping("/{helpsterEmail}/showBookings") 
	  public List<Booking> getHelpsterBookings(@PathVariable String helpsterEmail) {
		  System.out.println("in get Helpster bookings"); 
		  return service.getHelpsterBookings(helpsterEmail); }
	 
	  
		/*
		 * @PostMapping("/{helpsterEmail}/{customerEmail}/{serviceId}/placeBooking")
		 * public Optional<Helpster> confirmBooking(@RequestBody Booking newBooking) {
		 * System.out.println("in confirmBooking"); return null; }
		 */

}
