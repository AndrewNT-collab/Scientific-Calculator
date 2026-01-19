package kalkulator;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        String url = "jdbc:postgresql://localhost:5432/kalkulator_db";
        String user = "postgres";
        String pass = "12345";

        return DriverManager.getConnection(url, user, pass);
    }
}
