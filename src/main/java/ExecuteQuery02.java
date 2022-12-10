import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","123987Can");
        Statement st=con.createStatement();

       //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve
        // number_of_employees değerlerini çağırın.
        //1.yol==>offset fetch next 1 row only ile
        String str1="select number_of_employees, company from companies order by number_of_employees desc offset (1) row fetch next 1 row only;";
        ResultSet resultSet1=st.executeQuery(str1);
        while (resultSet1.next()){

            System.out.println(resultSet1.getInt(1)+"---"+resultSet1.getString(2));
        }
        //2.yol subquery ile
//        String str2="SELECT company, number_of_employees\n" +
//                "FROM companies\n" +
//                "WHERE number_of_employees = (SELECT MAX(number_of_employees)\n" +
//                "                            FROM companies\n" +
//                "                            WHERE number_of_employees < (SELECT MAX(number_of_employees)\n" +
//                "                                                         FROM companies));";
//        ResultSet resultSet2=st.executeQuery(str2);
//        while (resultSet2.next()){
//            System.out.println(resultSet2.getInt(1)+"---"+resultSet2.getString(2));
//        }
        con.close();
        st.close();
        resultSet1.close();
    }
}
