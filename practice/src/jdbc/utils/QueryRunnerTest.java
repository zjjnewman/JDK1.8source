package jdbc.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import jdk.internal.util.xml.impl.Input;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Properties;

public class QueryRunnerTest {

    @Test
    public void testInsert(){
        DataSource dataSource = null;
        QueryRunner queryRunner =  new QueryRunner();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        Connection connection = null;
        try {
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            connection = dataSource.getConnection();

            String sql = "update user_table set balance = ? where user = ?";

            queryRunner.update(connection, sql, 1000, "BB");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(connection, null, null);
        }

    }
}
