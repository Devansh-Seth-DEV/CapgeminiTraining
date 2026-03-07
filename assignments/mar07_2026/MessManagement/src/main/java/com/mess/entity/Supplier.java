package com.mess.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Supplier")
public class Supplier {
	@Id
    @Column(name = "Sid")
    private int sid;

    @Column(name = "Sname")
    private String sname;

    @Column(name = "Srank")
    private double srank;

    private String city;

    @OneToMany(mappedBy = "supplier")
    private List<Supply> supplies = new ArrayList<>();
    
    public Supplier() {
    	super();
    }

	public Supplier(int sid, String sname, double srank, String city) {
		this.sid = sid;
		this.sname = sname;
		this.srank = srank;
		this.city = city;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public double getSrank() {
		return srank;
	}

	public void setSrank(double srank) {
		this.srank = srank;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Supply> getSupplies() {
		return supplies;
	}

	public void setSupplies(List<Supply> supplies) {
		this.supplies = supplies;
	}

	@Override
	public String toString() {
		return String.format("Supplier [sid=%s, sname=%s, srank=%s, city=%s]", sid, sname, srank, city);
	}
}
