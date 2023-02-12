package dao;

import SQL_Connection.SQL_Con;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public void create(User user) {

        try (SQL_Con db = new SQL_Con();
             PreparedStatement statement = db.getConnection().prepareStatement("INSERT INTO users(id, name, address, email, birthday, telephone)VALUES(?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getEmail());
            Date birthday = Date.valueOf(user.getBirthday());
            statement.setDate(5, birthday);
            statement.setLong(6, user.getTelephone());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public User retrieve(int id){

        try(SQL_Con db = new SQL_Con();
            Statement statement = db.getConnection().createStatement();) {
            ResultSet resultSet = statement.executeQuery("SELECT * from users WHERE id = 1");
            User user = new User.UserBuilder()
                    .setId(1)
                    .setName()
            while(resultSet.next()){
                resultSet.getInt(1);
                resultSet.getString(2);
                resultSet.getString(3);
                resultSet.getString(4);
                resultSet.getDate(5);
                (resultSet.getLong(6);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }
}

