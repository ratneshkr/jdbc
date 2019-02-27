package jdbc.mysqltut;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnect {

    public static void main(String[] args) throws SQLException {
        final String connectionURL = "jdbc:mysql://localhost:3306/javastudent";
        String userName="root";
        String password="";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //1. Register
            Connection con = DriverManager.getConnection(connectionURL,userName,password); //2. Create Connection
            Statement stmt = con.createStatement(); //3. Create Statement
            
            ResultSet results = stmt.executeQuery(SQLQueries.selectFromStudentList); //4. Execute Query
            
            while(results.next()){
                System.out.print(results.getInt(1) +"| ");
                System.out.println(results.getString(2));
                System.out.println("----------");
            }
            
            
            con.close();//5. close connection
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
