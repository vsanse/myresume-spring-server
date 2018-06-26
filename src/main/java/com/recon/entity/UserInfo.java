package com.recon.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class UserInfo {

	@Id
	@Column(name = "username", unique = true, updatable = false, nullable = false)
	private String username;

	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;
	// @JsonIgnore
	private String password;

	private String designation;

	private String currentOrganization;

	private String githubLink;

	private String linkedinLink;

	private String role = "ROLE_USER";

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserInfo() {

	}

	public UserInfo(String username, String firstName, String lastName, String email, String phoneNumber,
			String password, String designation, String currentOrganization, String githubLink, String linkedinLink) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.designation = designation;
		this.currentOrganization = currentOrganization;
		this.githubLink = githubLink;
		this.linkedinLink = linkedinLink;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonIgnore
	@JsonProperty(value = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCurrentOrganization() {
		return currentOrganization;
	}

	public void setCurrentOrganization(String currentOrganization) {
		this.currentOrganization = currentOrganization;
	}

	public String getGithubLink() {
		return githubLink;
	}

	public void setGithubLink(String githubLink) {
		this.githubLink = githubLink;
	}

	public String getLinkedinLink() {
		return linkedinLink;
	}

	public void setLinkedinLink(String linkedinLink) {
		this.linkedinLink = linkedinLink;
	}

	@Override
	public String toString() {
		return "UserDetails [userName=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", password=" + password + ", designation=" + designation
				+ ", currentOrganization=" + currentOrganization + ", githubLink=" + githubLink + ", linkedinLink="
				+ linkedinLink + "]";
	}

}
