package employee.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {
    Connection connection;

    Statement statement;

    private static final String url = "jdbc:mysql://localhost:3306/employeemanagement";
    private static final String username = "root";
    private static final String password = "@nikhil";

    public conn(){
        try{
            connection = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new conn();
    }
}
