/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;


/**
 *
 * @author zeynep
 */

public class RegistrationDb {
      DataSource dataSource;
      public RegistrationDb() throws NamingException{
    try{
    Context ctx =new InitialContext();
    dataSource =(DataSource) ctx.lookup("jdbc/addressbook");
    
    
    }
    catch(NamingException e){
    e.printStackTrace();
    }
      }
    
    public boolean CreateUser(String id,String username,String password,String phonenumber,String email,String gender) throws SQLException{
        boolean flag=true;
         Connection connection = dataSource.getConnection();
     if ( connection == null )
     throw new SQLException( "Unable to connect to DataSource" );
        try {

                String query="INSERT INTO USERS VALUES(?,?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
              Statement st = connection.createStatement();
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, phonenumber);
            pstmt.setString(4, email);
            pstmt.setString(5, gender);
            pstmt.setString(6, id);
            
         st = connection.createStatement();
           pstmt.executeUpdate();
           flag=true;
            
        } catch (SQLException s) {
            JOptionPane.showMessageDialog(null, "Hata: " + s.toString());
           flag=false;
        }
      return flag;
    }
}