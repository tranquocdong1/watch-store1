package com.example.watchstore.model;

import com.example.watchstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Kiểm tra và chỉ thêm dữ liệu nếu bảng chưa có dữ liệu
        if (productRepository.count() == 0) {
            Product product1 = new Product();
            product1.setName("Rolex Submariner");
            product1.setDescription("A luxury diving watch.");
            product1.setPrice(7500);
            product1.setQuantity(10);
            product1.setImageUrl("public/images/w1.png");

            Product product2 = new Product();
            product2.setName("Omega Speedmaster");
            product2.setDescription("A classic chronograph watch.");
            product2.setPrice(5000);
            product2.setQuantity(15);
            product2.setImageUrl("https://example.com/omega.jpg");

            Product product3 = new Product();
            product3.setName("Tag Heuer Carrera");
            product3.setDescription("A sleek, stylish racing watch.");
            product3.setPrice(3000);
            product3.setQuantity(8);
            product3.setImageUrl("https://example.com/tagheuer.jpg");

            Product product4 = new Product();
            product4.setName("Breitling Navitimer");
            product4.setDescription("An iconic aviator's watch.");
            product4.setPrice(4500);
            product4.setQuantity(12);
            product4.setImageUrl("https://example.com/breitling.jpg");

            Product product5 = new Product();
            product5.setName("Patek Philippe Nautilus");
            product5.setDescription("A luxury sports watch.");
            product5.setPrice(30000);
            product5.setQuantity(5);
            product5.setImageUrl("https://example.com/patek.jpg");

            Product product6 = new Product();
            product6.setName("Audemars Piguet Royal Oak");
            product6.setDescription("An elegant watch with octagonal design.");
            product6.setPrice(25000);
            product6.setQuantity(3);
            product6.setImageUrl("https://example.com/audemars.jpg");

            Product product7 = new Product();
            product7.setName("IWC Portuguese Chronograph");
            product7.setDescription("A refined chronograph watch.");
            product7.setPrice(7000);
            product7.setQuantity(9);
            product7.setImageUrl("https://example.com/iwc.jpg");

            Product product8 = new Product();
            product8.setName("Cartier Santos");
            product8.setDescription("A timeless luxury watch with unique design.");
            product8.setPrice(6500);
            product8.setQuantity(7);
            product8.setImageUrl("https://example.com/cartier.jpg");

            Product product9 = new Product();
            product9.setName("Seiko Prospex");
            product9.setDescription("A durable and affordable diving watch.");
            product9.setPrice(800);
            product9.setQuantity(20);
            product9.setImageUrl("https://example.com/seiko.jpg");

            Product product10 = new Product();
            product10.setName("Casio G-Shock");
            product10.setDescription("A robust and versatile digital watch.");
            product10.setPrice(150);
            product10.setQuantity(50);
            product10.setImageUrl("https://example.com/casio.jpg");

            productRepository.saveAll(Arrays.asList(
                    product1, product2, product3, product4, product5,
                    product6, product7, product8, product9, product10
            ));
        }
    }
}
