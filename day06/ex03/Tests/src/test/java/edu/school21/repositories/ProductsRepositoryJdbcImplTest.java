package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsRepositoryJdbcImplTest {

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(new Product(0L, "carrot", 10),
            new Product(1L, "a", 10),
            new Product(2L, "b", 11),
            new Product(3L, "c", 12),
            new Product(4L, "d", 13));
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(0L, "carrot", 10);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(0L, "lol", 15);
    final Product EXPECTED_SAVE_PRODUCT = new Product(5L, "kek", 1000);

    EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder()
            .generateUniqueName(true)
            .addScript("schema.sql")
            .addScripts("data.sql")
            .build();


    @Test
    void findAllTest(){
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, new ProductsRepositoryJdbcImpl(dataSource).findAll());
    }

    @Test
    void findByIdTest(){
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, new ProductsRepositoryJdbcImpl(dataSource).findById(0L).get());
    }

    @Test
    void findByIdTestNotFound(){
        assertEquals(Optional.empty(),new ProductsRepositoryJdbcImpl(dataSource).findById(15252L));
    }


    @Test
    void updateTest(){
        ProductsRepository repository = new ProductsRepositoryJdbcImpl(dataSource);
        Product product = new Product(0L, "lol", 15);
        repository.update(product);
        assertEquals(EXPECTED_UPDATED_PRODUCT, repository.findById(0L).get());
    }

    @Test
    void saveTest(){
        ProductsRepository repository = new ProductsRepositoryJdbcImpl(dataSource);
        Product product = new Product(null, "kek", 1000);
        repository.save(product);
        assertEquals(EXPECTED_SAVE_PRODUCT, repository.findById(5L).get());
    }

    @Test
    void deleteTest(){
        ProductsRepository repository = new ProductsRepositoryJdbcImpl(dataSource);
        repository.delete(2L);
        assertEquals(Optional.empty(),repository.findById(5L));
    }
}
