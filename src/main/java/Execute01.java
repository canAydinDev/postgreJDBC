import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.driver'a kaydolma
        Class.forName("org.postgresql.Driver");

        //2. database'e baglanma
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","123987Can");

        //3.statemant olustur
        Statement st=con.createStatement();

        //4.query calistir

        /*
          execute method'u  ddl(create,drop,alter table...) ve dql(select) icin kullanilabilir.
          1)eger execute method'u ddl icin kullanilirsa false return eder
          2)eger execute() method'u dql icin kullanilirsa resultset alindiginda true aksi halde false donecek

         */

        //1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.

        boolean sql1= st.execute("create table workers(worker_id varchar(20), worker_name varchar(20), worker_salary int)");
        System.out.println(sql1);//false verdi cunku data (table) olusturduk. data cagirmadik

        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.
        st.execute("alter table workers add worker_address varchar(80)");

        //3.Örnek: Drop workers table
        String sql3="drop table workers;";
        st.execute(sql3);

        //5.baglanti ve statemant'i kapat
        con.close();
        st.close();
    }
}
