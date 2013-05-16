package org.zmoog.alchemy.model;

import java.util.Date;

public class Account {
	
	String id;
	
	String name;
	
	String description;
	
	Date createdAt;
	
	String createdBy;

	// Constructors
	
	public Account() {
	}

	public Account(String name) {
		this.name = name;
	}

	// Accessors 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
