package com.foldouts.foldouts.service;

import com.foldouts.foldouts.data.Message;
import com.foldouts.foldouts.data.TwimlResponse;

public interface ReceiveMessageService {
    String receive(Message message);
}
