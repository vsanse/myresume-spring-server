package com.recon.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="eduSeq", initialValue=1002, allocationSize=123)
public class EducationDetails {
	
    @Id 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="eduSeq")
    private Long educationId;
	
	private Integer yearOfCompletion;
	private String board;
	
	private String Stream;
	private String institution;
	
	private String degree;
	private String performanceScale;
	
	private Double performance;
	
	private String educationType;
	
	@ManyToOne
    @JoinColumn(
        name = "username", 
        referencedColumnName = "username"
    )
	private UserInfo userinfo;
	
	public String getEducationType() {
		return educationType;
	}

	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public Long getId() {
		return educationId;
	}

	public void setId(Long id) {
		this.educationId = id;
	}

	public Integer getYearOfCompletion() {
		return yearOfCompletion;
	}

	public void setYearOfCompletion(Integer yearOfCompletion) {
		this.yearOfCompletion = yearOfCompletion;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getStream() {
		return Stream;
	}

	public void setStream(String stream) {
		Stream = stream;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getPerformanceScale() {
		return performanceScale;
	}

	public void setPerformanceScale(String performanceScale) {
		this.performanceScale = performanceScale;
	}

	public Double getPerformance() {
		return performance;
	}

	public void setPerformance(Double performance) {
		this.performance = performance;
	}
	
	
}
