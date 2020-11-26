package jdbc.transaction;

import com.sun.javafx.tk.TKPulseListener;
import jdbc.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {

    //**************************** 考虑事务的 通用增删改测试 ***************************************************
    @Test
    public void testUpdateWithTransaction(){

        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();

            connection.setAutoCommit(false);
            String sql1 = "update user_table set balance = balance - 100 where user = ?";
            update(connection, sql1, "AA");

            // 模拟网络异常
//            System.out.println(12 / 0);

            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            update(connection, sql2, "BB");

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JDBCUtils.closeResource(connection, null);
        }
    }


    //**************************** 不考虑事务的 通用增删改测试 ***************************************************
    @Test
    public void testUpdate(){
        String sql1 = "update user_table set balance = balance - 100 where user = ?";
        update(sql1, "AA");

        // 模拟网络异常
        System.out.println(12 / 0);

        String sql2 = "update user_table set balance = balance + 100 where user = ?";
        update(sql2, "BB");
    }


    //**************************** 考虑事务的 通用增删改 ***************************************************
    // 通用的增删改 version 2.0(不考虑事务)
    public int update(Connection connection, String sql, Object ...args) {
//        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 1.获取连接
//            connection = JDBCUtils.getConnection();
            // 2.预编译sql语句，返回PreparedStatement实例
            preparedStatement = connection.prepareStatement(sql);
            // 3.填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            // 4.执行
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, preparedStatement);
        }
        return 0;
    }

    //**************************** 未考虑事务的 通用增删改 ***************************************************
    // 通用的增删改 version 1.0(不考虑事务)
    public int update(String sql, Object ...args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 1.获取连接
            connection = JDBCUtils.getConnection();
            // 2.预编译sql语句，返回PreparedStatement实例
            preparedStatement = connection.prepareStatement(sql);
            // 3.填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            // 4.执行
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement);
        }
        return 0;
    }
}
