import SQL_Connection.SQL_Con;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SQL_Con db = new SQL_Con();

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


        System.out.println("Choose what you want to do with db: add, update or delete ");
        String choice = in.nextLine();
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
                db.closeConnection();
            } else if (tableChoice.equals("users")) {
                System.out.println("How many users do you want to add");
                int usersQuantity = in.nextInt();
                for (int a = 1; a < usersQuantity + 1; a++) {
                    System.out.println("Insert name of the " + a + " user");
                    String name = in.nextLine();
                    in.nextLine();
                    System.out.println("Insert id of " + a + " user");
                    int id = in.nextInt();
                    System.out.println("Insert address of the " + a + " product");
                    String address = in.nextLine();
                    in.nextLine();
                    System.out.println("Insert email of the " + a + " user");
                    String email = in.nextLine();
                    System.out.println("Insert birthday (dd/MM/yyyy) of the" + a + " user");
                    String birthday = in.nextLine();
                    try {
                        Date date = dateFormat.parse(birthday);
                        System.out.println("Date: " + dateFormat.format(date));
                    } catch (Exception e) {
                        System.out.println("Invalid date format");
                    }
                    System.out.println("Insert telephone of the" + a + " user");
                    int telephone = in.nextInt();
                    in.nextLine();

                    try (PreparedStatement statement2 = db.getConnection().prepareStatement("INSERT INTO products(id, name, price)VALUES(?, ?, ?, ?, ?, ?)")) {
                        statement2.setInt(parameterIdUser, id);
                        statement2.setString(parameterNameUser, name);
                        statement2.setString(parameterAddressUser, address);
                        statement2.setString(parameterEmailUser, email);
                        statement2.setString(parameterBirthdayUser, birthday);
                        statement2.setFloat(parameterTelephoneUser, telephone);
                        statement2.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                db.closeConnection();
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

