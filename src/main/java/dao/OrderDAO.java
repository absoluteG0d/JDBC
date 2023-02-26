package dao;

import SQL_Connection.SQL_Con;
import entity.Order;
import entity.Product;
import entity.User;

import java.sql.*;
import java.time.LocalDate;

public class OrderDAO {
    public void create(Order order) {

        try (SQL_Con db = new SQL_Con();
             PreparedStatement statement = db.getConnection().prepareStatement("INSERT INTO orders(id, user, product, date, price, itemCount)VALUES(?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, order.getId());
            statement.setInt(3, order.getUser().getId());
            statement.setInt(3, order.getProduct().getId());
            Date date = Date.valueOf(order.getDate());
            statement.setDate(4, date);
            statement.setDouble(5, order.getPrice());
            statement.setInt(6, order.getItemCount());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Order retrieve(int id) {
        Order order;

        try (SQL_Con db = new SQL_Con();
             PreparedStatement statement = db.getConnection().prepareStatement("SELECT * \n" +
                     "FROM orders\n" +
                     "INNER JOIN users ON orders.client_id=users.id\n" +
                     "INNER JOIN products ON orders.item_id=products.id" +
                     "WHERE orders.id = ?;");
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            int userId = resultSet.getInt(7);
            int productId = resultSet.getInt(15);
            Date dateBirthday = resultSet.getDate(12);
            LocalDate birthday = LocalDate.ofEpochDay(dateBirthday.getTime());
            User user = new User.UserBuilder()
                    .setId(userId)
                    .setName(resultSet.getString(8))
                    .setAddress(resultSet.getString(9))
                    .setEmail(resultSet.getString(10))
                    .setTelephone(resultSet.getLong(11))
                    .setBirthday(birthday)
                    .build();
            Product product = new Product.ProductBuilder()
                    .setName(resultSet.getString(13))
                    .setPrice(resultSet.getDouble(14))
                    .setId(productId)
                    .build();

            Date date = resultSet.getDate(4);
            LocalDate date1 = LocalDate.ofEpochDay(date.getTime());
            order = new Order.OrderBuilder()
                    .setId(resultSet.getInt(1))
                    .setUser(user)
                    .setProduct(product)
                    .setDate(date1)
                    .setPrice(resultSet.getDouble(5))
                    .setItemCount(resultSet.getInt(6))
                    .build();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }


    public void update(Order order) {
        try (SQL_Con db = new SQL_Con();
             PreparedStatement statement = db.getConnection().prepareStatement("UPDATE orders SET date = ?, price = ?, item_count = ?")) {
            Date date = Date.valueOf(order.getDate());
            statement.setDate(4, date);
            statement.setDouble(5, order.getPrice());
            statement.setInt(6, order.getItemCount());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try (SQL_Con db = new SQL_Con();
             PreparedStatement statement = db.getConnection().prepareStatement("DELETE  FROM orders WHERE id = ? ")) {
            statement.setInt(1, id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
