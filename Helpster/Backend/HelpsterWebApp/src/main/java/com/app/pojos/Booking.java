package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(name="Booking_tbl")
public class Booking {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	
	@Column(columnDefinition = "float default 0")
	private float rating;
	
	@Column(length = 50)
	private String feedbackDescription ;
	
	@ManyToOne
	@JoinColumn(name="customerId") 
	private Customer associatedCustomer;
	
	@ManyToOne
	@JoinColumn(name="serviceId") 
	private Service associatedService;

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getFeedbackDescription() {
		return feedbackDescription;
	}

	public void setFeedbackDescription(String feedbackDescription) {
		this.feedbackDescription = feedbackDescription;
	}

	public Customer getAssociatedCustomer() {
		return associatedCustomer;
	}

	public void setAssociatedCustomer(Customer associatedCustomer) {
		this.associatedCustomer = associatedCustomer;
	}

	public Service getAssociatedService() {
		return associatedService;
	}

	public void setAssociatedService(Service associatedService) {
		this.associatedService = associatedService;
	}

	public Booking() {
		
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", rating=" + rating + ", feedbackDescription=" + feedbackDescription
				+ ", associatedCustomer=" + associatedCustomer + ", associatedService=" + associatedService + "]";
	}
	
	
}
