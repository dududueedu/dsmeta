package com.edudev.dsmeta.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudev.dsmeta.entities.Sale;
import com.edudev.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository repo;
	
	public List<Sale> findSales() {
		return repo.findAll();
	}
}
