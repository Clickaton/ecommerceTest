package com.ventas.eCommerce.controller;

import com.ventas.eCommerce.Services.ProductService;
import com.ventas.eCommerce.Services.UserService;
import com.ventas.eCommerce.entities.Product;
import com.ventas.eCommerce.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;
import com.ventas.eCommerce.repositories.ProductRepository;

import java.math.BigDecimal;
import com.ventas.eCommerce.enums.Category;

import com.ventas.eCommerce.repositories.UserRepository;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;



    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Integer id, ModelMap model) {
        Optional<Product> p = productRepository.findById(id);
        if (p.isPresent()) {
            Product prod = p.get();
            prod.setCreationDeletion(false);
            productRepository.save(prod);
        }
        return "redirect:/admin/products";
    }

    @PostMapping("/product/activate/{id}")
    public String activateProduct(@PathVariable Integer id, ModelMap model) {
        Optional<Product> p = productRepository.findById(id);
        if (p.isPresent()) {
            Product prod = p.get();
            prod.setCreationDeletion(true);
            productRepository.save(prod);
        }
        return "redirect:/admin/products";
    }

    @PostMapping("/product/deactivate/{id}")
    public String deactivateProduct(@PathVariable Integer id, ModelMap model) {
        Optional<Product> p = productRepository.findById(id);
        if (p.isPresent()) {
            Product prod = p.get();
            prod.setCreationDeletion(false);
            productRepository.save(prod);
        }
        return "redirect:/admin/products";
    }


    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Integer id, ModelMap model) {
        Optional<Product> p = productRepository.findById(id);
        if(p.isPresent()){
            model.put("product", p.get());
            return "AdminProductEdit.html";
        }
        return "redirect:/admin/products";
    }

    @PostMapping("/product/update/{id}")
    public String updateProduct(@PathVariable Integer id, String name, String description, BigDecimal price, Integer stock, ModelMap model) {
        Optional<Product> p = productRepository.findById(id);
        if(p.isPresent()){
            Product prod = p.get();
            if(name != null) prod.setName(name);
            if(description != null) prod.setDescription(description);
            if(price != null) prod.setPrice(price);
            if(stock != null) prod.setStock(stock);
            productRepository.save(prod);
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable Integer id, ModelMap model) {
        Optional<User> u = userRepository.findById(id);
        if(u.isPresent()){
            model.put("user", u.get());
            return "AdminUserEdit.html";
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable Integer id, String name, String lastName, String email, String phone, ModelMap model) {
        Optional<User> u = userRepository.findById(id);
        if(u.isPresent()){
            User user = u.get();
            if(name != null) user.setName(name);
            if(lastName != null) user.setLastName(lastName);
            if(email != null) user.setEmail(email);
            if(phone != null) user.setPhone(phone);
            userRepository.save(user);
        }
        return "redirect:/admin/users";
    }


    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Integer id, ModelMap model) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
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
