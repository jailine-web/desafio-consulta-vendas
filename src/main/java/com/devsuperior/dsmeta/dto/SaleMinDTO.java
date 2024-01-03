package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleProjection;

public class SaleMinDTO {

	private Long id;
	private Double amount;
	private LocalDate date;
	private String name;
	
	public SaleMinDTO(Long id, Double amount, LocalDate date, String name) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.name = name;
	}
	
	public SaleMinDTO(Sale entity) {
		id = entity.getId();
		amount = entity.getAmount();
		date = entity.getDate();
	}

	public SaleMinDTO(SaleProjection projection) {
		id     = projection.getId();
		amount = projection.getAmount();
		date   = projection.getDate();
		name   = projection.getName();
	}
	
	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "SaleMinDTO [id=" + id + ", amount=" + amount + ", date=" + date 
				+ ", name=" + name + "]";
	}
	
	
	
}
