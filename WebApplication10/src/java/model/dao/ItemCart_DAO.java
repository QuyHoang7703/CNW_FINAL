/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.bean.Cart;
import model.bean.ItemCart;

/**
 *
 * @author QUYHOANG
 */
public class ItemCart_DAO {

    private Connection cnn;
    private Statement statement;

    public ItemCart_DAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3308/bt_nhom_cnw_3", "root", "");
            statement = cnn.createStatement();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public ArrayList<String> getListId() {
        ArrayList<String> listId = new ArrayList<String>();
        String sql = "Select id_product from product";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                listId.add(resultSet.getString("id_product"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listId;
    }
    
     public ArrayList<String> getListIdCart() {
        ArrayList<String> listId = new ArrayList<String>();
        String sql = "Select id_product from cart";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                listId.add(resultSet.getString("id_product"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listId;
    }
     
    
}
