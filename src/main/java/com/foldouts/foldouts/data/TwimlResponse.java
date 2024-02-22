package com.foldouts.foldouts.data;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import lombok.Data;

@Data
public class TwimlResponse {
    private String messageBody;

    public TwimlResponse(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getXmlResponse() {
        // String responseMessage = String.format("Response received. message: %s", receivedMessage.getFirst("Body"));
        Body body = new Body.Builder(this.messageBody).build();
        Message sms = new Message.Builder().body(body).build();
        MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();
        return twiml.toXml();
    }

}
