package com.distancechecker.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentProcessFactoryImpl implements PaymentProcessFactory {
    @Override
    public PaymentProcessor createPaymentProcessor(String paymentType) {
        if (paymentType.equals("PIX")) {
            return new PixProcessor();
        } else return new CreditCardProcessor();
    }
}
