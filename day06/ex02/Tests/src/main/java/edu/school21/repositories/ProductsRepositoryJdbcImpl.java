package edu.school21.repositories;

import edu.school21.models.Product;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements  ProductsRepository{
    private DataSource dataSource;

    private static final String FIND_ALL = "SELECT * FROM products";

    private static final String FIND_PRODUCT_BY_ID_SQL = FIND_ALL + " WHERE product_id = ?";

    private static final String UPDATE_PRODUCT_SQL = new StringBuilder()
            .append("UPDATE products ")
            .append("SET product_name = ?, product_price = ? ")
            .append("WHERE product_id = ?").toString();

    private static final String SAVE_PRODUCT_SQL = new StringBuilder()
            .append("INSERT INTO products (product_id, product_name, product_price) ")
            .append("VALUES (?, ?, ?)").toString();

    private static final String DELETE_PRODUCT_SQL = "DELETE FROM products WHERE product_id = ?";

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            List<Product> products = new ArrayList<>();

            ResultSet executeResult = statement.executeQuery(FIND_ALL);
            while (executeResult.next()) {
                products.add(new Product(
                        executeResult.getLong("product_id"),
                        executeResult.getString("product_name"),
                        executeResult.getDouble("product_price")
                ));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            Product product = null;
            if (resultSet.next()) {
                product = buildProduct(resultSet);
            }

            return Optional.ofNullable(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());

            if (preparedStatement.executeUpdate() == 0) {
                throw new RuntimeException("Can't update product. Product ID " + product.getId()
                        + " doesn't exist.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_PRODUCT_SQL)) {
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            preparedStatement.setLong(1, id);

            if (preparedStatement.executeUpdate() == 0) {
                throw new RuntimeException("Can't delete product. Product ID " + id + " doesn't exist.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Product buildProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product(
                resultSet.getLong("product_id"),
                resultSet.getString("product_name"),
                resultSet.getDouble("product_price")
        );
        return product;
    }
}
