package jdbc.statement.crud;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import jdbc.bean.User;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Properties;

public class StatementTest {

    // 使用Statement的弊端：需要拼写sql，存在sql注入的问题
    @Test
    public void testLogin(){
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("请输入用户名：");
//        String user = scanner.next();
//        System.out.println("请输入密码：");
//        String  password = scanner.next();
        String user = "A";
        String  password = "123456";

//        String sql = "select user, password from user_table where user = " + "'" + user + "'" + " and password = " + password;
        // sql 注入
        String sql = "select user, password from user_table where user = " + "'" + user + "'" + " or 1=1 "+ " and password = " + password;
        System.out.println(sql);
        User retUser = get(sql, User.class);
        if(retUser != null){
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }


    }

    @Test
    public <T> T get(String sql, Class<T> clazz){
        T t = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // 加载配置文件
            // 获取此类的类加载器，并用它以流的形式加载进来jdbc.properties
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String driverClass = properties.getProperty("driverClass");

            //加载类
            Class.forName(driverClass);

            // 获取连接
            connection = DriverManager.getConnection(url, user, password);

            // 创建statement
            statement = connection.createStatement();
            // 用statement 执行查询，结果集
            resultSet = statement.executeQuery(sql);

            // 获取结果集的元数据，例如列数， 行数之类的。
            ResultSetMetaData resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();

            // 获取结果集列数
            int columnCount = resultSetMetaData.getColumnCount();

            // 用反射的方式获取结果中的列属性
            if(resultSet.next()){
                t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {

                    // 1.获取列别名，查询时把别名设置成和bean属性一样
                    String columnLable = resultSetMetaData.getColumnLabel(i + 1);

                    // 2.获取对应数据表中的数据
                    Object columnVal = resultSet.getObject(columnLable);

                    // 3.将数据表中得到的数据封装进对象
                    // 根据别名获取属性
                    Field field = clazz.getDeclaredField(columnLable);
                    // 使这个属性可访问
                    field.setAccessible(true);
                    // 把属性值set进这个对象的对应属性
                    field.set(t, columnVal);
                }
                return t;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(statement != null){
                try {
                    statement.close();
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
        return null;
    }

}
