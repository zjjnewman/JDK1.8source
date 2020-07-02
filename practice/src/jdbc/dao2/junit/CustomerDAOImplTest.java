package jdbc.dao2.junit;

import jdbc.bean.Customer;
import jdbc.dao2.CustomerDAOImpl;
import jdbc.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

class CustomerDAOImplTest {

    private CustomerDAOImpl dao = new CustomerDAOImpl();

    @Test
    void insert() {
        Connection connection = null;
        Customer customer = new Customer(1, "于小飞", "yuxiaofei@gmail.com", new Date(2344525343242L));
        try {
            connection = JDBCUtils.getConnection();
            dao.insert(connection, customer);
            System.out.println("插入成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
    }

    @Test
    void deleteById() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            dao.deleteById(connection, 13);
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
    }

    @Test
    void update() {
        Connection connection = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date date = simpleDateFormat.parse("1876-09-08");
            Customer customer = new Customer(18, "贝多芬", "beiduofen@126.com", new Date(date.getTime()));
            connection = JDBCUtils.getConnection();
            dao.update(connection, customer);
            System.out.println("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
    }

    @Test
    void getCustomerById() {
        Connection connection = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            connection = JDBCUtils.getConnection();
            Customer customer = dao.getCustomerById(connection, 18);
            System.out.println(customer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
    }

    @Test
    void getAll() {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            List<Customer> customerList = dao.getAll(connection);
            customerList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
    }

    @Test
    void getCount() {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            Long l = dao.getCount(connection);
            System.out.println(l);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
    }

    @Test
    void getMaxBirth() {
        Connection connection = null;

        try {
            connection = JDBCUtils.getConnection();
            Date date = dao.getMaxBirth(connection);
            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
    }
}