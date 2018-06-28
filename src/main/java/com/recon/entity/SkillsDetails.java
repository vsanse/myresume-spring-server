package com.recon.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="skillsSeq", initialValue=1002, allocationSize=123)
public class SkillsDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="skillsSeq")
	private Long skillId;
	
	private String skill;
	
	@ManyToOne
	@JoinColumn(name = "username", referencedColumnName = "username")
	private UserInfo userinfo;

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
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
