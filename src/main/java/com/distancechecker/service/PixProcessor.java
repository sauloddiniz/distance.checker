package com.distancechecker.service;

public class PixProcessor implements PaymentProcessor {
    @Override
    public String processPayment() {
        return "Pago com Pix";
    }
}
