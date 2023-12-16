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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.bean.Account;
import model.bean.Detail_account;
import model.bean.Product;
import model.bean.Product_size;

public class Account_DAO {

    private Connection cnn;
    private Statement statement;

    public Account_DAO() {
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

    public ArrayList<Account> getListAccount() {
        ArrayList<Account> list_Account = new ArrayList<Account>();
        String sql = "Select * from account";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list_Account.add(new Account(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password")));

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list_Account;
    }
    
     public ArrayList<Detail_account> getListDetailAccount() {
        ArrayList<Detail_account> list_Account = new ArrayList<Detail_account>();
        String sql = "Select * from detail_account";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list_Account.add(new Detail_account(resultSet.getInt("id"), resultSet.getInt("role")));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list_Account;
    }

    private String description;
    private BigDecimal price;
    private int quantity;
    private String origin;
    private int id_size;
    private Byte[] image;

    public ArrayList<Product> get_List_Product() {
        ArrayList<Product> list_Product = new ArrayList<Product>();

        String sql = "select * from product";
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

                Product product = new Product(id_cate, id_pro, name_pro, branch, des, price, origin, img);
                list_Product.add(product);

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list_Product;
    }

    public ArrayList<Product> get_List_Product_Paging(int page, int recordsPerPage, String category) {
        ArrayList<Product> list_Product = new ArrayList<Product>();
        // int recordsPerPage = 8;
        int start = (page - 1) * recordsPerPage;
        String query = "select * from product where id_category = '" + category + "' limit ?, ?";
        try (PreparedStatement preparedStatement = cnn.prepareStatement(query)) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, recordsPerPage);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Xử lý kết quả ResultSet
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

                Product product = new Product(id_cate, id_pro, name_pro, branch, des, price, origin, img);
                list_Product.add(product);
            }

            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(Account_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list_Product;
    }

    public int getTotal() {
        String sql_count = "select count(*) from product";
        try {
            ResultSet resultSet = statement.executeQuery(sql_count);
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public class ImageUtils {

        public String encodeImageToBase64(byte[] imageData) {
            return Base64.getEncoder().encodeToString(imageData);
        }
    }

    public void addAccount(Account account, Detail_account detail_account) throws SQLException {
        String sql = "Insert into account(username, password) values('" + account.getUsername() + "', '" + account.getPassword() + "')";
        statement.executeUpdate(sql);

        String sql2 = "Select * from account where username = '" + account.getUsername() + "'";
        ResultSet resultSet = statement.executeQuery(sql2);
        int id = -1;
        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        System.out.println(id + "");
        String name = detail_account.getName();
        String address = detail_account.getAddress();
        String phone = detail_account.getNumber_phone();
        String email = detail_account.getEmail();
        int role = detail_account.getRole();

        String sql3 = "INSERT INTO detail_account (id, name, address, phone_number, email, role) VALUES (" + id + ", '" + name + "', '" + address + "', '" + phone + "', '" + email + "', " + role + ")";
        statement.executeUpdate(sql3);

    }

    public ArrayList<String> getListUser() {
        ArrayList<String> list_username = new ArrayList<String>();
        String sql = "Select username from account";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list_username.add(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list_username;
    }

    public int get_Role_By_Id(int id) {
        String sql = "select role from detail_account where id = " + id;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
    
    public int get_id_by_username(String username){
        int result = 0;
        String sql = "Select id from account where username='" +username +"'";
       
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                result = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
        
                
    }
}
