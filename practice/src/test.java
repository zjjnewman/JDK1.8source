import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.*;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class test {
    static int cnt = 0;
    public static void main(String[] args) throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        driver.getClass();
        DataSource ds;

    }


}
