package dq.inventory.service;

import dq.inventory.entity.Product;

import java.util.List;
import java.util.Map;

public interface IProductService {
    List<Product> listProducts();

    Product getProductById(Integer id);

    Product updateProductById(Integer id, Product product);

    Product saveProduct(Product product);

    Map<String, Boolean> deleteProduct(Integer id);
}
