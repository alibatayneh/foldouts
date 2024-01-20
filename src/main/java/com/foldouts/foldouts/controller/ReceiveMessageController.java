package com.foldouts.foldouts.controller;

import com.foldouts.foldouts.dao.ProductRepository;
import com.foldouts.foldouts.data.Product;
import com.twilio.twiml.messaging.Message;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;

@RestController
@RequestMapping("/v1/sms")
public class ReceiveMessageController {

    // Testing product creation purposes only.
    @Autowired
    private ProductRepository repository;

    @PostMapping(value = "/receive",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public String receiveMessage(@RequestBody MultiValueMap<String, String> message) {
        String responseMessage = String.format("Response received. message: %s", message.getFirst("Body"));
        Body body = new Body.Builder(responseMessage).build();
        Message sms = new Message.Builder().body(body).build();
        MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();
        return twiml.toXml();
    }

    @PostMapping(value = "/testCreate/{id}")
    public void TestCreate(@PathVariable String id) {
        Product product = new Product(id,"Test Product", 0.00);
			try {
				repository.insert(product);
			} catch (DuplicateKeyException e) {
				System.err.println("Duplicate key found. Details: " + e.getMessage());
			}
    }
}