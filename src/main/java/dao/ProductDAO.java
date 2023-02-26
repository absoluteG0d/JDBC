package dao;

import SQL_Connection.SQL_Con;
import entity.Product;
import entity.User;

import java.sql.*;

public class ProductDAO {
    public void create(Product product) {

        try (SQL_Con db = new SQL_Con();
             PreparedStatement statement = db.getConnection().prepareStatement("INSERT INTO products(id, name, price)VALUES(?, ?, ?);")) {
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public Product retrieve(int id){

        Product product;
        try(SQL_Con db = new SQL_Con();
            PreparedStatement statement = db.getConnection().prepareStatement("SELECT * from users WHERE id = ?")) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                product = new Product.ProductBuilder()
                        .setId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setPrice(resultSet.getDouble(3))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }



    public void update(Product product){
        try(SQL_Con db = new SQL_Con();
            PreparedStatement statement = db.getConnection().prepareStatement("UPDATE products SET name = ?, price = ?;")) {
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id){
        try(SQL_Con db = new SQL_Con();
            PreparedStatement statement = db.getConnection().prepareStatement("DELETE FROM products WHERE id  = ?;")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
