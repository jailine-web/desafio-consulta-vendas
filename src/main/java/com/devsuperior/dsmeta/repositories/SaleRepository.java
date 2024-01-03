package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query(nativeQuery = true, value = 
			"SELECT  s.id , sr.name , SUM(s.amount) as amount, s.date "
			+ "FROM tb_sales as s "
			+ "INNER JOIN tb_seller as sr "
			+ "ON s.seller_id = sr.id "
			+ "WHERE date between :minDate AND :maxDate "
			+ "AND UPPER(name) LIKE UPPER(CONCAT('%', :name , '%')) "
			+ "GROUP BY s.id ")
	List<SaleProjection> searchReport(LocalDate minDate, LocalDate maxDate, String name);
	
	
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.id, obj.amount, obj.date, obj.seller.name) "
			+ "FROM Sale obj "
			+ "WHERE date between :minDate AND :maxDate "
			+ "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name , '%')) "
			+ "GROUP BY obj.id ")
	Page<SaleMinDTO> searchReportJpql(LocalDate minDate, LocalDate maxDate, 
			String name, Pageable pageable);
	
}
