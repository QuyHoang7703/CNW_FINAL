/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.BillView;

/**
 *
 * @author QUYHOANG
 */
public class BillView_DAO {

    private Connection cnn;
    private Statement statement;

    public BillView_DAO() {
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

    public ArrayList<BillView> getListBillView() {
        ArrayList<BillView> list = new ArrayList<BillView>();
        String sql = "SELECT `order`.id_user, `order`.id_order, `order`.total_price, `order`.order_date, detail_account.name \n"
                + "FROM `order` \n"
                + "JOIN account ON `order`.id_user = account.id \n"
                + "JOIN detail_account ON account.id = detail_account.id";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int id_user = resultSet.getInt("id_user");
                int id_order = resultSet.getInt("id_order");
                BigDecimal total_price = resultSet.getBigDecimal("total_price");
                Timestamp order_date = resultSet.getTimestamp("order_date");
                String name = resultSet.getString("name");
                BillView billView = new BillView(id_user, name, id_user, total_price, order_date);
                list.add(billView);
                        
            }
        } catch (SQLException ex) {
            System.out.println("Khong the lay danh sach bill view");
        }
        return list;
        
    }
}
