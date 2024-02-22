package com.foldouts.foldouts.data;

import lombok.Data;

@Data
public class ParsedMessage {
    private ActionCode actionCode;
    private String productId;
    private String senderPhoneNumber;

    public ParsedMessage(String senderPhoneNumber) {
        this.senderPhoneNumber = senderPhoneNumber;
    }
    public ParsedMessage(String productID, ActionCode actionCode) {
        this.productId = productID;
        this.actionCode = actionCode;
    }
    // Enum for action codes
    public enum ActionCode {
        ADD_PRODUCT,
        APPROVE_OPT_IN,
        STOP_OPT_IN,
        HELP,
        PRODUCT_PARSE_ERROR
    }
}
