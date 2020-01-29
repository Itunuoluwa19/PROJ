 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MEDIC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ITUNU
 */
public class medreport {
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;



    static final String DB_url = "jdbc:mysql://localhost:3306/treat?zeroDateTimeBehavior=convertToNull";
    static final String username = "root";
    static final String password = "";
    private String nam, elec, temp, rep;
    
    
    public void medreport (String name, String bio_electric, String temperature, String report){
       String available=checkIfAccountExist(name);
       
        boolean ame=true;
        {
            //JOptionPane.showMessageDialog(null, "Account Exist");
        
        nam=name;
        elec=bio_electric;
        temp=temperature;
        rep=report;
       try{
           java.util.Date date = new java.util.Date();
        SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat Time = new SimpleDateFormat("hh:mm:ss a");
        
        String DateCreated = Date.format(date);
        String TimeCreated=Time.format(date);
    
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_url, username, password);
        String sql = "INSERT INTO diagnosis (name, bio_electric , temperature, report,dateEntered,timeEntered) VALUES ( ?, ?, ?, ?,?,?);";
        statement=conn.prepareStatement(sql);
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, nam);
        statement.setString(2, elec);
        statement.setString(3, temp);
        statement.setString(4, rep);
        statement.setString(5, DateCreated);
        statement.setString(6, TimeCreated);
        statement.executeUpdate();
       }
       catch(Exception ex){
           ex.printStackTrace();
       }
          }//end of else
    }
    

    public String checkIfAccountExist(String name ) {
        String report= "";
        boolean avail = false;
      
        try {
            Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_url, username, password);
                String sqlStatement = "select * from diagnosis where name =?  && report= ?;";
                PreparedStatement k = conn.prepareStatement(sqlStatement);
                k.setString(1, name);
                k.setString(2, report);
                   while(avail==true){
    statement.setString(2, report);
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
  return report; 
    }
    
    
    public boolean getDetails(String ID,ArrayList<String> date,ArrayList<String> time,ArrayList<String> bio_electric,ArrayList<String> temps,ArrayList<String> reports , String [] values) {
        boolean d=false;
        {date.clear();
        time.clear();
        bio_electric.clear();
        temps.clear();
        reports.clear();}
        
        String report= "";
        boolean avail = false;
      
        try {
            Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_url, username, password);
                String sqlStatement = "SELECT * FROM treat.diagnosis d JOIN information f on f.id=d.name where f.id=?";
                PreparedStatement k = conn.prepareStatement(sqlStatement);
                k.setString(1, ID);
               
    resultSet=k.executeQuery();
    if(resultSet.next()){
        //ID exitavai
      d=true;
        values[0]=resultSet.getString("last_name")+" "+resultSet.getString("middle_name")+" "+ resultSet.getString("first_name");
        values[1]=resultSet.getString("dob");
        values[2]=resultSet.getString("gender");
        values[3]=resultSet.getString("mobile");
        resultSet.previous();
        while(resultSet.next()){
            date.add(resultSet.getString("dateEntered"));
            time.add(resultSet.getString("timeEntered"));
            bio_electric.add(resultSet.getString("bio_electric"));
            temps.add(resultSet.getString("temperature"));
            reports.add(resultSet.getString("report"));
        }
       
    }
    else{
        JOptionPane.showMessageDialog(null, "No Report found");
    }
    
    }
        
    catch(ClassNotFoundException | SQLException ex){
        }
 return d;
 
    }
    
    
    public boolean checkID(String ID){
      boolean  avail=false;
               try {
            Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_url, username, password);
                String sqlStatement = "SELECT * FROM treat.information where id = ?";
                PreparedStatement k = conn.prepareStatement(sqlStatement);
                k.setString(1, ID);
               
    resultSet=k.executeQuery();
    if(resultSet.next()==true){
        //ID exitavai
     avail=true;
        }
               }
               catch(Exception ex){
                   ex.printStackTrace();
               }
       
        return avail;
    }
    
    
}
