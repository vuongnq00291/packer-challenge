package com.mobiquityinc.Entity;

import java.util.List;

public class Package {
	private double maxWeight;
	private List<Item> items;

	public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
