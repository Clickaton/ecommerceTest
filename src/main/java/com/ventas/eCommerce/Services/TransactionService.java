/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.eCommerce.Services;

import com.ventas.eCommerce.entities.Cart;
import com.ventas.eCommerce.entities.Transaction;
import com.ventas.eCommerce.repositories.TransactionRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ventas.eCommerce.entities.User;

/**
 *
 * @author chris
 */
@Service
public class TransactionService {
    

    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private EmailService emailService;

    @Autowired
    private MercadoPagoService mercadoPagoService;


    public void register(LocalDateTime localDateTime, String dni, String cardNumber, LocalDate expirationDate, Cart cart, String address, User user, Double amount){

        boolean paymentSuccess = mercadoPagoService.processPayment(cardNumber, amount);

        if (!paymentSuccess) {
            throw new RuntimeException("Payment failed");
        }

        Transaction transaction = new Transaction();
        
        transaction.setDni(dni);
        transaction.setLocalDateTime(localDateTime);
        transaction.setAddress(address);
        transaction.setCardNumber(cardNumber);
        transaction.setExpirationDate(expirationDate);
        transaction.setCart(cart);
        transaction.setUser(user);
        
        transactionRepository.save(transaction);

        if (user != null && user.getEmail() != null) {
            emailService.sendSimpleMessage(user.getEmail(), "Confirmación de compra", "Su compra ha sido procesada con éxito.");
        }
    }
    
}
