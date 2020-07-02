package jdbc.preparedstatement.crud;

import jdbc.bean.Customer;
import jdbc.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * 针对customers表的查询操作
 */
public class CustomersForQuery {


    @Test
    public void testQueryForCustomer(){
        String sql = "select id, name, email, birth from customers where id = ?";
        Customer customer = queryForCustomer(sql, 13);
        System.out.println(customer);

        String sql1 = "select  name, email, birth from customers where name = ?";
        Customer customer1 = queryForCustomer(sql1, "周杰伦");
        System.out.println(customer1);
    }
    /**
     * 针对customers 表通用的查询一条记录操作
     * 比如查询的字段可变
     */
    public Customer queryForCustomer(String sql, Object ...args){
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;

        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();
            // 获取结果集元数据
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            // 获取结果集列数
            int columnCount = resultSetMetaData.getColumnCount();
            if(resultSet.next()){
                Customer customer = new Customer();
                // 处理每一行数据中的每列
                for (int i = 0; i < columnCount; i++) {
                    // 获取列值
                    Object columnVal = resultSet.getObject(i + 1);

                    // 获取表中字段别名
                    String columnLabel = resultSetMetaData.getColumnLabel(i+1);

                    // 反射获取 名字叫 columnLabel 的JavaBean 属性
                    Field field = Customer.class.getDeclaredField(columnLabel);
                    // 设置此属性可访问
                    field.setAccessible(true);
                    // 用这个属性的set方法，把值传出实例对象
                    field.set(customer, columnVal);
                }
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement, resultSet);
        }
        return null;
    }




    // 对customers表查询一条记录的操作
    @Test
    public void testQuery1(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 1.获取连接
            connection = JDBCUtils.getConnection();

            // 2.预编译sql语句，获取PreparedStatement实例
            String sql = "select id, name, email, birth from customers where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, 1);
            // 3.填充占位符

            // 4.执行，返回结果集
            resultSet = preparedStatement.executeQuery();

            // 处理结果集
            /**
             * 这里区别于迭代器的 hasNext() next() next是返回结果后，指针下移
             * 下面的next是
             */
            if(resultSet.next()){
                // 获取当前这条数据的各个字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement, resultSet);
        }
    }
}
