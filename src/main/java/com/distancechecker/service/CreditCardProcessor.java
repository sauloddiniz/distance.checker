package com.distancechecker.service;

public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public String processPayment() {
        return "Pago Com Cartao de credito";
    }
}
