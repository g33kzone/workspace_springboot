package com.amazonaws.lambda.es;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.lambda.es.Claims;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent.KinesisEventRecord;

public class ESTransformationHandler implements RequestHandler<KinesisEvent, List<Claims>> {

    @Override
    public List<Claims> handleRequest(KinesisEvent event, Context context) {
        context.getLogger().log("Input: " + event);
        
        List<Claims> claimList = new ArrayList<Claims>();

        for (KinesisEventRecord record : event.getRecords()) {
            String payload = new String(record.getKinesis().getData().array());
            context.getLogger().log("Payload: " + payload);
            
            Claims claim = new Claims();
            
            String[] str = payload.split(",");

            claim.setClaimId(Integer.valueOf(str[0]));
            claim.setCustomerId(str[1]);
            claim.setClaimType(str[2]);
            claim.setClaimAmount(Float.valueOf(str[3]));
            claim.setCountry(str[4]);
            claim.setIpAddress(str[5]);
            
            claimList.add(claim);
        }

        return claimList;
    }
}
	