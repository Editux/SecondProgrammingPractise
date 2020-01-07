package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Driver {
    public static void OpenDB(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\edita\\user.db");
            c.setAutoCommit(false);


            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Items;" );

            while ( rs.next() ) {

                String itemname= rs.getString("Item_name");
                String description = rs.getString("desc");
                double price= rs.getInt("Price");
                int quantity=rs.getInt("Quantity");

                Controller.shopData.add(new Shop(itemname, description,price,quantity ));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}


