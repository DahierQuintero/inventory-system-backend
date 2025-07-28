package dq.inventory.controller;

import dq.inventory.entity.Product;
import dq.inventory.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products") // http://localhost:8082/api/v1/product
@CrossOrigin(value = "http://localhost:4200") //Default port for angular
@Tag(name = "Product", description = "Operations related to application products")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    @Operation(
            summary = "Get all products",
            description = "Get all products"
    )
    public ResponseEntity<List<Product>> getProducts() {
        //Get all products from the database
        List<Product> products = productService.listProducts();
        LOGGER.info("Products: {}", products);
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/")
    @Operation(
            summary = "Add a new product",
            description = "Add a new product with the information sent in the request body"
    )
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        LOGGER.info("Product to add: {}", product);
        return ResponseEntity.ok().body(productService.saveProduct(product));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a product by id",
            description = "Get a product by unique id"
    )
    public ResponseEntity<Product> getProductById( @PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a product",
            description = "Update a product with the information sent in the request body"
    )
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        return ResponseEntity.ok().body(productService.updateProductById(id, product));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a product",
            description = "Delete a product by unique id"
    )
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(productService.deleteProduct(id));
    }

}
