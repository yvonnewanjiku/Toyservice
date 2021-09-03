/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Toy_Implementation;

import Toy.ToyClass;
import Toy_Interface.Toy_interf;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author #USER
 */
public class Toy_implem extends UnicastRemoteObject implements Toy_interf {
    //conn is an object for Connection class used to initiate session for database connection
    Connection conn;
    //Contructor for exposing the class
    public Toy_implem() throws RemoteException{
        super();
        getConnection();
    }

    @Override
    public void getConnection() throws RemoteException {
         try {
             //Binding the class with mysql driver
            Class.forName("com.mysql.jdbc.Driver");
            //Assign the conn object to database connection by supplying(dbname,port,permission)
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ToyEngine", "root", "");
        } catch (ClassNotFoundException ex) {
            //catching database error
            Logger.getLogger(Toy_implem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Toy_implem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //does't return anything
    @Override
    public void addToy(ToyClass vegis) throws RemoteException {
        //String msg = "";
        try {
                       
                //insert query that receives begi_name and vegi_price
                String query = "INSERT INTO toy_info(toy_name,toy_price) VALUES(?,?)";
                //Bind the insert query with thhe conn object
                PreparedStatement stmt = conn.prepareStatement(query);
                //getting the supplied variables(toy_name and price)
                stmt.setString(1, toy.getToy_name());            
                stmt.setDouble(2, toy.getToy_price());
                //execute the query
                stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Toy_implem.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return msg;
    }
    
    //Return a List
    @Override
    public List<ToyClass> getToyInfo() throws RemoteException {
        //Creat a list variable to hold the return items later
        List<ToyClass> list = new ArrayList<>();
        try {
            //select query to get vegi_info
            String query = "SELECT * FROM toy_info";
            //Bind the insert query with thhe conn object
            PreparedStatement stmt = conn.prepareStatement(query);
            //execute the query and store the result in res object
            ResultSet res = stmt.executeQuery();
            //Extract data from result set
            while(res.next()){
                //Retrive by column name
                String name = res.getString("toy_name");                
                int price = res.getInt("toy_price");
                
                //Setting the values
                ToyClass toy = new ToyClass();
                //iterate while temporarily storing the values in vegis object
                toy.setToy_name(name);
                toy.setToy_price(price);
                //add each row to the list
                list.add(toy);
            }
        }catch(SQLException ex){
            Logger.getLogger(Toy_implem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //return the full list
        return list;
    }

    @Override
    public int totalPrice(ToyClass toy) throws RemoteException {
        int price = 0;
        try{
            String query = "SELECT toy_price FROM toy_info WHERE toy_name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,toy.Toy_name());
            ResultSet res = stmt.executeQuery();
           while(res.next()){
                price = res.getInt("Toy_price");
                
                toy.setToy_price(price);
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return price;
    }

    @Override
    public void updateToy(ToyClass vegis) throws RemoteException {
        try{
            String query = "UPDATE toy_info SET toy_price = ? WHERE toy_name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, toy.getToy_price());
            stmt.setString(2, toy.getToy_name());
            
            stmt.executeUpdate();            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteToy(ToyClass toy) throws RemoteException {
        try{
            String query = "DELETE FROM toy_info WHERE toy_name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, toy.getToy_name());
            
            stmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

//    @Override
//    public String getUsername(User users) throws RemoteException {
//        try {
//            String query = "SELECT username FROM user username=? AND password=?";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setString(1, users.getUsername());
//    String query1 = "SELET * FROM toy_info WHERE toy_name=?";
//            PreparedStatement stmt1 = conn.prepareStatement(query1);
//            stmt1.setString(1, toy.getToy_name());
//            ResultSet res = stmt1.executeQuery();
//            
//            if(res.next()){
//                msg = "Data already exist";
//            }else{
//        } catch (SQLException ex) {
//            Logger.getLogger(Toy_implem.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    
}
