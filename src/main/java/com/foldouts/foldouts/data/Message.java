package com.foldouts.foldouts.data;

import lombok.Data;

@Data
public class Message {
    private String messageBody;
    private String senderPhoneNumber;

    public Message(String messageBody, String customerPhoneNumber){
        this.messageBody = messageBody;
        this.senderPhoneNumber = customerPhoneNumber;
    }
}
