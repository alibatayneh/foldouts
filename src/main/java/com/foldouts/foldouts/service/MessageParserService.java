package com.foldouts.foldouts.service;

import com.foldouts.foldouts.data.Message;
import com.foldouts.foldouts.data.ParsedMessage;

public interface MessageParserService {
    public ParsedMessage parse(Message message);
}
