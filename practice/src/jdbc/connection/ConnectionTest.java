package jdbc.connection;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * 5 种数据库连接方式，仅限于连接。
 */
public class ConnectionTest {

    //方式1：
    @Test
    public void testConnection1() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();

        String url = "jdbc:mysql://remotehost1:3306/book?useSSl=false";
        //将用户名密码封装在这里
        Properties info = new Properties();
        info.setProperty("user", "jin");
        info.setProperty("password", "123");

        Connection conn = driver.connect(url, info);
        System.out.println(conn);

    }


    /**
     * 方式2：对方式1的迭代
     * 为什么要迭代？ 面向接口编程，是为了更好的可移植性，那么在代码中
     * 尽可能的不出现任何第三方相关API,用反射
     */
    @Test
    public void testConnection2() throws Exception {
        //1. 使用反射获取Driver 实现类对象
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        String url = "jdbc:mysql://remotehost1:3306/book?useSSl=false";
        //将用户名密码封装在这里
        Properties info = new Properties();
        info.setProperty("user", "jin");
        info.setProperty("password", "123");

        Connection conn = driver.connect(url, info);
        System.out.println(conn);
    }

    /**
     * 方式3：用DriverManager 替代 Driver
     */
    @Test
    public void testConnection3() throws Exception{
        // 1. 获取driver的实现类对象
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //提供住连接信息
        String url = "jdbc:mysql://remotehost1:3306/book?useSSl=false";
        String user = "jin";
        String password = "123";
        //注册驱动
        DriverManager.registerDriver(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

    /**
     * 方式4：在3的基础上优化
     */
    @Test
    public void testConnection4() throws Exception {
        // 1. 提供住连接信息
        String url = "jdbc:mysql://remotehost1:3306/book?useSSl=false";
        String user = "jin";
        String password = "123";

        /**
         * 2. 获取driver的实现类对象
         * 这一步之所以可以不报错，是因为在mysql Driver源码中有个静态代码块，执行了
         * static {
         *     try {
         *         DriverManager.registerDriver(new Driver());
         *     } catch (SQLException var1) {
         *         throw new RuntimeException("Can't register driver!");
         *     }
         * }
         * 自动注册了 Driver 实现类
         * 在mysql驱动中有下面的路径，所以即使注释掉也可以成功，但是不要注释掉，保持通用性
         */
        Class.forName("com.mysql.cj.jdbc.Driver");
//        Driver driver = (Driver) clazz.newInstance();

        // 注册驱动
//        DriverManager.registerDriver(driver);

        // 3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

    /**
     * 方式(final版)5：把配置信息写在配置文件
     * 此种方式的好处？
     * 1. 实现了 数据与代码 的 解耦
     * 2. 如需修改配置文件，可以避免重新打包
     */
    @Test
    public void testConnection5() throws Exception{

        // 1.读取配置文件中4个基本信息
//        ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driverClass = properties.getProperty("driverClass");

        //2. 加载驱动
        Class.forName(driverClass);

        //3. 获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

}
