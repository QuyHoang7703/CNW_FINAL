/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

import java.math.BigDecimal;

public class ItemCart {

    private String id_product;
    private String name_product;
    private byte[] image;
    private BigDecimal price;
    private int quantity;
    private String name_size;

    public String getName_size() {
        return name_size;
    }

    public void setName_size(String name_size) {
        this.name_size = name_size;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

   

    public String getName_product() {
        return name_product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ItemCart(String id_product, String name_product, byte[] image, BigDecimal price, int quantity, String name_size) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.name_size = name_size;
    }

   

}
