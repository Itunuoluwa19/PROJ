/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MEDIC;

/**
 *
 * @author ITUNU
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Adeoluwa
 */

public class Records_sql {
            // Steps for the JDBC
    //STEP 1. Import required packages
    //STEP 2: Register JDBC driver
    //STEP 3: Open a connection
    //STEP 4: Execute a query
    //STEP 5: Extract data from result set
    //STEP 6: Clean-up environment
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    static final String DB_url = "jdbc:mysql://localhost:3306/treat?zeroDateTimeBehavior=convertToNull";
    static final String username = "root";
    static final String password = "";
    
public String checkID(){
    String ID="";
    boolean avail=true;
    try{
      
    Class.forName("com.mysql.jdbc.Driver");
    conn=DriverManager.getConnection(DB_url, username, password);
    String sql="SELECT * FROM information  where id=?";
    statement=conn.prepareStatement(sql);
    while(avail==true){
     ID = generateID();
    statement.setString(1, ID);
    resultSet=statement.executeQuery();
    if(resultSet.next()==true){
        //ID exit
        avail=true;
    }
    else{
        //ID doesnot exist
        avail=false;
        break;
    }
    }
    }
    catch(Exception ex){
        ex.printStackTrace();
        }
  return ID;  
}
public String generateID(){
    String ID1="PAT";
    Random randNum=new Random();
    String ID2= String.valueOf(randNum.nextInt(9));
    Random randNum3=new Random();
    String ID3= String.valueOf(randNum3.nextInt(9));
    Random randNum4=new Random();
    String ID4= String.valueOf(randNum4.nextInt(9));
            
  String ID=ID1+ID2+ID3+ID4;
  return ID;
}
public void InsertRecord(String Id, String FirstName, String LastNme, String MName, String Phone, String DOB, String Gender){
   
    try{
        
      
    Class.forName("com.mysql.jdbc.Driver");
    conn=DriverManager.getConnection(DB_url, username, password);
    String sql="insert into information (id, first_name, last_name, middle_name, dob, mobile, gender) value (?,?,?,?,?,?,?)";
    statement=conn.prepareStatement(sql);
    
    statement.setString(1, Id);
    statement.setString(2, FirstName);
    statement.setString(3, LastNme);
    statement.setString(4, MName);
    statement.setString(5, DOB);
    statement.setString(6, Phone);
    statement.setString(7, Gender);
    statement.executeUpdate();
    }
    catch(Exception ex){
        ex.printStackTrace();
    }
 
}
}
