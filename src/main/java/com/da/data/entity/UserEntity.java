package com.da.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@Column(name = "username")
	String username;
	
	@Column(name = "password")
	String password;
	
	@Column(name = "first_name")
	String firstName;
	
	@Column(name = "last_name")
	String lastName;
	
	public UserEntity() {
		
	}
	
	public UserEntity(long id, String username, String password, String firstName, String lastName) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * getter for id
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Setter for Id
	 * @param id id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * getter for username
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * setter for username
	 * @param username username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * getter for password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * setter for password
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Getter for first name
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Setter for first name
	 * @param firstName first Name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * getter for last name
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Setter for last name
	 * @param lastName last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
