package model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import model.bean.Product;
import model.bean.Product_Category;
import model.bean.Product_size;

public class Product_DAO {

    private Connection cnn;
    private Statement statement;

    public Product_DAO() {
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

    public ArrayList<Product_Category> getAllCategory() {
        ArrayList<Product_Category> list = new ArrayList<Product_Category>();
        String sql = "Select * from product_category";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new Product_Category(resultSet.getString("id_category"), resultSet.getString("category_name")));
            }
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public void addProduct(Product product) {

        try {
            String sql = "Insert into product values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setString(1, product.getId_category());
            preparedStatement.setString(2, product.getId());
            preparedStatement.setString(3, product.getName());
            preparedStatement.setString(4, product.getBrand());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setBigDecimal(6, product.getPrice());
            preparedStatement.setString(7, product.getOrigin());
            preparedStatement.setBytes(8, product.getImage());

            preparedStatement.executeUpdate();

            String sql2 = "Insert into product_size values(?, ?, ?)";
            PreparedStatement addProduct_size = cnn.prepareStatement(sql2);
            for (Product_size product_size : product.getProductSizes()) {
                addProduct_size.setString(1, product_size.getId_product());
                addProduct_size.setInt(2, product_size.getId_size());
                addProduct_size.setInt(3, product_size.getQuantity());
                addProduct_size.executeUpdate();
            }

            System.out.println("Da them thanh cong");
        } catch (SQLException e) {
            System.out.println("Khong the add dc prouduct ccccccccccccccccc");
            e.printStackTrace();
        }

    }

    public int getIdBySize(String name_size) {
        int result = 0;
        String sql = "Select id_size from size where name_size = '" + name_size + "'";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                result = resultSet.getInt("id_size");
            } else {
                //Thêm size mới vào nếu chưa tồn tại
                String sql2 = "Insert into size(name_size) values('" + name_size + "')";
                statement.executeUpdate(sql2);
                resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    result = resultSet.getInt("id_size");
                }

            }
        } catch (SQLException e) {
            System.out.println("Loi cho add size!");
            e.printStackTrace();
        }

        return result;
    }

    public ArrayList<String> getListId(String id) {

        ArrayList<String> listId = new ArrayList<String>();
        String sql = "Select id_product from product";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                listId.add(resultSet.getString("id_product"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listId;
    }
    
   

    public ArrayList<String> getListThuongHIeu(int id_th) {

        ArrayList<String> listTH = new ArrayList<String>();
        String sql = "SELECT DISTINCT brand FROM product where id_category = " + id_th;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                listTH.add(resultSet.getString("brand"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listTH;
    }

    public ArrayList<String> getListXuatXu() {

        ArrayList<String> listXX = new ArrayList<String>();
        String sql = "SELECT DISTINCT origin FROM product";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                listXX.add(resultSet.getString("origin"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listXX;
    }

    public ArrayList<Product> getListProductSearch(String brand, String origin) {

        ArrayList<Product> list_Product = new ArrayList<>();
        StringBuilder sqlQuery = new StringBuilder("SELECT * FROM product WHERE");

        boolean hasCondition = false;

        if (brand != null) {
            sqlQuery.append(" brand = '").append(brand).append("'");
            hasCondition = true;
        }

        if (origin != null) {
            if (hasCondition) {
                sqlQuery.append(" AND");
            }
            sqlQuery.append(" origin = '").append(origin).append("'");
        }

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery.toString());
            while (resultSet.next()) {
                String id_cate = resultSet.getString("id_category");
                String id_pro = resultSet.getString("id_product");
                String name_pro = resultSet.getString("name_product");
                String branch = resultSet.getString("brand");
                String des = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                String origin1 = resultSet.getString("origin");
                byte[] imageByte = resultSet.getBytes("image");
                Product_DAO.ImageUtils imageUtils = new Product_DAO.ImageUtils();
                String img = imageUtils.encodeImageToBase64(imageByte);

                Product product = new Product(id_cate, id_pro, name_pro, branch, des, price, origin1, img);
                list_Product.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list_Product;
    }

    public ArrayList<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM product WHERE price BETWEEN " + minPrice + " AND " + maxPrice;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String id_cate = resultSet.getString("id_category");
                String id_pro = resultSet.getString("id_product");
                String name_pro = resultSet.getString("name_product");
                String branch = resultSet.getString("brand");
                String des = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                String origin1 = resultSet.getString("origin");
                byte[] imageByte = resultSet.getBytes("image");
                Product_DAO.ImageUtils imageUtils = new Product_DAO.ImageUtils();
                String img = imageUtils.encodeImageToBase64(imageByte);

                Product product = new Product(id_cate, id_pro, name_pro, branch, des, price, origin1, img);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    private static String convertPriceRange(String priceRange) {
        BigDecimal priceValue = new BigDecimal(priceRange.substring(2).trim());
        return priceRange.charAt(0) == '<' ? " < " + priceValue : " > " + priceValue;
    }

    public ArrayList<Product> get_List_Product_By_Name(String name) {
        ArrayList<Product> list_Product = new ArrayList<Product>();

        String sql = "select * from product where name_product like '%" + name + "%'";
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
                Product_DAO.ImageUtils imageUtils = new Product_DAO.ImageUtils();
                String img = imageUtils.encodeImageToBase64(imageByte);

                Product product = new Product(id_cate, id_pro, name_pro, branch, des, price, origin, img);
                list_Product.add(product);

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list_Product;
    }
    
    public Product getProductById(String id) {
		String sql = "Select * from product where id_product ='" +id +"'";
		String sql2 = "Select * from product_size where id_product = '" +id +"'";
		ArrayList<Product_size> list = new ArrayList<Product_size>();
		try {
			ResultSet resultSet2 = statement.executeQuery(sql2);
			while(resultSet2.next()) {
				list.add(new Product_size(resultSet2.getInt("id_size"), resultSet2.getString("id_product") , resultSet2.getInt("quantity")));
			}
//			System.out.println(list.size()+"neneeeeeeeeeeeeeeeeee");
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				String id_category = resultSet.getString("id_category");
				String id_product = resultSet.getString("id_product");
				String name_product = resultSet.getString("name_product");
				String brand = resultSet.getString("brand");
				String description = resultSet.getString("description");
				BigDecimal price = resultSet.getBigDecimal("price");
				String origin = resultSet.getString("origin");
				byte[] image = resultSet.getBytes("image");
				Product product = new Product(id_category, id_product, name_product, brand, description, price, origin, image, list);
				return product;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Khong tim thay product voi id nay");
			e.printStackTrace();
		
		}
		return null;
	}

    public class ImageUtils {

        public String encodeImageToBase64(byte[] imageData) {
            return Base64.getEncoder().encodeToString(imageData);
        }
    }
}
