package com.gal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="item_id")
	private int itemId;
	
	@Column(name="item_type")
	String itemType;
	
	@Column(name="item_name")
	String itemName;
	
	@Column(nullable=false)
	double cost;
	
	public Item() {
		super();
	}
	
	public Item(
		int itemId,
		String itemType,
		String itemName,
		double cost
	) {
		super();
		this.itemId = itemId;
		this.itemType = itemType;
		this.itemName = itemName;
		this.cost = cost;
	}
	
	public Item(
		String itemType,
		String itemName,
		double cost
	) {
		this(0, itemType, itemName, cost);
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return "Item[itemId: %s, itemType: '%s', itemName: '%s', cost: %s]"
			   .formatted(
				  itemId,
				  itemType,
				  itemName,
				  cost
			   );
	}
}
