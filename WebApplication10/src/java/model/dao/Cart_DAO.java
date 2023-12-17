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

import model.bean.Cart;
import model.bean.Detail_order;
import model.bean.ItemCart;
import model.bean.Product;
import model.bean.Product_size;

public class Cart_DAO {

    private Connection cnn;
    private Statement statement;

    public Cart_DAO() {
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

    public int addItemCart(Cart cart) {
        int result = 0;
        String sql = "Insert into Cart values(?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, cart.getId_user());
            preparedStatement.setString(2, cart.getId_product());
            preparedStatement.setInt(3, cart.getQuantity());
            preparedStatement.setString(4, cart.getName_size());

            result = preparedStatement.executeUpdate();
            System.out.println("Them vao cart thanh cong");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Them vao cart that bai");
            e.printStackTrace();
        }

        return result;
    }

    public ArrayList<ItemCart> getListCart(int id_user) {
        ArrayList<ItemCart> list = new ArrayList<ItemCart>();
        Product_DAO dao = new Product_DAO();
        String sql = "Select * from cart where id_user =" + id_user;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Product product = dao.getProductById(resultSet.getString("id_product"));
                String id_product = resultSet.getString("id_product");
                String name_product = product.getName();
                byte[] image = product.getImage();
                BigDecimal price = product.getPrice();
                int quantity = resultSet.getInt("quantity");
                String name_size = resultSet.getString("name_size");

                ItemCart itemCart = new ItemCart(id_product, name_product, image, price, quantity, name_size);
                list.add(itemCart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cart_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public ItemCart getItemCartByID(int id_user, String id_product) {
        System.out.println("id_user: " + id_user);
        System.out.println("id_product: " + id_product);
        Product_DAO dao = new Product_DAO();
        String sql = "Select * from cart where id_user =" + id_user + " and id_product='" + id_product + "'";

        try {

            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Product product = dao.getProductById(resultSet.getString("id_product"));

                String name_product = product.getName();
                byte[] image = product.getImage();
                BigDecimal price = product.getPrice();
                int quantity = resultSet.getInt("quantity");
                String name_size = resultSet.getString("name_size");
                System.out.println("Itemcart khac null");
                return new ItemCart(id_product, name_product, image, price, quantity, name_size);

            } else {
                System.out.println("Itemcart = null");
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return null;

    }

//    public ItemCart getItem(int id_user, String id_product){
//        ItemCart itemCart = null;
//        String sql 
//    }
    public int updateCart(Cart cart) {
        int result = 0;
        try {

            String sql = "UPDATE cart SET quantity = " + cart.getQuantity() + " WHERE id_user = " + cart.getId_user() + " AND id_product = '" + cart.getId_product() + "' AND name_size='" + cart.getName_size() + "'";

            result = statement.executeUpdate(sql);
            System.out.println(" UPDATE CART thanh cong " + result);
            return result;
        } catch (SQLException ex) {
            System.out.println("LOI UPDATE CART");
            Logger.getLogger(Cart_DAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        return result;
    }

    public int deleteItemCart(int id_user, String id_product, String name_size) {
        int result = 0;
        String sql = "Delete from cart where id_user =? and id_product =? and name_size=?";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, id_user);
            preparedStatement.setString(2, id_product);
            preparedStatement.setString(3, name_size);
            result = preparedStatement.executeUpdate();
            System.out.println("Xoa ItemCart thanh cong");
            System.out.println("Result" + result);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Xoa ItemCart that bai");
            e.printStackTrace();
        }

        return result;
    }

    public ArrayList<String> getListID() {
        ArrayList<String> list_id = new ArrayList<String>();
        String sql = "Select id_product from cart";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list_id.add(resultSet.getString("id_product"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        return list_id;
    }

    public int getQuantity_byID(int id_user, String id_product) {
        int result = 0;
        String sql = "Select quantity from cart where id_user =? and id_product =?";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, id_user);
            preparedStatement.setString(2, id_product);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("quantity");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public ArrayList<Cart> getAllCart(int id_user) {
        ArrayList<Cart> list = new ArrayList<Cart>();
        Product_DAO dao = new Product_DAO();
        String sql = "Select * from cart where id_user =" + id_user;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String id_product = resultSet.getString("id_product");
                int quantity = resultSet.getInt("quantity");
                String name_size = resultSet.getString("name_size");
                Cart cart = new Cart(id_user, id_product, quantity, name_size);
                list.add(cart);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public int removeCart(int id_user, ArrayList<Detail_order> list) {
        int result = 0;
        String sql = "Delete from cart where id_user= ? and id_product = ? and name_size = ? ";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            for (Detail_order detail_order : list) {
                preparedStatement.setInt(1, id_user);
                preparedStatement.setString(2, detail_order.getId_product());
                preparedStatement.setString(3, detail_order.getName_size());
                preparedStatement.executeUpdate();
            }
            System.out.println("Xoa cart thanh cong");
        } catch (SQLException ex) {
            System.out.println("Xoa cart that bai");
            ex.printStackTrace();
        }

        return result;
    }

    public int getIdSize_by_name(String name_size) {
        int result = 0;
        String sql = "Select id_size from size where name_size = '" + name_size + "'";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                result = resultSet.getInt("id_size");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cart_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;

    }

    public int updateQuantity(Product_size product_size) {
        int result = 0;
        int quantity = 0;
        String sql2 = "Select quantity from product_size where id_product = ? and id_size = ?";
        try {
            PreparedStatement preparedStatement2 = cnn.prepareStatement(sql2);
            preparedStatement2.setString(1, product_size.getId_product());
            preparedStatement2.setInt(2, product_size.getId_size());
            ResultSet resultSet  = preparedStatement2.executeQuery();
            if(resultSet.next()){
                quantity = resultSet.getInt("quantity");
            }
            System.out.println("So luong ban dau: " +quantity);
            
        } catch (SQLException ex) {
             System.out.println("So luong ban dau ko lay dc la");
            ex.printStackTrace();;
        }
        String sql = "Update product_size set quantity = ? where id_product = ? and id_sỉze=? ";

        try {
             
            int quantity_update = quantity - product_size.getQuantity();
            System.out.println("So luong moi la: " +quantity_update);
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, quantity_update);
            preparedStatement.setString(2, product_size.getId_product());
            System.out.println("id sp cap nhap so luong: " +product_size.getId_product());
            preparedStatement.setInt(3, product_size.getId_size());
            System.out.println("id sp cap nhap so luong: " +product_size.getId_size());

            preparedStatement.executeUpdate();
            System.out.println("Cap nhap so luong thanh cong");
        } catch (SQLException ex) {
            System.out.println("Cap nhap so luong that bai");
            ex.printStackTrace();;
        }

        return result;
    }

}
