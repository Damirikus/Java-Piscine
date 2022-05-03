package edu.school21.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;
import java.sql.SQLException;

public class EmbeddedDataSourceTest {

    EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder()
            .generateUniqueName(true)
            .addScript("schema.sql")
            .addScripts("data.sql")
            .build();


    @Test
    @BeforeEach
    void init(){
        try {
            assertNotNull(dataSource.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dataSource.shutdown();
    }
}
