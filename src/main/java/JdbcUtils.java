import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
    public static void main(String[] args) {

    }
    private static Connection connection;
    private static Statement statement;

    public  static Connection connectToDatabase(){

        //1.driver'a kaydolma
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //2. database'e baglanma
        try {
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","123987Can");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (connection!=null){
            System.out.println("connection success");
        }else{
            System.out.println("connection fail");
        }
        return connection;
    }

    public static Statement createStatement(){
        try {
           statement =connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }
}
