package model.bo;

import java.util.ArrayList;
import java.util.List;
import model.bean.Product;

import model.bean.ProductView;
import model.bean.Product_size;
import model.dao.ProductView_DAO;

public class ProductView_BO {

    private ProductView_DAO dao;

    public ProductView_BO() {
        this.dao = new ProductView_DAO();
    }

    public ArrayList<ProductView> getProductView_BO() {
        return dao.getProductView();
    }

    public Product getProductById(String id) {
        return dao.getProductById(id);
    }

    public Product_size getProductSizeById(String id) {
        return dao.getProductSizeById(id);
    }

    public List<String> getSizesForProduct(String productId) {
        return dao.getSizesForProduct(productId);
    }
}
