package com.gal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Parking {
	@Id
	@Column(name="parking_id")
	private int parkingId;
	
	@Column(name="floor_number")
	private int floorNo;
	
	@Column(name="slot_number")
	private String slotNo;
	
	public Parking() {
		super();
	}

	public Parking(int parkingId, int floorNo, String slotNo) {
		super();
		this.parkingId = parkingId;
		this.floorNo = floorNo;
		this.slotNo = slotNo;
	}

	public int getParkingId() {
		return parkingId;
	}

	public void setParkingId(int parkingId) {
		this.parkingId = parkingId;
	}

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public String getSlotNo() {
		return slotNo;
	}

	public void setSlotNo(String slotNo) {
		this.slotNo = slotNo;
	}
	
	@Override
	public String toString() {
		return """
				parking: {
				  parking_id: %s,
				  floor_number: %s,
				  slot_number: '%s'
				}
				"""
				.formatted(
					parkingId,
					floorNo,
					slotNo
				);
	}
}
