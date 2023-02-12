import SQL_Connection.SQL_Con;
import entity.Order;
import entity.Product;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Order order = new Order.OrderBuilder()
//                .setId(1)
//                .setUser(new User())
//                .setProduct(new Product())
//                .setDate(LocalDate.now())
//                .setPrice(1000)
//                .setItemCount(5)
//                .build();


        int parameterIdProducts = 1;
        int parameterNameProducts = 2;
        int parameterPriceProducts = 3;

        int parameterIdUser = 1;
        int parameterNameUser = 2;
        int parameterAddressUser = 3;
        int parameterEmailUser = 4;
        int parameterBirthdayUser = 5;
        int parameterTelephoneUser = 6;


        Scanner in = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


        System.out.println("Choose what you want to do with db: add, update, delete or display in the console");
        String choice = in.nextLine();
        //ADD
        if (choice.equals("add")) {
            System.out.println("What do you want to add: products, users or orders");
            String tableChoice = in.nextLine();
            if (tableChoice.equals("products")) {
                System.out.println("How many products do you want to add");
                int productsQuantity = in.nextInt();
                for (int a = 1; a < productsQuantity + 1; a++) {
                    System.out.println("Insert name of the " + a + " product");
                    in.nextLine();
                    String name = in.nextLine();
                    System.out.println("Insert id of " + a + " product");
                    int id = in.nextInt();
                    System.out.println("Insert price of the " + a + " product");
                    float price = in.nextFloat();
                    in.nextLine();
                    try (PreparedStatement statement = db.getConnection().prepareStatement("INSERT INTO products(id, name, price)VALUES(?, ?, ?)")) {
                        statement.setInt(parameterIdProducts, id);
                        statement.setString(parameterNameProducts, name);
                        statement.setFloat(parameterPriceProducts, price);
                        statement.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        //DELETE
        } else if (choice.equals("delete")) {
            System.out.println("What date do you want to be deleted: table, column or parameters");
            String deleteChoice = in.nextLine();
            if (deleteChoice.equals("table")) {
                System.out.println("What table do you want to be deleted? users, products, orders");
                String tableChoice = in.nextLine();
                if (tableChoice.equals("users")) {
                    try (PreparedStatement statement = db.getConnection().prepareStatement("DROP TABLE IF EXISTS users")) {
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    db.closeConnection();
                } else if (tableChoice.equals("products")) {
                    try (PreparedStatement statement = db.getConnection().prepareStatement("DROP TABLE IF EXISTS products")) {
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    db.closeConnection();
                } else if (tableChoice.equals("orders")) {
                    try (PreparedStatement statement = db.getConnection().prepareStatement("DROP TABLE IF EXISTS orders")) {
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    db.closeConnection();
                }else{
                    System.out.println("There`s no such table");
                }
            }
        }
    }
}


/*
TASK 3
statement.addBatch("INSERT INTO products(id, name, price)VALUES(?, ?, ?)");
statement.addBatch("INSERT INTO products(id, name, price)VALUES(?, ?, ?)");
statement.addBatch("INSERT INTO products(id, name, price)VALUES(?, ?, ?)");
statement.addBatch("INSERT INTO products(id, name, price)VALUES(?, ?, ?)");
statement.addBatch("INSERT INTO products(id, name, price)VALUES(?, ?, ?)");
statement.executeBatch();
statement.clearBatch();
 */

