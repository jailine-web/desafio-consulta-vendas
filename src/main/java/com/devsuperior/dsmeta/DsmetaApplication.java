package com.devsuperior.dsmeta;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.projections.SaleProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@SpringBootApplication
public class DsmetaApplication implements CommandLineRunner{

	@Autowired
	private SaleRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(DsmetaApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		List<SaleProjection> list = repository.searchReport(LocalDate.parse("2022-05-01") , LocalDate.parse("2022-05-31")  , "Odinson");
		List<SaleMinDTO> result = list.stream().map(x -> new SaleMinDTO(x)).collect(Collectors.toList());
		
		for(SaleMinDTO dto: result) {
			System.out.println(dto);
		}
		
	}
}
