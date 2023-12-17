/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.Detail_account;
import model.bean.Detail_order;
import model.bean.ItemCart;
import model.bean.Order;
import model.bean.Product_size;
import model.bo.Account_BO;
import model.bo.Cart_BO;
import model.bo.Order_BO;

/**
 *
 * @author QUYHOANG
 */
@WebServlet(name = "Order_Servlet", urlPatterns = {"/Order_Servlet"})
public class Order_Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        Account_BO account_BO = new Account_BO();
        Cart_BO cart_BO = new Cart_BO();
        Order_BO order_BO = new Order_BO();
        HttpSession session = request.getSession();
        int id_account = Integer.parseInt(session.getAttribute("id_account").toString());
        if (request.getParameter("btn_back") != null) {
            session.removeAttribute("listItemCart");
            Cart_Servlet cart_Servlet = new Cart_Servlet();
            cart_Servlet.getPageCart(request, response);
        }
        if (request.getParameter("btn_payment") != null) {
            System.out.println("Qua order_servlet roi");
            String price = request.getParameter("total_price");
            System.out.println("price " + price);
            BigDecimal total_price = new BigDecimal(price);
            System.out.println("Gia tien " + total_price);

            Timestamp order_date = new Timestamp(System.currentTimeMillis());
//            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd-MM-yyyy"); // Định dạng ngày giờ mong muốn
//        String formattedDate = formatter.format(currentTimestamp);
//        System.out.println("Thời gian hiện tại: " + formattedDate);
            Order order = new Order(id_account, total_price, order_date);
         
            ArrayList<ItemCart> list = (ArrayList<ItemCart>) session.getAttribute("listItemCart");
            ArrayList<Detail_order> list_detail_order = new ArrayList<Detail_order>();
            ArrayList<Product_size> list_product_size = new ArrayList<Product_size>();
            for (ItemCart itemCart : list) {
                String id_product = itemCart.getId_product();
                String name_product = itemCart.getName_product();
                int quantity = itemCart.getQuantity();
                BigDecimal p;
                p = itemCart.getPrice();
                String name_size = itemCart.getName_size();
                Detail_order detail_order = new Detail_order(id_product, name_product, quantity, p, name_size);
                list_detail_order.add(detail_order);
                Product_size product_size = new Product_size(cart_BO.getIdSize_by_name(name_size), id_product, quantity);
                list_product_size.add(product_size);
            }
            order_BO.addOrder_OrderDetail(order, list_detail_order);
            session.removeAttribute("listItemCart");
            System.out.println("Xoa listItem trong session roi");
            
            
            cart_BO.removeCart(id_account, list_detail_order);
            cart_BO.updateQuantity(list_product_size);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
