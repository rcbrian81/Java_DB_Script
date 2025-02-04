package com.mycompany;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class BatchProcessor {
    public static void processFile(String path){
        try(Connection connection = DBHelper.connect();
        BufferedReader br = new BufferedReader (new FileReader(path));){
            if(connection != null){
                System.out.println("Successful Connection To Database");
            }
            String updateCommand = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(updateCommand);

            String line; 
            int lineCount = 0;
            int failCount = 0;
            int successCount =0;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                lineCount++;

                if(parts.length < 2){
                    System.out.println("Skipping line: " + lineCount + ": " + line);
                    failCount++;
                    continue;
                }

                try{
                    
                    int id = Integer.parseInt(parts[0].trim());
                    double adjustment = Double.parseDouble(parts[1].trim());
                    System.out.println("" + lineCount + ":adjusting " + id  + "by " + adjustment);

                    pstmt.setDouble(1,adjustment);
                    pstmt.setInt(2,id);

                    int rowsAffected = pstmt.executeUpdate();
                    if(rowsAffected > 0){
                        successCount++;
                    }else{
                        failCount++;
                    }

                }catch(Exception e){
                    failCount++;
                }
            }

            System.out.println("Batch Processed \n Total Lines Ecountered: " + lineCount + "\nTotal Successful: " + successCount + "\nTotal Failed: " + failCount);
            

        }catch(SQLException | IOException e){
            //Handle SQLException 
            //& FileNotFoundExcpetion from new FileReader()
            e.printStackTrace();
        }
    }
}
