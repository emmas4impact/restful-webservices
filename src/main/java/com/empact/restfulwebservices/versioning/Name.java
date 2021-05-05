package com.empact.restfulwebservices.versioning;

public class Name {
	private String firstName;
	private String lastName;
	
	public Name() {
		
		
	}
	public Name(String firstNmae, String lastName) {
		super();
		this.firstName = firstNmae;
		this.lastName = lastName;
	}
	public String getFirstNmae() {
		return firstName;
	}
	public void setFirstNmae(String firstNmae) {
		this.firstName = firstNmae;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
