package com.example.watchstore.controller;

import com.example.watchstore.model.Product;
import com.example.watchstore.repository.ProductRepository;
import com.example.watchstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository repo;

    // 1. Lấy danh sách sản phẩm dưới dạng JSON (GET)
    @GetMapping("/api")
    @ResponseBody
    public List<Product> getAllProductsApi() {
        return productService.getAllProducts();
    }

    // 2. Lấy sản phẩm theo ID (GET)
    @GetMapping("/api/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // 3. Hiển thị danh sách sản phẩm trên giao diện (GET)
    @GetMapping({"", "/products"})
    public String ShowProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products";
    }


    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu không tìm thấy sản phẩm
        }
        productService.deleteProduct(id); // Xóa sản phẩm
        return ResponseEntity.noContent().build(); // Trả về 204 khi thành công
    }


}
