package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConnectSQLServer {

    public static void main(String[] args) throws ClassNotFoundException {

        // Create a variable for the connection string.
        final String connectionUrl = "jdbc:sqlserver://10.10.10.236:1433;databaseName= javastudent;user=sa;password=Neosphere@123";
        

        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection(connectionUrl);
            Statement stmt = con.createStatement();

            String sqlQuery = "insert into customer (customer_id, customer_name, customer_phone, customer_email) values (?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sqlQuery);

            Scanner sc = new Scanner(System.in);
            Input i = new Input();

            System.out.print("Enter CUstomer Id: ");
            i.setCustomerId(sc.nextInt());

            System.out.print("Enter CUstomer Name: ");
            i.setCustomerName(sc.next());

            System.out.print("Enter CUstomer Phone: ");
            i.setCustomerPhone(sc.next());

            System.out.print("Enter CUstomer Email: ");
            i.setCustomerEmail(sc.next());

            ps.setInt(1, i.getCustomerId());
            ps.setString(2, i.getCustomerName());
            ps.setString(3, i.getCustomerPhone());
            ps.setString(4, i.getCustomerEmail());

            ps.executeUpdate();

            System.out.println("---------------------------------------------");

            String SQL = "SELECT * FROM dbo.customer";
            ResultSet rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                System.out.print(rs.getString(1) + "|");
                System.out.print(rs.getString(2) + "|");
                System.out.print(rs.getString(3) + "|");
                System.out.println(rs.getString(4));
            }

            ps.close();
            rs.close();
            stmt.close();
            con.close();

        } // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
