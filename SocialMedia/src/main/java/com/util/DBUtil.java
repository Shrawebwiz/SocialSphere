package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static Connection connection;
    static {
        try {
            Class.forName("org.h2.Driver");
            System.out.println("H2 Driver Loaded.");

            connection = DriverManager.getConnection(
                    "jdbc:h2:file:~/test",
                    "sa", ""
            );


            System.out.println("H2 Connection Established.");

        } catch (ClassNotFoundException e) {
            System.out.println("H2 Driver NOT Found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("H2 Connection or Table Creation Failed.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
