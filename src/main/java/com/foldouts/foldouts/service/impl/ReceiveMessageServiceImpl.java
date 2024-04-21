package com.foldouts.foldouts.service.impl;

import com.foldouts.foldouts.data.*;
import com.foldouts.foldouts.service.CustomerService;
import com.foldouts.foldouts.service.MessageParserService;
import com.foldouts.foldouts.service.ProductService;
import com.foldouts.foldouts.service.ReceiveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiveMessageServiceImpl implements ReceiveMessageService {

    @Autowired
    private MessageParserService messageParserService;

    @Autowired
    ProductService productService;

    @Autowired
    private CustomerService customerService;

    public String receive(Message message) {
        // First parse the message to receive the action code determining how the
        // message will be processed and the relevant product id if needed.
        ParsedMessage parsedMessage = messageParserService.parse(message);

        // Extract the product id in one place if action is product related.
        Product product = null;
        ParsedMessage.ActionCode actionCode = parsedMessage.getActionCode();
        if (actionCode == ParsedMessage.ActionCode.ADD_PRODUCT) {
            product = productService.read(parsedMessage.getProductId());
        }

        // Fetch customer by phone number.
        String senderPhoneNumber = parsedMessage.getSenderPhoneNumber();
        Customer customer = customerService.readByPhoneNumber(senderPhoneNumber);

        // Create the customer if not found in our system and add any pending products if necessary.
        if (customer == null) {
            Customer newCustomer = new Customer(senderPhoneNumber);
            if (product != null) {
                newCustomer.addPendingProduct(product);
            }
            customerService.create(newCustomer);

            return "You are not opted-in to the Foldouts service. \n" +
                    "Please reply \n" +
                    "YES to opt-in \n" +
                    "HELP for more info.";
        } else {
            // Conditions for if customer exists in our system.
            // To-Do Re-factor into separate action code handler service.
            // To-Do set messages as constants in a message response class.
            switch (actionCode) {
                case APPROVE_OPT_IN:
                    // check if opted in,
                    //  If opted in return message already opted in.
                    //  If NOT opted in -> update opt-in status and add pending products to list.
                    //  Update customer
                    //  return message indicating opted in and products added to wishlist.
                    if (customer.isOptedIn()) {
                        return "You are already opted-in to our service!";
                    } else {
                        customer.setOptedIn(true);
                        String pendingProductsAddedMessage = "";
                        if (!customer.getPendingProductAdd().isEmpty()) {
                            pendingProductsAddedMessage = customer.addPendingProductsToWishList();
                        }
                        customerService.update(customer);
                        return "Successfully opted-in to Foldouts service! \n" + pendingProductsAddedMessage;
                    }
                case STOP_OPT_IN:
                    // Change opt in status.
                    // if already opted out return relevant message.
                    // if not opted out, update customer to opted out, return relevant message.
                    if (customer.isOptedIn()) {
                        customer.setOptedIn(false);
                        customerService.update(customer);
                        return "You have successfully opted-out of the Foldouts service! We're sad to see you go :(";
                    } else {
                        return "You're already opted-out :)";
                    }
                case HELP:
                    // return help message based on if opted-in or not.
                    if (customer.isOptedIn()) {
                        return "You are currently opted-in to the Foldouts service! \n" +
                                "Reply STOP to opt-out. \n Reply HELP for more info.";
                    } else {
                        return "You are not opted-in to the Foldouts service. \n" +
                                "Please reply \n" +
                                "YES to opt-in \n" +
                                "HELP for more info.";
                    }
                case ADD_PRODUCT:
                    // Check if opted in
                    // if not opted in, send opt in message, add pending products to list.
                    // if opted in, add product to list return success message with product added to list.
                    if (customer.isOptedIn()) {
                        customer.addProductToWishlist(product);
                        customerService.update(customer);

                        return "Product:\n" +
                                product.getName() + "\n" +
                                "has been added to your wish list!\n" +
                                "View wish list at https://foldouts-front-end.vercel.app";
                    } else {
                        return "You are not opted-in to the Foldouts service \n" +
                                "Please reply YES to opt-in. \n" +
                                product.getName() +
                                "\n" +
                                "Will be added to your wish list upon opt-in :)";
                    }
                default:
                    return "Your response cannot be processed, Please reply \n" +
                            "YES to opt-in \n" +
                            "STOP to opt-out \n" +
                            "HELP for more info";
            }
        }
    }
}
