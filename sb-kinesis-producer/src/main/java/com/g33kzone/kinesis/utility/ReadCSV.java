package com.g33kzone.kinesis.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.g33kzone.kinesis.DataProducer;
import com.g33kzone.kinesis.model.Claims;
import com.g33kzone.kinesis.repository.ClaimsRepository;
import com.opencsv.CSVReader;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadCSV {
	
	@Autowired
	private DataProducer dataProducer;
	
	@Autowired
	private ClaimsRepository claims;

	private static final String SAMPLE_CSV_FILE_PATH = "./config/claims_data.csv";
	
	public void csvReader() {
		
		try (
				
	            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
						
	            CSVReader csvReader = new CSVReader(reader);
	        ) {
	            // Reading Records One by One in a String array
	            String[] nextRecord;
	            while ((nextRecord = csvReader.readNext()) != null) {
	                
	                Claims claims = new Claims();
	                claims.setClaimId(Integer.valueOf(nextRecord[0]));
	                claims.setCustomerId(nextRecord[1]);
	                claims.setClaimType(nextRecord[2]);
	                claims.setClaimAmount(Float.valueOf(nextRecord[3]));
	                claims.setCountry(nextRecord[4]);
	                claims.setIpAddress(nextRecord[5]);
	                
	                pushDataKinesisStream(claims);
	            }
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	
	public void fetchDBClaims() {

		
		List<Claims> claimsList = claims.findAll();
		
		for (Claims claims : claimsList) {
            pushDataKinesisStream(claims);
		}
		
	}
	
	private void pushDataKinesisStream(Claims claims) {
		ObjectMapper mapper = new ObjectMapper();
		//mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		String payloadData = null;
		
		try {
			//payloadData = mapper.writeValueAsString(claims);
			//payloadData = mapper.writer(new MinimalPrettyPrinter("\n")).writeValueAsString(claims);
			
			payloadData = claims.toString();
			
			System.out.println(payloadData);
			
			dataProducer.putIntoKinesis("claims-kinesis-stream", "12345", payloadData);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
