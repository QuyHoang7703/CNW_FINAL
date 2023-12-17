/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Detail_order;
import model.bean.ItemCart;
import model.bean.Order;

/**
 *
 * @author QUYHOANG
 */
public class Order_DAO {

    private Connection cnn;
    private Statement statement;

    public Order_DAO() {
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

    public int addOrder(Order order) {
        int result = 0;
        String sql = "INSERT INTO `Order` (id_user, total_price, order_date) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, order.getId_user());
            preparedStatement.setBigDecimal(2, order.getTotal_price());
            preparedStatement.setTimestamp(3, order.getOrder_date());

            result = preparedStatement.executeUpdate();
            System.out.println("Da them order result" + result);
        } catch (SQLException ex) {
            System.out.println("Ko them order duoc result" + result);
            ex.printStackTrace();
        }

        return result;
    }

    public int addOrder_OrderDetail(Order order, ArrayList<Detail_order> list) {
        int result = 0;
        String sql = "INSERT INTO `Order` (id_user, total_price, order_date) VALUES (?, ?, ?)";
        int id_order = 0;
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, order.getId_user());
            preparedStatement.setBigDecimal(2, order.getTotal_price());
            preparedStatement.setTimestamp(3, order.getOrder_date());

            result = preparedStatement.executeUpdate();

            String sql2 = "SELECT id_order FROM `Order` ORDER BY id_order DESC LIMIT 1";
            ResultSet resultSet = statement.executeQuery(sql2);

            if (resultSet.next()) {
                id_order = resultSet.getInt("id_order");
                System.out.println("ID_ORDER: " + id_order);
            } else {
                System.out.println("Khong tim thay id_order");
            }

            String sql3 = "Insert into detail_order(id_product, id_order, name_product, quantity, price, name_size) values(?, ?, ?, ?, ?, ?)";
            PreparedStatement insert_detail_order = cnn.prepareStatement(sql3);
            for (Detail_order detail_order : list) {
                insert_detail_order.setString(1, detail_order.getId_product());
                insert_detail_order.setInt(2, id_order);
                insert_detail_order.setString(3, detail_order.getName_product());
                insert_detail_order.setInt(4, detail_order.getQuantity());
                insert_detail_order.setBigDecimal(5, detail_order.getPrice());
                insert_detail_order.setString(6, detail_order.getName_size());
                insert_detail_order.executeUpdate();

            }
            System.out.println("Them order hoac detail_order thanh cong");
        } catch (SQLException ex) {
            System.out.println("Them order hoac detail_order khong thanh cong");
            ex.printStackTrace();
        }

        return result;
    }
}
