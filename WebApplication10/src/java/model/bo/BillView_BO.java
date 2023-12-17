/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bo;

import java.util.ArrayList;
import model.bean.BillView;
import model.dao.Account_DAO;
import model.dao.BillView_DAO;

/**
 *
 * @author QUYHOANG
 */
public class BillView_BO {

    private BillView_DAO dao;

    public BillView_BO() {
        this.dao = new BillView_DAO();
    }

    public ArrayList<BillView> getListBillView() {
        return dao.getListBillView();
    }
}
