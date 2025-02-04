package com.mycompany;
import java.io.*;
import java.sql.Connection;

public class BatchProcessor {
    public static void processFile(String path){
        try(Connection connection = DBHelper.connect();
        BufferedReader br = new BufferedReader (new FileReader(path));){
            String line; 
            int lineCount = 0;
            int skippCount = 0;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                lineCount++;
                if(parts.length < 2){
                    System.out.println("Skipping line: " + lineCount + ": " + line);
                    skippCount++;
                    continue;
                }

                try{
                    
                    int id = Integer.parseInt(parts[0]);
                    double adjustment = Double.parseDouble(parts[1]);
                    System.out.println("" + lineCount + ":adjusting " + id  + "by " + adjustment);

                }catch(Exception e){}
            }


            

        }catch(Exception e){
            //Handle SQLException 
            //& FileNotFoundExcpetion from new FileReader()
            e.printStackTrace();
        }
    }
}
