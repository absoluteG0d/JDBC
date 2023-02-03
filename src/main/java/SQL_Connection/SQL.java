package SQL_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL {
    private static final String URL = "jdbc:postgresql://localhost:5432/PostgreSQl";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";
    Connection connection;

    {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

