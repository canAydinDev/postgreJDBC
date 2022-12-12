import java.sql.Connection;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {
        Connection connection=JdbcUtils.connectToDatabase();
        Statement statement=JdbcUtils.createStatement();
    }
}
