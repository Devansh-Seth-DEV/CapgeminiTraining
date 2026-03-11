package com.gal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="vendor_id")
	private int vendorId;
	
	@Column(name="vendor_name")
	String vendorName;
	
	public Vendor() {
		super();
	}

	public Vendor(
		int vendorId,
		String vendorName
	) {
		this.vendorId = vendorId;
		this.vendorName = vendorName;
	}
	
	public Vendor(String vendorName) {
		this(0, vendorName);
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	@Override
	public String toString() {
		return "Vendor[vendorId: %s, vendorName: '%s']"
				.formatted(
					vendorId,
					vendorName
				);
	}
}
