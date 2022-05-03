package edu.school21.repositories;

import edu.school21.models.Product;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository{

    private final DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Product> findById(Long id) {

        Product product = null;
        try {
            String sql = "select * from product where identifier=?";

            PreparedStatement pst = dataSource.getConnection().prepareStatement(sql);
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();

            product = new Product();
            product.setId(resultSet.getLong("identifier"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getInt("price"));
        } catch (SQLException t) { return Optional.empty(); }
        return Optional.of(product);
    }

    @Override
    public void save(Product product) {
        try {
            String sql = "INSERT INTO product(name, price) VALUES(?, ?)";
            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {

        try {
            String sql = "UPDATE product SET name=?, price=? WHERE identifier=?";
            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);

            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from product";

            PreparedStatement pst = dataSource.getConnection().prepareStatement(sql);

            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getLong("identifier"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                list.add(product);
            }

        } catch (SQLException throwables) {
            System.out.println("Product not found!");
            return null;
        }
        return list;
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "delete from product where identifier=?";

            PreparedStatement pst = dataSource.getConnection().prepareStatement(sql);
            pst.setLong(1, id);
            pst.executeQuery();

        } catch (SQLException throwables) {
            System.out.println("Product not found!");
        }
    }
}
