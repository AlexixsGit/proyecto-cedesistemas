package co.edu.cedesistemas.ecommerce.service;

import co.edu.cedesistemas.ecommerce.model.Product;
import co.edu.cedesistemas.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }
}
