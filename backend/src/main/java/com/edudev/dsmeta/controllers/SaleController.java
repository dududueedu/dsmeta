package com.edudev.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edudev.dsmeta.entities.Sale;
import com.edudev.dsmeta.services.SaleService;
import com.edudev.dsmeta.services.SmsService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {
	
	@Autowired
	private SaleService serv;
	
	@Autowired
	private SmsService smsServ;
	
	@GetMapping
	public Page<Sale> findAllSales(
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate,
			Pageable pageable){
		return serv.findSales(minDate, maxDate, pageable);
	}

	@GetMapping("/{id}/notification")
	public ResponseEntity<?> notifySms(@PathVariable Long id) {
		if(id.equals(null)) {
			return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
		}
		smsServ.sendSms(id);
		return new ResponseEntity<>("Ok!", HttpStatus.OK);
	}
}
