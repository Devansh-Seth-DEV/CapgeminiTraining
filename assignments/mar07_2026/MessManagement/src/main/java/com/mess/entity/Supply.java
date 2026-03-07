package com.mess.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Supply")
public class Supply {
    @Id
    private int billno;

    @ManyToOne
    @JoinColumn(name = "Sid")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "itemid")
    private Item item;

    private LocalDate datesupply;

    @Column(name = "Price")
    private double price;

    private int qty;
    
    public Supply() {
    	super();
    }

	public Supply(int billno, Supplier supplier, Item item, LocalDate datesupply, double price, int qty) {
		this.billno = billno;
		this.supplier = supplier;
		this.item = item;
		this.datesupply = datesupply;
		this.price = price;
		this.qty = qty;
	}

	public int getBillno() {
		return billno;
	}

	public void setBillno(int billno) {
		this.billno = billno;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public LocalDate getDatesupply() {
		return datesupply;
	}

	public void setDatesupply(LocalDate datesupply) {
		this.datesupply = datesupply;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return String.format("Supply [billno=%s, supplier=%s, item=%s, datesupply=%s, price=%s, qty=%s]", billno,
				supplier, item, datesupply, price, qty);
	}
}
