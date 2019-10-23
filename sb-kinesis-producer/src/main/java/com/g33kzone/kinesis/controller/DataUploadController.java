package com.g33kzone.kinesis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.g33kzone.kinesis.DataProducer;
import com.g33kzone.kinesis.model.Claims;
import com.g33kzone.kinesis.service.ClaimsService;

@RestController
public class DataUploadController {
	
	@Autowired
	private DataProducer dataProducer;
	
	@Autowired
	private ClaimsService claimsService;
	
	@PostMapping(value="/streams/api")
	public ResponseEntity<String> dataUpload(@RequestBody Claims claims){
		
		String payloadData = null;
		
		try {
			payloadData = claims.toString();
			
			System.out.println(payloadData);
			
			dataProducer.putIntoKinesis("claims-kinesis-stream", "12345", payloadData);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Kinesis Stream - Single Request Demo...");
		
	}
	
	@GetMapping(value="/streams/batch")
	public ResponseEntity<String> readCSV(){
		
		claimsService.fetchDBClaims();
		
		return ResponseEntity.status(HttpStatus.OK).body("Kinesis Stream - Batch Request Demo...");
		
	}

}
