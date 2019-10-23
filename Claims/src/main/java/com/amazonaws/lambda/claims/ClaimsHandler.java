package com.amazonaws.lambda.claims;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class ClaimsHandler implements RequestHandler<S3Event, String> {

    private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();
    private AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    private DynamoDB dynamoDB = new DynamoDB(client);
    
    private static String tableName = "Claims";

    public ClaimsHandler() {}

    ClaimsHandler(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public String handleRequest(S3Event event, Context context) {
    	
    	int claim_id;
		String customer_id;
		String claim_type;
		Float claim_amount;
		String country;
		String ipAddr;
		
        context.getLogger().log("Received event: " + event);

        // Get the object from the event and show its content type
        String bucket = event.getRecords().get(0).getS3().getBucket().getName();
        String key = event.getRecords().get(0).getS3().getObject().getKey();
        
        try {
            S3Object response = s3.getObject(new GetObjectRequest(bucket, key));
            String contentType = response.getObjectMetadata().getContentType();
            
            context.getLogger().log("CONTENT TYPE: " + contentType);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getObjectContent()));
            
            
            String csvOutput;
            
            while ((csvOutput = br.readLine()) != null) {
				String[] str = csvOutput.split(",");

				
				claim_id = Integer.valueOf(str[0]);
				customer_id = str[1];
				claim_type = str[2];
				claim_amount = Float.valueOf(str[3]);
				country = str[4];
				ipAddr = str[5];
				
				createDynamoItem(claim_id, customer_id,claim_type,claim_amount,country,ipAddr);
			}
            
            return contentType;
        } catch (Exception e) {
            e.printStackTrace();
            context.getLogger().log(String.format(
                "Error getting object %s from bucket %s. Make sure they exist and"
                + " your bucket is in the same region as this function.", key, bucket));
            return e.toString();
        }
    }
    
    private void createDynamoItem(int claim_id, String customer_id, String claim_type,float claim_amount,String country,String ipAddr) {
		Table table = dynamoDB.getTable(tableName);

		try {
			Item item = new Item().withPrimaryKey("claim_id", claim_id)
					.withString("customer_id", customer_id).withString("claim_type", claim_type)
					.withFloat("claim_amount", claim_amount).withString("country", country).withString("ipAddr", ipAddr);
			
			table.putItem(item);

		} catch (Exception e) {
			System.err.println("Create item failed.");
			System.err.println(e.getMessage());
		}
	}
}