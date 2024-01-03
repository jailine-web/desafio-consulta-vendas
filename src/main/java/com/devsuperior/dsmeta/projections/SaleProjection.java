package com.devsuperior.dsmeta.projections;

import java.time.LocalDate;

public interface SaleProjection {

	Long getId();
	LocalDate getDate();
	Double getAmount();
	String getName();
	
}
