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
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Admins;" );

            while ( rs.next() ) {

                String username= rs.getString("User_name");
                String password = rs.getString("User_Password");

                  MainController.adminData.add(new Admins(username,password))  ;
                //Controller.shopData.add(new Shop(itemname, description,price,quantity ));
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
public static  void getUsers(){
    Connection c = null;
    Statement stmt = null;
    try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\edita\\user.db");
        c.setAutoCommit(false);


        stmt = c.createStatement();
        ResultSet rst = stmt.executeQuery( "SELECT * FROM Users;" );

        while ( rst.next() ) {

            String user_username= rst.getString("User_name");
            String user_password = rst.getString("User_password");
            int item_bought= rst.getInt("quantity");
            String last_item=rst.getString("last_item");
            int spent=rst.getInt("price");
            int discount=rst.getInt("discount");

            UserController.userData.add(new Users(user_username,user_password,item_bought,last_item,spent,discount));
        }
        rst.close();
        stmt.close();
        c.close();
    } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
    }
    System.out.println("Operation done successfully");

}
    public static  void getOrders(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\edita\\user.db");
            c.setAutoCommit(false);


            stmt = c.createStatement();
            ResultSet rst = stmt.executeQuery( "SELECT * FROM Orders;" );

            while ( rst.next() ) {

                String username= rst.getString("username");
                String item = rst.getString("item_name");
                String country= rst.getString("country");
                String city=rst.getString("city");
                String address=rst.getString("address");
                int spent=rst.getInt("item_cost");


               OrderController.orderData.add(new Orders(username,item,country,city,address,spent));
            }
            rst.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

    }

    public static void getItems(){
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
                String description = rs.getString("description");
                double price= rs.getInt("Price");
                int quantity=rs.getInt("Quantity");

                ItemManager.itemData.add(new Items(itemname,description, price,quantity));

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