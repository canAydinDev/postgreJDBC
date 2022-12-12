import java.sql.*;
import java.text.DecimalFormat;

public class CallableStatement01 {
    /*
    Java'da methodlar return type sahibi olsa daa olmasa da method olarak adlandirilir.
    Sqld ise data return ediyorsa "function" denir. return yapmiyorsa "procedure" olarak adlandirilir
     */

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "123987Can");
        Statement st = con.createStatement();

        //collableStatement ile function cagirmayi parametrelendirecegiz
        String sql1="create or replace function  toplamaF(x Numeric, y numeric)\n" +
                "returns numeric\n" +
                "language plpgsql\n" +
                "as\n" +
                "$$\n" +
                "begin\n" +
                "return x+y;\n" +
                "end\n" +
                "$$";

        //2.adim function i calistiralim
        st.execute(sql1);

        //3.adim:  function'i cagiralim
        CallableStatement cst1=con.prepareCall("{? = call toplamaF(?, ?)}");//ilk parametre return type

        //4.adim: return icin registerOurParameter() methodunu, parametreler icin ise set().. methodlarini uygula
        cst1.registerOutParameter(1,Types.NUMERIC);
        cst1.setInt(2,6);
        cst1.setInt(3,4);

        //5.adim:execute() methodu ile CollableStatement'i calistir.
        cst1.execute();
        //6.adim: Sonucu cagirmak icin return data type tipine gore
        System.out.println(cst1.getBigDecimal(1));

        ////2. Örnek: Koninin hacmini hesaplayan bir function yazın.

        String sql2="create or replace function  koniHacmiAl(r Numeric, h numeric)\n" +
                "returns numeric\n" +
                "language plpgsql\n" +
                "as\n" +
                "$$\n" +
                "begin\n" +
                "return 3.14*r*r*h/3;\n" +
                "end\n" +
                "$$";
        st.execute(sql2);
        CallableStatement cst2=con.prepareCall("{? = call koniHacmiAl(?,?) }");
        cst2.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,1);
        cst2.setInt(3,6);
        cst2.execute();
        System.out.printf("%.2f",cst2.getBigDecimal(1));

    }
}
