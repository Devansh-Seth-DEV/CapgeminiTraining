package com.gal.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="cabs")
public class Cab {
	@Id
	@Column(name="cab_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cabId;
	
	@OneToMany(mappedBy = "cab")
	private List<CabAssignment> assignments;

	public int getCabId() {
		return cabId;
	}

	public void setCabId(int cabId) {
		this.cabId = cabId;
	}
}
