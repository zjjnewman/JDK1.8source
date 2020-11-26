package jdbc.daoCommon;

import jdbc.utils.JDBCUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Calendar;
import java.util.List;

public class BaseDAO<T> {

    public Class clazz = null;

    {
        // 获取 当前类父类的 泛型（可以是个 泛型 集合）
        Type genericSuperclass = this.getClass().getGenericSuperclass();

        // 强转为 参数化类型型
        ParameterizedType parameterizedType = (ParameterizedType)genericSuperclass;

        // 获取实际 泛型参数 类型
        clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    public T queryForOne(String sql, Object ...args){
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = (ResultSet) preparedStatement.executeQuery();

            if(resultSet.next()){
                T t = (T) clazz.newInstance();

                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String labelName = resultSetMetaData.getColumnLabel(i + 1);
                    Object columnVal = resultSet.getObject(i + 1);

                    Field field = clazz.getField(labelName);
                    field.setAccessible(true);
                    field.set(t, columnVal);
                }
                return t;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement, resultSet);
        }
        return null;
    }

    public List<T> queryForList(String sql, Object ...args){
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
