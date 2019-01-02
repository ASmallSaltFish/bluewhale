package com.test;

import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 测试mysql事务隔离级别
 */
public class TestJdbc {

    @Test
    public void testSelect() throws SQLException {
        Connection connection = JdbcUtils.getSqlConnection();
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from t_bw_user where user_id=?");
        preparedStatement.setString(1, "1071307903258558466");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String userId = resultSet.getString("user_id");
            String username = resultSet.getString("user_name");
            String userStatus = resultSet.getString("user_status");

            User user = new User();
            user.setUserStatus(userStatus);
            user.setUserId(userId);
            user.setUserName(username);
            System.out.println("--->>>user=" + user);
        }

        connection.commit();
    }

    @Test
    public void testUpdate() throws SQLException {
        Connection connection = JdbcUtils.getSqlConnection();
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        PreparedStatement preparedStatement =
                connection.prepareStatement("update t_bw_user set user_status=?  where user_id=?");
        preparedStatement.setString(1, "3");
        preparedStatement.setString(2, "1071307903258558466");
        preparedStatement.execute();
        connection.commit();
    }


}
