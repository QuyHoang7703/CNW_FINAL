package model.bo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Product;
import model.bean.Product_Category;
import model.dao.Product_DAO;

public class Product_BO {

    private Product_DAO dao;

    public Product_BO() {
        this.dao = new Product_DAO();
    }

    public ArrayList<Product_Category> getAllCategory() {
        return dao.getAllCategory();
    }

    public void addProductt_BO(Product product) {
        dao.addProduct(product);
    }

    public int getIdBySize_BO(String name_size) {
        return dao.getIdBySize(name_size);
    }

    public boolean checkIdAvailable(String id) {
        boolean check = false;
        ArrayList<String> listId = dao.getListId(id);
        if (listId.contains(id)) {
            check = true;
        }
        return check;
    }

    public ArrayList<Product> get_List_Product_By_Name(String name) {
        return dao.get_List_Product_By_Name(name);
    }

    public ArrayList<String> getListThuongHIeu(int id_th) {
        return dao.getListThuongHIeu(id_th);
    }
    
    public ArrayList<String> getListXuatXu()
    {
        return dao.getListXuatXu();
    }
    
    public ArrayList<Product> getListProductSearch(String brand, String origin)
    {
        return dao.getListProductSearch(brand, origin);
    }
    public ArrayList<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return  dao.getProductsByPriceRange(minPrice, maxPrice);
    }
    
    public Product getProductById(String id) {
        return dao.getProductById(id);
		
    }
    
}
