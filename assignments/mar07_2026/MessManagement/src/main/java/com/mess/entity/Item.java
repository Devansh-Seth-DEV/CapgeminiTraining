package com.mess.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    private int itemid;
    private String name;

    @Column(name = "Type")
    private String type;

    @OneToMany(mappedBy = "item")
    private List<Supply> supplies = new ArrayList<>();
    
    public Item() {
    	super();
    }

	public Item(int itemid, String name, String type) {
		this.itemid = itemid;
		this.name = name;
		this.type = type;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Supply> getSupplies() {
		return supplies;
	}

	public void setSupplies(List<Supply> supplies) {
		this.supplies = supplies;
	}

	@Override
	public String toString() {
		return String.format("Item [itemid=%s, name=%s, type=%s]", itemid, name, type);
	}
 }
