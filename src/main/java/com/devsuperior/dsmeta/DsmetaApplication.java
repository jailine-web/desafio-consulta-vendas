package com.devsuperior.dsmeta;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@SpringBootApplication
public class DsmetaApplication implements CommandLineRunner {

	@Autowired
	private SaleRepository repository;

//	private Pageable pageable;

	public static void main(String[] args) {
		SpringApplication.run(DsmetaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<SellerMinDTO> list = repository.searchSalesJpql(LocalDate.parse("2022-01-01"), 
				LocalDate.parse("2022-06-30"));
		
		System.out.println("\nInício da consulta JPQL");
		
		for(SellerMinDTO s : list) {
			System.out.println(s);
		}
		
		System.out.println("\nFim da consulta JPQL");

	}

}
