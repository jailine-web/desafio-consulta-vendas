package com.devsuperior.dsmeta;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.projections.SellerProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@SpringBootApplication
public class DsmetaApplication implements CommandLineRunner {

	@Autowired
	private SaleRepository repository;

	private Pageable pageable;

	public static void main(String[] args) {
		SpringApplication.run(DsmetaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
		List<SellerProjection> list = repository.searchSales(LocalDate.parse("2022-01-01"),
				LocalDate.parse("2022-06-30"));
		List<SellerMinDTO> result = list.stream().map(x -> new SellerMinDTO(x))
				.collect(Collectors.toList());

		System.out.println("\nInício da consulta SQL");

		for (SellerMinDTO dto : result) {
			System.out.println(dto);
		}

		System.out.println("\nFim da consulta SQL");

	}

}
