package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SellerProjection;

public class SellerMinDTO {

	private String name;
	private Double amount;
	
	public SellerMinDTO(String name, Double amount) {
		this.name = name;
		this.amount = amount;
		
	}
	
	public SellerMinDTO(SellerProjection seller) {
		name = seller.getName();
		amount = seller.getAmount();
		
	}

	public String getName() {
		return name;
	}

	public Double getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "Resultado [name=" + name + ", quantia=" + amount + "]";
	}

}
