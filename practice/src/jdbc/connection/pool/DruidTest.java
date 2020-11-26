package jdbc.connection.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import jdbc.bean.Customer;
import jdbc.dao.CustomerDAO;
import jdbc.dao.CustomerDAOImpl;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidTest {

    CustomerDAO customerDAO = new CustomerDAOImpl();
    @Test
    public void getConnection(){
        Connection connection = null;
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            connection = dataSource.getConnection();
            System.out.println(connection);
            System.out.println(customerDAO.getCustomerById(connection, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
