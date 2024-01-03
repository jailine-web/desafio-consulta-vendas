package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SellerProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public Page<SaleMinDTO> findReport(String minDate, String maxDate, 
			String name, Pageable pageable) {

		LocalDate dateMax, dateMin;

		if (maxDate.equals("")) {
			dateMax = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}  
		else {
			dateMax = transformDate(maxDate);
		}
		if (minDate.equals("")) {
			dateMin = dateMax.minusYears(1);
		} 
		else {
			dateMin = transformDate(minDate);
		}
		
		Page<SaleMinDTO> report = repository.searchReportJpql(dateMin, dateMax, name, pageable);
		 
		return report;

	}

	private LocalDate transformDate(String date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate formattedDate = LocalDate.parse(date, formatter);
		return formattedDate;
	}
	
	
	public List<SellerMinDTO> findSumary(String dataInicial, String dataFinal){

		LocalDate maxDate, minDate;

		if (dataFinal.equals("")) {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}  
		else {
			maxDate = transformDate(dataFinal);
		}
		if(dataInicial.equals("")) {
			minDate = maxDate.minusYears(1);
		} 
		else {
			minDate = transformDate(dataInicial);
		}

		List<SellerMinDTO> list = repository.searchSalesJpql(minDate, maxDate);
		
		return list;
	}
}
