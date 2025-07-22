package dq.inventory.service;

import dq.inventory.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> listProducts();

    Product getProductById(Integer id);

    Product updateProductById(Integer id, Product product);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);
}
