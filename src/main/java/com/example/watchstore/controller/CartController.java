package com.example.watchstore.controller;

import com.example.watchstore.model.CartItem;
import com.example.watchstore.model.Product;
import com.example.watchstore.service.CartService;
import com.example.watchstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity, Model model) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            cartService.addProductToCart(product, quantity);
        }
        // Sau khi thêm sản phẩm, chuyển hướng đến trang giỏ hàng
        return "redirect:/cart/view";
    }

    @GetMapping("/view")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalAmount", cartService.getTotalAmount());
        return "cart"; // Trả về tên của file HTML 'cart.html'
    }

}

