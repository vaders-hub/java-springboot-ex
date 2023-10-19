package vader.lab.demo.service;

import vader.lab.demo.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> fetchProducts();
}
