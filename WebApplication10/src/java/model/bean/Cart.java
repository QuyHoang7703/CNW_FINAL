/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

public class Cart {

    private int id_user;
    private String id_product;
    private int quantity;
    private String name_size;

    public String getName_size() {
        return name_size;
    }

    public void setName_size(String name_size) {
        this.name_size = name_size;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    public Cart(int id_user, String id_product, int quantity) {
//        super();
//        this.id_user = id_user;
//        this.id_product = id_product;
//        this.quantity = quantity;
//    }

    public Cart(int id_user, String id_product, int quantity, String name_size) {
        this.id_user = id_user;
        this.id_product = id_product;
        this.quantity = quantity;
        this.name_size = name_size;
    }
}
