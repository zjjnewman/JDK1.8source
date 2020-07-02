package jdbc.dao2;

import jdbc.utils.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装了针对于数据表的增删改查的方法
 *
 * 作为 抽象类 只提供通用方法，不实例化，针对于具体的表，提供具体的，继承这个类的 dao
 */

public abstract class BaseDAO<T> {

    private Class<T> clazz = null;

    /**
     * 下面代码 针对 泛型 的反射优化，直接为继承此类的子类，拿到此类的 泛型 类型
     */
    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();

        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;

        //获取父类的泛型参数
        clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    // 通用的增删改
    public void update(Connection connection, String sql, Object ...args) {

        PreparedStatement preparedStatement = null;

        try {
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
            JDBCUtils.closeResource(null, preparedStatement);
        }
    }


    // 对所有表的 多条记录的，通用查询
    public List<T> getForList(Connection connection, String sql, Object ...args){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
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
            JDBCUtils.closeResource(null, preparedStatement, resultSet);
        }
        return null;
    }


    // 对不同表获取一条记录
    public T getInstance(Connection connection, String sql, Object ...args){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            int columnCount = resultSetMetaData.getColumnCount();

            if (resultSet.next()){
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {

                    Object columnVal = resultSet.getObject(i + 1);

                    String columnLabel = resultSetMetaData.getColumnLabel(i + 1);

                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);

                    field.set(t, columnVal);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, preparedStatement, resultSet);
        }
        return null;
    }


    // 特殊的查询 如count(*)
    public <E> E getValue(Connection connection, String sql, Object ...args){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return (E) resultSet.getObject(1);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, preparedStatement, resultSet);
        }
        return null;
    }


}
