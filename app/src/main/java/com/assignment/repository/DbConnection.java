package com.assignment.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    static String host = "mysqlapp";
    static String port = "3306";
    static String db_name = "assignment";
    static String username = "root";
    static String password = "supersecret";

    static Connection con;

    static public void connect() throws ClassNotFoundException, SQLException{

        if(con == null || con.isClosed()){

            Class.forName("com.mysql.jdbc.Driver");

            String connect_string =  "jdbc:mysql://"+host+":"+port+"/"+db_name;

            con = DriverManager.getConnection(connect_string,username,password); 
        }
    }

    static public void disconnect(){
    
        try {
            if(con != null && !con.isClosed()){
                con.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

    }
}
