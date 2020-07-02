package jdbc.preparedstatement.crud;

import jdbc.bean.Customer;
import jdbc.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;


/**
 * 对所有表的 多条记录的，通用查询
 */
public class PreparedStatementQueryTest {


    @Test
    public void testGetForList(){
        String sql = "select id, name, email, birth from customers where id = ?";
        String sql1 = "select id, name, email, birth from customers";
        List<Customer> customerList = getForList(Customer.class, sql1);
        customerList.forEach(System.out::println);
    }


    // 对所有表的 多条记录的，通用查询
    public <T> List<T> getForList(Class<T> clazz, String sql, Object ...args){
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

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            List<T> list =  new ArrayList<>();

            int columnCount = resultSetMetaData.getColumnCount();

            while (resultSet.next()){
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {

                    Object columnVal = resultSet.getObject(i + 1);

                    String columnLabel = resultSetMetaData.getColumnLabel(i + 1);

                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);

                    field.set(t, columnVal);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement, resultSet);
        }
        return null;
    }
}
