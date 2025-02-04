package com.mycompany;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please CSV File Path:");
        String path = scanner.nextLine().trim();

        BatchProcessor.processFile(path);
        scanner.close();
    }
}
