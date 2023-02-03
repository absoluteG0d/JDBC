import SQL_Connection.SQL_Con;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SQL_Con db = new SQL_Con();

        int parameterId = 1;
        int parameterName = 2;
        int parameterPrice = 3;

        Scanner in = new Scanner(System.in);
        for (int a = 1; a < 5; a++) {
            System.out.println("Insert name of the " + a + " product");

            String name = in.nextLine();
            System.out.println("Insert id of " + a + " product");
            int id = in.nextInt();
            System.out.println("Insert price of the " + a + " product");
            float price = in.nextFloat();
            in.nextLine();

            try (PreparedStatement statement = db.getConnection().prepareStatement("INSERT INTO products(id, name, price)VALUES(?, ?, ?)")) {
                statement.setInt(parameterId, id);
                statement.setString(parameterName, name);
                statement.setFloat(parameterPrice, price);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        db.closeConnection();
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

