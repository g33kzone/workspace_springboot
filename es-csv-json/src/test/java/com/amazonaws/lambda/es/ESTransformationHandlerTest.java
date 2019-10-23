package com.amazonaws.lambda.es;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ESTransformationHandlerTest {

    private KinesisEvent input;

    @Before
    public void createInput() throws IOException {
        input = TestUtils.parse("/kinesis-event.json", KinesisEvent.class);
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }
/*
    @Test
    public void testESTransformationHandler() {
        ESTransformationHandler handler = new ESTransformationHandler();
        Context ctx = createContext();

        Integer output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(1, output.intValue());
    }
    */
}
