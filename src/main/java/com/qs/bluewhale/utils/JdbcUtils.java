package com.qs.bluewhale.utils;

import java.sql.*;

public class JdbcUtils {

    private static String username;
    private static String password;
    private static String url;


    static {
        username = ConfigConstants.getParam("mysql.username");
        password = ConfigConstants.getParam("mysql.password");
        url = ConfigConstants.getParam("mysql.url");

        String className = ConfigConstants.getParam("mysql.driverClassName");
        try {
            //注册数据库驱动
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     */
    public static Connection getSqlConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }


    /**
     * 关闭资源
     */
    public static void close(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {
        if(resultSet != null){
            resultSet.close();
        }

        if(statement != null){
            statement.close();
        }

        if(connection != null){
            connection.close();
        }
    }
}
