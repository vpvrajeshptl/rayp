package com.test.pojo;
// default package
// Generated Jan 24, 2019 3:39:49 PM by Hibernate Tools 5.3.6.Final

/**
 * Employee generated by hbm2java
 */
public class Employee implements java.io.Serializable {

	private Integer id;
	private String lastName;
	private String department;
	private String email;
	private Integer deptId;

	public Employee() {
	}

	public Employee(String lastName, String department, String email, Integer deptId) {
		this.lastName = lastName;
		this.department = department;
		this.email = email;
		this.deptId = deptId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

}
