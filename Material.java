package com.project.nouapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="materials")

public class Material {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int Id;
	@Column(nullable=false, length=100)
	private String program;
	@Column(nullable=false, length=100)
	private String branch;
	@Column(nullable=false, length=100)
	private String year;
	@Column(nullable=false, length=100)
	private String subject;
	@Column(nullable=false, length=500)
	private String topic;
	@Column(nullable=false, length=50)
	
	private String materialtype;
	@Column(nullable=false, length=500)
	private String filename;
	@Column(nullable=false, length=50)
	private String posteddate;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMaterialtype() {
		return materialtype;
	}
	public void setMaterialtype(String materialtype) {
		this.materialtype = materialtype;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPosteddate() {
		return posteddate;
	}
	public void setPosteddate(String posteddate) {
		this.posteddate = posteddate;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	

}
