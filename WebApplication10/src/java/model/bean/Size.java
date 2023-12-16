/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author Admin
 */
public class Size {
    private int id_size;
    private String name_size;

    public Size(int id_size, String name_size) {
        this.id_size = id_size;
        this.name_size = name_size;
    }

    public int getId_size() {
        return id_size;
    }

    public void setId_size(int id_size) {
        this.id_size = id_size;
    }

    public String getName_size() {
        return name_size;
    }

    public void setName_size(String name_size) {
        this.name_size = name_size;
    }
    
}
