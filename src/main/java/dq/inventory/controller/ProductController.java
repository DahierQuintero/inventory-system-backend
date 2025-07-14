package dq.inventory.controller;

import dq.inventory.entity.Product;
import dq.inventory.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products") // http://localhost:8082/api/v1/product
@CrossOrigin(value = "http://localhost:4200") //Default port for angular
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<Product> getProducts() {
        //Get all products from the database
        List<Product> products = productService.listProducts();
        LOGGER.info("Products: {}", products);
        return products;
    }

    @PostMapping("/")
    public Product addProduct(@RequestBody Product product) {
        LOGGER.info("Product to add: {}", product);
        return productService.saveProduct(product);
    }
}
