package com.app.pojos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Service_tbl")
public class Service {
//serviceId,serviceType,description,city,locality
	// noOforders,rating,category
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serviceId;

	@Column(length = 20)
	private String serviceName;

	@Column(length = 20)
	private String category;

	@Column(length = 20)
	private String description;

	@Column(length = 20)
	private String city;

	@Column(length = 20)
	private String locality;

	private boolean currentlyAvailable;

	@ElementCollection(targetClass = DayOfWeek.class, fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)

	@CollectionTable(name = "Days_of_week")
	@Column(length = 10)
	private List<DayOfWeek> availableDays = new ArrayList<>();

	@Column
	private LocalTime startTime;

	@Column
	private LocalTime endTime;

	@Column(columnDefinition = "int default 0")
	private int noOfOrders;

	@Column(columnDefinition = "float default 0")
	private float rating;
	
	  @ManyToOne(cascade = CascadeType.ALL)	  
	  @JoinColumn(name="helpster_id") 
	  private Helpster associatedHelpster;

	
	  public Helpster getAssociatedHelpster() { return associatedHelpster; }
	  
	  
	  public void setAssociatedHelpster(Helpster associatedHelpster) {
	  this.associatedHelpster = associatedHelpster; }
	 
	public Service() {
		// TODO Auto-generated constructor stub
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public List<DayOfWeek> getAvailableDays() {
		return availableDays;
	}

	public void setAvailableDays(List<DayOfWeek> availableDays) {
		this.availableDays = availableDays;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public int getNoOfOrders() {
		return noOfOrders;
	}

	public void setNoOfOrders(int noOfOrders) {
		this.noOfOrders = noOfOrders;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public boolean isCurrentlyAvailable() {

		for (DayOfWeek day : availableDays)
			if (day.equals(LocalDate.now().getDayOfWeek()))
				if (startTime.isBefore(LocalTime.now()) && endTime.isAfter(LocalTime.now()))
					return true;
		return false;
	}

	public void setCurrentlyAvailable(boolean currentlyAvailable) {
		this.currentlyAvailable = currentlyAvailable;
	}

	@Override
	public String toString() {
		return "Service [serviceId=" + serviceId + ", serviceName=" + serviceName + ", category=" + category
				+ ", description=" + description + ", city=" + city + ", locality=" + locality + ", currentlyAvailable="
				+ currentlyAvailable + ", availableDays=" + availableDays + ", startTime=" + startTime + ", endTime="
				+ endTime + ", noOfOrders=" + noOfOrders + ", rating=" + rating + ", feedbackDescription=" + "]";
	}

}