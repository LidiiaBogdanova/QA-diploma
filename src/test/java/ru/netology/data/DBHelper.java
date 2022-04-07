package ru.netology.data;

import lombok.SneakyThrows;
import lombok.Value;
import lombok.var;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.DriverManager;

public final class DBHelper {
    public DBHelper() {
    }

    static final String DB_URL = "jdbc:mysql://localhost:3306/touristicBooking";
    static final String USER = "app";
    static final String PASS = "pass";


    @Value
    public static class Status {
        private String status;
    }

    @SneakyThrows
    public static String checkStatus() {
        var runner = new QueryRunner();
        var status = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            var statusCheck=runner.query(conn, status, new BeanHandler<>(Status.class));
            return statusCheck.getStatus();
        }
    }
    }
