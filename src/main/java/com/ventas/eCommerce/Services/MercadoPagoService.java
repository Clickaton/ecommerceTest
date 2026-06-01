package com.ventas.eCommerce.Services;

import org.springframework.stereotype.Service;

@Service
public class MercadoPagoService {

    public boolean processPayment(String cardNumber, Double amount) {
        // Mock implementation of MercadoPago payment processing
        System.out.println("Processing payment for card: " + cardNumber + " amount: " + amount);
        return true; // Assume payment is always successful for the mock
    }
}
