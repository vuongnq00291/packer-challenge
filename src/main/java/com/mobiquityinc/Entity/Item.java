package com.mobiquityinc.Entity;

public class Item {

	private Integer id;
	private Double cost;
	private Double weight;
	
	
	public Item(int id,double cost,double weight){
		this.id = id;
		this.cost = cost;
		this.weight = weight;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	
}
