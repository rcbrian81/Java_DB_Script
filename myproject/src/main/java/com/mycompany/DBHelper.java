package com.mycompany;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    //private static final String DB_URL = "jdbc:sqlite:resources:database.db";
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/database.db";

    public static Connection connect(){
        try{
            Connection connection = DriverManager.getConnection(DB_URL);
            return connection;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        
    }
}
