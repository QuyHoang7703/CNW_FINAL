package model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import model.bean.ProductView;

import java.util.Base64;
import java.util.List;
import java.util.Locale;
import model.bean.Product;
import model.bean.Product_size;
import model.bean.Size;

public class ProductView_DAO {

    private Connection cnn;
    private Statement statement;

    public ProductView_DAO() {
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

    public ArrayList<ProductView> getProductView() {
        ArrayList<ProductView> list = new ArrayList<ProductView>();
        String sql = "select category_name ,brand, id_product, image, price from product_category , product "
                + "where product_category.id_category = product.id_category ";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String category_name = resultSet.getString("category_name");
                String brand = resultSet.getString("brand");
                String id_product = resultSet.getString("id_product");
                byte[] imageByte = resultSet.getBytes("image");
                ImageUtils imageUtils = new ImageUtils();
                PriceFormatter priceFormatter = new PriceFormatter();
                BigDecimal price = resultSet.getBigDecimal("price");
                String price_show = priceFormatter.formatCurrency(price);
                System.out.println(price_show);
                String image = imageUtils.encodeImageToBase64(imageByte);
                ProductView productView = new ProductView(id_product, category_name, brand, image, price_show);
                list.add(productView);
            }

            return list;

        } catch (SQLException e) {
            System.out.println("loi ne");
            e.printStackTrace();
            return null;
        }

    }

    public Product getProductById(String id) {
        String sql = "SELECT * FROM product WHERE id_product = '" + id + "'";

        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String id_cate = resultSet.getString("id_category");
                String id_pro = resultSet.getString("id_product");
                String name_pro = resultSet.getString("name_product");
                String branch = resultSet.getString("brand");
                String des = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                String origin = resultSet.getString("origin");
                byte[] imageByte = resultSet.getBytes("image");
                ImageUtils imageUtils = new ImageUtils();
                String img = imageUtils.encodeImageToBase64(imageByte);
                return new Product(id_cate, id_pro, name_pro, branch, des, price, origin, img);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối hoặc truy vấn");
            e.printStackTrace();
        }
        return null;
    }

    public Product_size getProductSizeById(String id) {
        String sql = "SELECT * FROM product_size WHERE id_product = '" + id + "'";

        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String id_product = resultSet.getString("id_product");
                int id_size = resultSet.getInt("id_size");
                int quantity = resultSet.getInt("quantity");
                return new Product_size(id_size, id_product, quantity);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối hoặc truy vấn");
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getSizesForProduct(String productId) {
        List<String> sizes = new ArrayList<>();

        String sql = "SELECT s.name_size "
                + "FROM product AS p "
                + "JOIN product_size AS ps ON p.id_product = ps.id_product "
                + "JOIN size AS s ON ps.id_size = s.id_size "
                + "WHERE p.id_product = '" + productId + "'";

        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String size = resultSet.getString("name_size");
                sizes.add(size);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối hoặc truy vấn");
            e.printStackTrace();
        }
        return sizes;
    }

    public class ImageUtils {

        public String encodeImageToBase64(byte[] imageData) {
            return Base64.getEncoder().encodeToString(imageData);
        }
    }

    public class PriceFormatter {

        public String formatCurrency(BigDecimal amount) {
            Locale vietnameseLocale = new Locale("vi", "VN");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(vietnameseLocale);

            return currencyFormatter.format(amount);
        }

    }
}
