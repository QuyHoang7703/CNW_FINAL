/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author QUYHOANG
 */
public class BillView {
    private int id;
    private String name_customer;
    private int id_bill;
    private BigDecimal value_bill;
    private Timestamp date_order;

    public Timestamp getDate_order() {
        return date_order;
    }

    public void setDate_order(Timestamp date_order) {
        this.date_order = date_order;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_customer() {
        return name_customer;
    }

    public void setName_customer(String name_customer) {
        this.name_customer = name_customer;
    }

    public int getId_bill() {
        return id_bill;
    }

    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }

    public BigDecimal getValue_bill() {
        return value_bill;
    }

    public void setValue_bill(BigDecimal value_bill) {
        this.value_bill = value_bill;
    }

    public BillView(int id, String name_customer, int id_bill, BigDecimal value_bill, Timestamp date_order) {
        this.id = id;
        this.name_customer = name_customer;
        this.id_bill = id_bill;
        this.value_bill = value_bill;
        this.date_order = date_order;
    }

   
    
    
    
    
}
