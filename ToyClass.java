/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Toy;

import java.io.Serializable;

/**
 *
 * @author #USER
 */
//Serializable -process of converting object state into a format that can be transmitted or stored from the server to client
public class ToyClass implements Serializable {
    
    int id;
    double toy_price;
    String toy_name;
    
    public ToyClass(){
        int id = 0;
        double toy_price = 0.0;
        String toy_name = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getToy_price() {
        return toy_price;
    }

    public void setToy_price(double Toy_price) {
        this.toy_price = toy_price;
    }

    public String getToy_name() {
        return toy_name;
    }

    public void setToy_name(String toy_name) {
        this.toy_name = toy_name;
    }
    
    
    
}
