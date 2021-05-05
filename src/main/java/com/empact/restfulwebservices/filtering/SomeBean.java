package com.empact.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
//import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonFilter("SomeBeanFilter")
public class SomeBean {
	//@JsonIgnore //static filtering
	private String fields1;
	private String fields2;
	private String fields3;
	public SomeBean(String fields1, String fields2, String fields3) {
		super();
		this.fields1 = fields1;
		this.fields2 = fields2;
		this.fields3 = fields3;
	}
	public String getFields1() {
		return fields1;
	}
	public void setFields1(String fields1) {
		this.fields1 = fields1;
	}
	public String getFields2() {
		return fields2;
	}
	public void setFields2(String fields2) {
		this.fields2 = fields2;
	}
	public String getFields3() {
		return fields3;
	}
	public void setFields3(String fields3) {
		this.fields3 = fields3;
	}
	

}
