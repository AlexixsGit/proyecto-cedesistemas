package co.edu.cedesistemas.ecommerce.service;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import co.edu.cedesistemas.ecommerce.common.TestUtil;
import co.edu.cedesistemas.ecommerce.model.Product;
import co.edu.cedesistemas.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock private ProductRepository productRepository;
    @InjectMocks private ProductService productService;

    @Test
    public void testCreateProduct() {
        Product product = TestUtil.buildProduct("product1", "product1");
        when(productRepository.save(product)).thenReturn(product);

        Product created = productService.createProduct(product);

        assertThat(created, notNullValue());
        assertThat(created.getName(), equalTo("product1"));
    }
}
