package ru.netology.data;

import lombok.SneakyThrows;
import lombok.Value;
import lombok.var;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public final class DBHelper {
    public DBHelper() {
    }

    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.password");


    @SneakyThrows
    public static String checkStatus() {
        QueryRunner runner = new QueryRunner();
        String query = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var conn = DriverManager.getConnection(url, user, password)) {
            var status=runner.query(conn, query, new ScalarHandler<String>());
            return  status;
        }
    }
}