/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.eCommerce.entities;


import com.ventas.eCommerce.enums.Category;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 *
 * @author chris
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Product {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;
    private String brand;
    private java.math.BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Boolean creationDeletion;
    private Integer stock;
    //@OneToMany(MappedBy="Cart")
    //private Cart cart;

}