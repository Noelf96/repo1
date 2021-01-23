package com.app.pojos;
import javax.persistence.*;

@Entity
@Table(name="Helpster_tbl")
public class Helpster {
	

//	@TableGenerator(name = "tbl", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "Addr_Gen", initialValue = 10000, allocationSize = 100)
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer hId;
	
	@Column(length = 20)
	private String firstName;
	
	@Column(length = 20)
	private String lastName;
	
	@Column(length = 25, unique = true)
	private String email;
	
	@Column(length = 25)
	private String password;

	@Column(length = 15,unique = true , nullable = false)
	private String contactNo;
	
	@Column(length = 15 , columnDefinition = "tinyint(1) default 1")
	private boolean available;
	
	@Column(columnDefinition = "float default 0")
	private float rating;
	
	public Helpster() {}
	
	public Integer getHId() {
		return hId;
	}

	public void setHelpsterId(Integer hId) {
		this.hId = hId;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Helpster [helpsterId=" + hId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + ", contactNo=" + contactNo + ", availibility=" + available
				+ ", rating=" + rating + "]";
	}
		
}
