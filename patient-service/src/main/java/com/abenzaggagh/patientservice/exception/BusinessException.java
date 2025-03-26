package com.abenzaggagh.patientservice.exception;

import java.util.Locale;

import static com.abenzaggagh.patientservice.configuration.MessageSourceConfiguration.getMessageForLocale;

public class BusinessException extends RuntimeException {

    private final String messageKey;
    private final Locale locale;
    private final Object[] args;

    public BusinessException(String message) {
        super(message);
        this.messageKey = message;
        this.locale = Locale.getDefault();
        this.args = new Object[0];
    }

    public BusinessException(String message, Locale locale, Object... args) {
        super(message);
        this.messageKey = message;
        this.locale = locale;
        this.args = args;
    }

    public String getLocalizedMessage() {
        return getMessageForLocale(messageKey, locale, args);
    }


}
