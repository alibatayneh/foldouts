package com.foldouts.foldouts.service.impl;

import com.foldouts.foldouts.data.Message;
import com.foldouts.foldouts.data.ParsedMessage;
import com.foldouts.foldouts.service.MessageParserService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageParserServiceImpl implements MessageParserService {
    @Override
    public ParsedMessage parse(Message message) {
        String messageBody = message.getMessageBody().toLowerCase(); // Make it non case-sensitive

        ParsedMessage parsedMessage = new ParsedMessage(message.getSenderPhoneNumber());
        parsedMessage.setProductId(null);

        if (messageBody.equals("yes")) {
            parsedMessage.setActionCode(ParsedMessage.ActionCode.APPROVE_OPT_IN);
            return parsedMessage;
        } else if (messageBody.equals("stop")) {
            parsedMessage.setActionCode(ParsedMessage.ActionCode.STOP_OPT_IN);
            return parsedMessage;
        } else if (messageBody.equals("help")) {
            parsedMessage.setActionCode(ParsedMessage.ActionCode.HELP);
            return parsedMessage;
        } else if (messageBody.contains("product-id")) {
            try {
                String productId = extractProductId(messageBody);
                parsedMessage.setProductId(productId);
                parsedMessage.setActionCode(ParsedMessage.ActionCode.ADD_PRODUCT);
                return parsedMessage;
            } catch (IllegalArgumentException e) {
                parsedMessage.setActionCode(ParsedMessage.ActionCode.PRODUCT_PARSE_ERROR);
                return parsedMessage;
            }
        } else {
            // Handle other cases or throw an exception for unknown messages
            parsedMessage.setActionCode(ParsedMessage.ActionCode.HELP);
            return parsedMessage;
        }
    }

    private String extractProductId(String messageBody) {
        // Define a regex pattern to match "product-id[6621be2aa05fc20e0d216369]" and extract "xxx"
        String regex = "product-id\\[([^\\]]+)\\]";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(messageBody);

        // Check if the pattern is found
        if (matcher.find()) {
            // Group 1 of the match contains the value inside the square brackets
            return matcher.group(1);
        } else {
            // Handle the case where the pattern is not found
            throw new IllegalArgumentException("Product ID not found in the specified format");
        }
    }
}
