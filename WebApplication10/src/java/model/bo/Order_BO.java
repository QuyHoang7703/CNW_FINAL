/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bo;

import java.util.ArrayList;
import model.bean.Detail_order;
import model.bean.Order;
import model.dao.Cart_DAO;
import model.dao.Order_DAO;

/**
 *
 * @author QUYHOANG
 */
public class Order_BO {

    private Order_DAO dao;

    public Order_BO() {
        this.dao = new Order_DAO();
    }

    public int addOrder(Order order) {
        return dao.addOrder(order);
    }
    
     public int addOrder_OrderDetail(Order order, ArrayList<Detail_order> list) {
         return dao.addOrder_OrderDetail(order, list);
     }

}
