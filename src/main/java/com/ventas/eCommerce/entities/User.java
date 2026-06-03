package com.ventas.eCommerce.entities;

import com.ventas.eCommerce.enums.Rol;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Image image;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Transaction> transaction;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

}
