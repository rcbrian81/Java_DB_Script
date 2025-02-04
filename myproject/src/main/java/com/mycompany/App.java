package com.mycompany;
import java.sql.Connection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Connection connection = DBHelper.connect();

        if(connection != null)
            System.out.println("Connection to database was SUCCESSFUL!");
    }
}
