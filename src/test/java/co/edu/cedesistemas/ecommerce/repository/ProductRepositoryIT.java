package co.edu.cedesistemas.ecommerce.repository;

import co.edu.cedesistemas.ecommerce.common.TestUtil;
import co.edu.cedesistemas.ecommerce.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepositoryIT {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource, notNullValue());
        assertThat(jdbcTemplate, notNullValue());
        assertThat(entityManager, notNullValue());
        assertThat(productRepository, notNullValue());
    }

    @Test
    public void testCreateProduct() {
        Product product = TestUtil.buildProduct("test product", "test product description");
        Product created = productRepository.save(product);
        assertThat(created, notNullValue());
        String id = created.getId();
        Product found = productRepository.findById(id).orElse(null);
        assertThat(found, notNullValue());
        assertThat(found.getName(), equalTo("test product"));
    }
}
