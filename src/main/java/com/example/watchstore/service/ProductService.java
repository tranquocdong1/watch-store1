package com.example.watchstore.service;

import com.example.watchstore.model.Product;
import com.example.watchstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // Lấy danh sách tất cả sản phẩm
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Lấy sản phẩm theo ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    // Thêm sản phẩm mới
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Cập nhật sản phẩm
    public Product updateProduct(Long id, Product productDetails) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

        // Cập nhật thông tin sản phẩm
        existingProduct.setName(productDetails.getName());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setImageUrl(productDetails.getImageUrl());
        existingProduct.setQuantity(productDetails.getQuantity());

        // Lưu sản phẩm sau khi cập nhật
        return productRepository.save(existingProduct);
    }

    // Xóa sản phẩm
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id); // Xóa sản phẩm khỏi DB
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }
}
