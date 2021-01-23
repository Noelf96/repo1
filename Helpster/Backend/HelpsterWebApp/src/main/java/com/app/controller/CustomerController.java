package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Customer;
import com.app.pojos.Service;
import com.app.service.ICustomerService;

@RestController
@RequestMapping("/customer")
//@CrossOrigin(origins = { "http://localhost:4200" })
public class CustomerController {
	
	@Autowired
	private ICustomerService service;
	
	public CustomerController() {
		System.out.println("In customer controller "+getClass().getName());
	}
	
	@GetMapping
	public ResponseEntity<?> fetchAllCustomers(){
		System.out.println("in fetch all user");
		List<Customer> users=service.getAllUser();
		if(users.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> addNewUser(@RequestBody Customer newCustomer){
		System.out.println("in add new Customer "+newCustomer);
		return ResponseEntity.ok(service.addCustomer(newCustomer));
	}
	
	@DeleteMapping("/{uId}")
	public void deleteUserDetails(@PathVariable int uId) {
		System.out.println("in del user detls "+uId);
		try {
			service.deleteCustomer(uId);
		}catch(RuntimeException e) {
			System.out.println("err in controller "+e);
			e.printStackTrace();
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authUser(@RequestBody Customer us){
		return ResponseEntity.of(service.verifyLoginDetails(us.getFirstName(), us.getPassword()));
	}
	
	@GetMapping("/{pid}")
	public ResponseEntity<?> getUserDetailsById(@PathVariable int pid) {
		System.out.println("in get product dtls " + pid);
		try {
			return ResponseEntity.ok(service.getCustomerDetails(pid));
		} catch (RuntimeException e) {
			System.out.println("err in controller " + e);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping
	public ResponseEntity<?> updateUserDetails(@RequestBody Customer u) {
		System.out.println("in update user" + u);
		try {
			return ResponseEntity.ok(service.updateCustomerInfo(u));
		} catch (RuntimeException e) {
			System.out.println("err in controller " + e);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/getAvailableServices")
	public ResponseEntity<?> getAvailableServices(@RequestBody Service s) {
		System.out.println("in get services" + s);
		try {
			return ResponseEntity.ok(service.getAvailableServices(s));
		} catch (RuntimeException e) {
			System.out.println("err in controller " + e);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}

