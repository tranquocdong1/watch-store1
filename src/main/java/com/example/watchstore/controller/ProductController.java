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

    // 4. Thêm sản phẩm mới (POST)
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    // 5. Cập nhật thông tin sản phẩm (PUT)
    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    // 6. Xóa sản phẩm (DELETE)
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product with ID " + id + " has been deleted.");
    }
}
