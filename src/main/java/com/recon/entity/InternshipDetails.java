package com.recon.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@SequenceGenerator(name = "internSeq", initialValue = 1002, allocationSize = 123)
public class InternshipDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "internSeq")
	private Long internId;

	private String profile;

	private String organization;

	private String location;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dateStarted;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dateEnd;
	
	@Lob
	private String description;
	
	
	@ManyToOne
    @JoinColumn(
        name = "username", 
        referencedColumnName = "username"
    )
	private UserInfo userinfo;

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public Long getInternId() {
		return internId;
	}

	public void setInternId(Long internId) {
		this.internId = internId;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "InternshipDetails [internId=" + internId + ", profile=" + profile + ", organization=" + organization
				+ ", location=" + location + ", dateStarted=" + dateStarted + ", dateEnd=" + dateEnd + ", description="
				+ description + "]";
	}
	
	

}
