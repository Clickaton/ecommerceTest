package com.ventas.eCommerce.controller;

import com.ventas.eCommerce.Services.ProductService;
import com.ventas.eCommerce.Services.UserService;
import com.ventas.eCommerce.entities.Product;
import com.ventas.eCommerce.entities.User;

import com.ventas.eCommerce.entities.Transaction;
import com.ventas.eCommerce.repositories.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/transactions")
    public String transactions(ModelMap model) {
        List<Transaction> transactions = transactionRepository.findAll();
        model.addAttribute("transactions", transactions);
        return "dashboard.html";
    }


    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard.html";
    }

    @GetMapping("/products")
    public String products(ModelMap model) {
        List<Product> products = productService.productList();
        model.addAttribute("products", products);
        return "admin-products.html";
    }

    @GetMapping("/users")
    public String users(ModelMap model) {
        List<User> users = userService.userList();
        model.addAttribute("users", users);
        return "admin-users.html";
    }
}
