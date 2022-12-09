import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","123987Can");
        Statement st=con.createStatement();

        String sql1="select country_name from countries where region_id=1;";
        boolean result1=st.execute(sql1);
        System.out.println("result1 = " + result1);

        //recordlari gormek icin ExecuteQuery() method'unu kullanmaliyiz.
        ResultSet resultSet1=st.executeQuery(sql1); //resultset donduruyor==>database'den gelen datalarin bulundugu yer

        while (resultSet1.next()){
            System.out.println(resultSet1.getString("country_name"));//==>veya sutun numarasi yani 1 de yazabiliriz
        }

        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        String sql2="select country_id, country_name from countries where region_id >2 ";


        ResultSet rs2=st.executeQuery(sql2);
        while (rs2.next()){
            System.out.println(rs2.getString("country_name")+"---"+rs2.getString("country_id"));
        }

        System.out.println("**********************");
        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.

        String sql3="select * from companies where number_of_employees in(select min(number_of_employees) from companies)";

        ResultSet rs3=st.executeQuery(sql3);
        while (rs3.next()){
            System.out.println(rs3.getString("company")+"--"+rs3.getInt("company_id")+"--"+rs3.getInt(3));
        }


    }
}
