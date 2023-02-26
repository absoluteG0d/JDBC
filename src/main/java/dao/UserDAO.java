package dao;

import SQL_Connection.SQL_Con;
import entity.User;

import java.sql.*;
import java.time.LocalDate;

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
    public User retrieve(int id) {

        User user;
        try (SQL_Con db = new SQL_Con();
             PreparedStatement statement = db.getConnection().prepareStatement("SELECT * from users WHERE id = ?");
             ResultSet resultSet = statement.executeQuery("SELECT * from users WHERE id = ?")) {

            resultSet.next();
            Date date = resultSet.getDate(5);
            LocalDate birthday = LocalDate.ofEpochDay(date.getTime());
            user = new User.UserBuilder()
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2))
                    .setAddress(resultSet.getString(3))
                    .setEmail(resultSet.getString(4))
                    .setBirthday(birthday)
                    .setTelephone(resultSet.getLong(6))
                    .build();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }



    public void update(User user){
        try(SQL_Con db = new SQL_Con();
            PreparedStatement statement = db.getConnection().prepareStatement("UPDATE users SET name = ?, address = ?, email = ?, birthday = ?, telephone = ?")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getAddress());
            statement.setString(3, user.getEmail());
            Date birthday = Date.valueOf(user.getBirthday());
            statement.setDate(4, birthday);
            statement.setLong(5, user.getTelephone());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id){
        try(SQL_Con db = new SQL_Con();
            PreparedStatement statement = db.getConnection().prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

