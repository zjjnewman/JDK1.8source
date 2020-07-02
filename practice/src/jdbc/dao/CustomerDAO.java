package jdbc.dao;

import com.sun.org.glassfish.external.statistics.CountStatistic;
import jdbc.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 为了更加规范，对customers这个表的查询提供一种规范接口，
 * 关于这个的实体类要遵循这个规范。
 */
public interface CustomerDAO {

    // 将customer对象添加到表中
    void insert(Connection connection, Customer customer);

    // 根据id删除对应记录
    void deleteById(Connection connection, int id);

    void update(Connection connection, Customer customer);

    Customer getCustomerById(Connection connection, int id);

    // 查询所有记录
    List<Customer> getAll(Connection connection);

    // 查询表记录总数
    Long getCount(Connection connection);

    Date getMaxBirth(Connection connection);
}
