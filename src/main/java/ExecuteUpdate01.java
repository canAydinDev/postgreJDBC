import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","123987Can");
        Statement st=con.createStatement();

        String sql1="update companies \n" +
                "set number_of_employees=16000\n" +
                "where number_of_employees< (select avg(number_of_employees)from companies);";
        int updateRow=st.executeUpdate(sql1);// kac satir update ettiyse o kadar int verir
        System.out.println(updateRow);
        ResultSet resultSet1=st.executeQuery("select * from companies");
        while (resultSet1.next()){
            System.out.println(resultSet1.getInt(1)+"--"+resultSet1.getString(2)+"---"+resultSet1.getInt(3));
        }
        con.close();
        st.close();
        resultSet1.close();
    }
}
