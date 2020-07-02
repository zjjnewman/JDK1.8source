package jdbc.preparedstatement.crud;

import jdbc.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 增删改：查
 */
public class PreparedStatementUpdateTest {

    @Test
    public void testCommenUpdate(){
//        String sql = "delete from customers where id = ?";
//        update(sql, 3);

        String sql1 = "update `order` set order_name = ? where order_id = ?";
        update(sql1, "DD", 2);

    }


    // 通用查询，难点是1.查询的表不同，2.返回的是结果集



    // 通用的增删改
    public void update(String sql, Object ...args) {
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
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement);
        }

    }



    // 修改customer表记录
    @Test
    public void testUpdate(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        // 1.获取连接
        try {
            connection = JDBCUtils.getConnection();
            // 2.预编译sql语句，返回PreparedStatement实例
            String sql = "update customers set name = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            // 3.填充占位符
            preparedStatement.setObject(1, "莫扎特");
            preparedStatement.setObject(2, 18);
            // 4.执行
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.资源的关闭
            JDBCUtils.closeResource(connection, preparedStatement);
        }
    }



    // 向customers表中添加一条记录
    @Test
    public void testInsert(){

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            // 1.读取配置信息
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String driverClass = properties.getProperty("driverClass");

            // 2.加载驱动
            Class.forName(driverClass);

            // 3.获取连接
            connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);

            // 4.预编译sql语句，返回PreparedStatement实例
            String sql = "insert into customers(name, email, birth)values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            // 5.填充占位符
            preparedStatement.setString(1, "哪吒");
            preparedStatement.setString(2, "nezha@gmail.com");
            // 格式化日期
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


            preparedStatement.setDate(3, new java.sql.Date(232342341));

            // 6.执行sql
            System.out.println(preparedStatement.execute());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
