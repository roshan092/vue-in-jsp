package com.newscorp.subscription.service.impl;

import com.newscorp.subscription.service.ILogUtilService;
import org.springframework.stereotype.Service;

@Service
public class LogUtilService implements ILogUtilService {
    private static final String TRUNCATED_TEXT = " ... (truncated) ... ";
    private static final Integer TRUNCATED_TEXT_LENGTH = 21;

    @Override
    public String truncate(String input, int length) {
        int logLength = input.length();
        if (logLength < TRUNCATED_TEXT_LENGTH + 2 || length < TRUNCATED_TEXT_LENGTH + 2) {
            return input;
        }
        if (logLength <= length) {
            return input;
        }
        StringBuffer truncatedLogBuffer = new StringBuffer();
        int startIndex = ((length - TRUNCATED_TEXT_LENGTH) / 2);
        int offset = ((length + TRUNCATED_TEXT_LENGTH) % 2);
        truncatedLogBuffer.append(input.substring(0, startIndex + offset));
        truncatedLogBuffer.append(TRUNCATED_TEXT);
        truncatedLogBuffer.append(input.substring(logLength - startIndex, logLength));
        return truncatedLogBuffer.toString();
    }
}
