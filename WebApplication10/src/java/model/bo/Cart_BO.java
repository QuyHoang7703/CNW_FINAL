/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bo;

import java.util.ArrayList;
import model.bean.Cart;
import model.bean.Detail_order;
import model.bean.ItemCart;
import model.bean.Product_size;
import model.dao.Cart_DAO;
import model.dao.Product_DAO;

public class Cart_BO {

    private Cart_DAO dao;

    public Cart_BO() {
        this.dao = new Cart_DAO();
    }

    public int addItemCart_BO(Cart cart) {
        return dao.addItemCart(cart);
    }

    public ArrayList<ItemCart> getListCart(int id_user) {
        return dao.getListCart(id_user);
    }

    public int updateCart(Cart cart) {
        return dao.updateCart(cart);
    }

    public int deleteItemCart(int id_user, String id_product, String name_size) {
        return dao.deleteItemCart(id_user, id_product, name_size);
    }

    public ArrayList<String> getListID() {
        return dao.getListID();
    }

    public int getQuantity_byID(int id_user, String id_product) {
        return dao.getQuantity_byID(id_user, id_product);
    }

    public boolean checkAvailableItemInCart(int id_user, String id_product, String name_size) {
        boolean check = false;
        ArrayList<Cart> list = dao.getAllCart(id_user);
        for (Cart cart : list) {
            if (cart.getId_product().equals(id_product) && cart.getName_size().equals(name_size)) {
                check = true;
                break;

            }
        }
        return check;
    }

    public ArrayList<ItemCart> getListItemCart(int id_user, ArrayList<String> list_id) {
        ArrayList<ItemCart> list = new ArrayList<ItemCart>();
        System.out.println("list_id: " + list_id.size());
        for (String id_product : list_id) {
            System.out.println("id_product_bo " + id_product);
            ItemCart itemcart = dao.getItemCartByID(id_user, id_product);
            list.add(itemcart);
        }
        return list;
    }

    public int removeCart(int id_user, ArrayList<Detail_order> list) {
        return dao.removeCart(id_user, list);
    }

    public int getIdSize_by_name(String name_size) {
        return dao.getIdSize_by_name(name_size);
    }

    public void updateQuantity(ArrayList<Product_size> list_product_size) {
        int result = 0;
        for(Product_size product_size : list_product_size){
            result = dao.updateQuantity(product_size);
        }
    }

}
