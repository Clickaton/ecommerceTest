/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.eCommerce.repositories;

import com.ventas.eCommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chris
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    
    public List<Product> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    public Product findByName(@Param("name") String email);
    
    
}
