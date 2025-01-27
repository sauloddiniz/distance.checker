package com.distancechecker.service;

public interface PaymentProcessFactory {
    PaymentProcessor createPaymentProcessor(String paymentType);
}
