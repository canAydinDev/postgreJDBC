import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
           bu bir interface
           bir sql query olusturup onu parametirize edecegiz
           her seferinde ayni query'i yazmak yerine degerleri degistirecegiz.

           reparedStatement interface'i birden cok kez calistirilabilen,
           onceden derlenmis bir sql kodunu temsil eder(yerine kullanilir)
           parametrelendirilmis sql sorgular  ile calisir.
           bu sorguyu 0 veya daha fazla parametre ile kullanabiliriz
         */



        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","123987Can");
        Statement st=con.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //1.Adim: preparedStatement query'sini olustur.
        String sql1="Update companies set number_of_employees=? where company= ?";

        //2.adim preparedStatement objesini olustur.
        PreparedStatement pst1=con.prepareStatement(sql1);

        //3.adim: ? yerine deger atayacagiz.
        //deger atarken set (setInt(), setString()..) kullaniyoruz
        pst1.setInt(1,9999);
        pst1.setString(2,"IBM");

        //4.Adim: Query'i  calistiralim
        int guncellenenSatuSayisi=pst1.executeUpdate();
        System.out.println("guncellenenSatuSayisi = " + guncellenenSatuSayisi);

        String sql2="select * from companies order by company_id";
        ResultSet rs1=st.executeQuery(sql2);
        while (rs1.next()){
            System.out.println(rs1.getInt(1)+" - "+rs1.getString(2)+" - "+rs1.getInt(3));
        }

        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.

        pst1.setInt(1,5555);
        pst1.setString(2,"GOOGLE");

        pst1.executeUpdate();
        while (rs1.next()){
            System.out.println(rs1.getInt(1)+" - "+rs1.getString(2)+" - "+rs1.getInt(3));
        }
        con.close();
        rs1.close();
        st.close();
        pst1.close();

    }
}
