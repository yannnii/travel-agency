package com.example.turagency.model.connection;

import com.example.turagency.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectorDB {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

        } catch(Exception ex){
            System.out.println(ex);
        }
    }

    static Connection getConnection() throws DaoException {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/travel-agency?useSSL=false&serverTimezone=UTC&characterEncoding=utf8", "root", "root");
        } catch (SQLException e) {
            System.out.println(e);
            throw new DaoException("Problem with creating Connection. ", e);
        }
    }

}