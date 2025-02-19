package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private String url;
    private final String user = System.getenv("DB_USER");
    private final String password = System.getenv("DB_PASSWORD");
    private final String host = System.getenv("DB_HOST");
    private final String port = System.getenv("DB_PORT");
    private final String dbname = System.getenv("DB_NAME");

    public DataSource() {
        url = "jdbc:postgresql://"+host+":"+port+"/"+dbname;
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

