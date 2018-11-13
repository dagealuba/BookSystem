package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/booksystem?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "199883";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
