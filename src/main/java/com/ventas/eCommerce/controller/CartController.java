/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.eCommerce.controller;

import com.ventas.eCommerce.Services.CartService;
import com.ventas.eCommerce.entities.Cart;
import com.ventas.eCommerce.entities.Product;
import com.ventas.eCommerce.entities.User;
import javax.servlet.http.HttpSession;

import com.ventas.eCommerce.Services.TransactionService;
import java.time.LocalDateTime;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import java.math.BigDecimal;


/**
 *
 * @author chris
 */
@Controller
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private TransactionService transactionService;

    @PostMapping("/checkout")
    public String checkout(HttpSession session, ModelMap model) {
        try {
            User logueado = (User) session.getAttribute("usuariosession");
            if (logueado == null) {
                return "redirect:/user/login";
            }
            Cart cart = logueado.getCart();
            if (cart == null || cart.getProducts() == null || cart.getProducts().isEmpty()) {
                model.put("error", "El carrito está vacío.");
                return "cart.html";
            }

            BigDecimal totalAmount = BigDecimal.ZERO;
            for (Product p : cart.getProducts()) {
                if(p.getPrice() != null) {
                    totalAmount = totalAmount.add(p.getPrice());
                }
            }

            transactionService.register(LocalDateTime.now(), "00000000", "0000-0000-0000-0000", LocalDate.now().plusYears(1), cart, "Direccion registrada", logueado, totalAmount.doubleValue());
            // Clear cart logic could go here
            model.put("exito", "Compra realizada exitosamente.");
            return "inicio.html";
        } catch (Exception e) {
            model.put("error", "Error procesando la compra: " + e.getMessage());
            return "cart.html";
        }
    }

    @Autowired
    private CartService cartService;

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/addProduct/{id}")
    public String AddToCart(@PathVariable Integer id, HttpSession session) {
        User logueado=(User) session.getAttribute("usuariosession");
        Integer idUsuario = logueado.getId();
        cartService.AddProductToCart(id, logueado);
        return "redirect:/product/catalogue"; // Redirige a la página de catálogo después de agregar el producto al carrito
    }

    @GetMapping("/myCart/{id}")
    public String myCart(@PathVariable Integer id, HttpSession session, ModelMap model) {
        User logueado = (User) session.getAttribute("usuariosession");
        Integer idUsuario = logueado.getId();

        List<Product> listaCarrito = cartService.getProductosEnCarrito(idUsuario);

        // Agregar la lista al modelo
        model.addAttribute("listaCarrito", listaCarrito);

        return "cart.html";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProductFromCart(@PathVariable Integer productId, HttpSession session) {
        User user = (User) session.getAttribute("usuariosession");
        Integer userId = user.getId();

        // Llamar al servicio para eliminar el producto del carrito
        cartService.deleteProductFromCart(productId, userId);

        // Redirigir a la página del carrito con el userId en la URL
        return "redirect:/cart/myCart/" + userId;
    }


/*
    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable Integer id, HttpSession session, ModelMap model) {
        try {
            serviciosLibro.eliminarLibro(isbn);
            modelo.put("exito", "El libro fue eliminado correctamente");
        } catch (MiException ex) {
            Logger.getLogger(LibroControlador.class.getName()).log(Level.SEVERE, null, ex);
            List<Autor> autores = serviciosAutor.listarAutores();
            List<Editorial> editoriales = serviciosEditorial.listarEditorial();
            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            modelo.put("error", ex.getMessage());
        }
        return "redirect:/libro/lista"; // Redirigir a la lista de libros después de la eliminación
    }
*/

}
