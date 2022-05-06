package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ProductsRepositoryJdbcImplTest {
    private ProductsRepository repository;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<Product>() {{
            add(new Product(1L,"keyboard", 30.87));
            add(new Product(2L,"computer", 2500.0));
            add(new Product(3L,"mobile phone", 755.55));
            add(new Product(4L,"mouse", 7.0));
            add(new Product(5L,"printer", 320.0));
            add(new Product(6L,"scanner", 125.0));
    }};
    final Product EXPECTED_FIND_BY_ID_PRODUCT = EXPECTED_FIND_ALL_PRODUCTS.get(1);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(1L,"SUPER PC", 1000000.0);

    private DataSource dataSource;

    @BeforeEach
    void init() {
        dataSource = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .addScripts("schema.sql", "data.sql")
                .build();

        repository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void findAllTest() {
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, repository.findAll());
    }

    @Test
    void findByIdTest() {
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repository.findById(EXPECTED_FIND_BY_ID_PRODUCT.getId()).get());
    }

    @Test
    void updateTest() {
        Product product = new Product(145L, "SUPER PC", 10000000.0);
        assertThrows(RuntimeException.class, () -> repository.update(product));

        repository.update(EXPECTED_UPDATED_PRODUCT);
        assertEquals(EXPECTED_UPDATED_PRODUCT, repository.findById(EXPECTED_UPDATED_PRODUCT.getId()).get());
    }

    @Test
    void saveTest() {
        repository.save(EXPECTED_FIND_ALL_PRODUCTS.get(1));
        assertEquals(repository.findById(1L).get(), EXPECTED_FIND_ALL_PRODUCTS.get(0));
    }

    @Test
    void deleteTest() {
        assertThrows(RuntimeException.class,
                () ->  repository.delete(EXPECTED_FIND_ALL_PRODUCTS.get(EXPECTED_FIND_ALL_PRODUCTS.size()).getId()));

        repository.delete(EXPECTED_FIND_ALL_PRODUCTS.get(2).getId());
        assertFalse(repository.findById(EXPECTED_FIND_ALL_PRODUCTS.get(2).getId()).isPresent());
    }
}