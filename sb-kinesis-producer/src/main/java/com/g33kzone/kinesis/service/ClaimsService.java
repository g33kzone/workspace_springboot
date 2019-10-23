package com.g33kzone.kinesis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.g33kzone.kinesis.DataProducer;
import com.g33kzone.kinesis.model.Claims;
import com.g33kzone.kinesis.repository.ClaimsRepository;

@Service
public class ClaimsService {

	@Autowired
	private DataProducer dataProducer;

	@Autowired
	private ClaimsRepository claims;

	public void fetchDBClaims() {

		List<Claims> claimsList = claims.findAll();

		for (Claims claims : claimsList) {
			pushDataKinesisStream(claims);
		}

	}

	private void pushDataKinesisStream(Claims claims) {

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
	}
}
