package com.recon.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="additionalSeq", initialValue=1002, allocationSize=123)
public class AdditionalDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="additionalSeq")
	private Long addid;
	
	@Lob
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "username", referencedColumnName = "username")
	private UserInfo userinfo;

	public Long getAddid() {
		return addid;
	}

	public void setAddid(Long addid) {
		this.addid = addid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonIgnore
	@JsonProperty(value = "userinfo")
	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
	
	
}
