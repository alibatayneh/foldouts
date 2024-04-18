package com.foldouts.foldouts.controller;

import com.foldouts.foldouts.dao.ProductRepository;
import com.foldouts.foldouts.data.Product;
import com.foldouts.foldouts.data.TwimlResponse;
import com.foldouts.foldouts.data.Message;
import com.foldouts.foldouts.service.ReceiveMessageService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/sms")
public class ReceiveMessageController {

    // Testing product creation purposes only.
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ReceiveMessageService receiveMessageService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/receive", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public String receiveMessage(@RequestBody MultiValueMap<String, String> receivedMessage) {

        // Extract the message body and sender phone number into the Message object.
        Message message = new Message(receivedMessage.getFirst("Body"), receivedMessage.getFirst("From"));

        // Use the receive message service to receive the message and return the proper twiml response.
        String responseMessage = receiveMessageService.receive(message);

        TwimlResponse twiml = new TwimlResponse(responseMessage);
        return twiml.getXmlResponse();
    }

//    @PostMapping(value = "/testCreate/{id}")
//    public void TestCreate(@PathVariable String id) {
//        Product product = new Product(id,"Test Product", 0.00);
//			try {
//				repository.insert(product);
//			} catch (DuplicateKeyException e) {
//				System.err.println("Duplicate key found. Details: " + e.getMessage());
//			}
//    }
}