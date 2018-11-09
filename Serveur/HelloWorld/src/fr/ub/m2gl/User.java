package fr.ub.m2gl;

import com.fasterxml.jackson.annotation.JsonProperty;


public class User {
	@JsonProperty("Firstname")
	private String firstname;
	@JsonProperty("Lastname")
	private String lastname;
	
	public User() {
		
	}
	
	public User(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
}
