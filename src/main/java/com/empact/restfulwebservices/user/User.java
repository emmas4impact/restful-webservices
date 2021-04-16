package com.empact.restfulwebservices.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {
	private Integer id;
	@Size(min=2, message="Name should have at least two charater")
	private String name;
	
	@Past(message="Date can not be in the future.")
	private Date birthdate;
	
	protected User() {
		
	}
	
	public User(Integer id, String name, Date birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	

}
