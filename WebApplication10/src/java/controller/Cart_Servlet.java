/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.Cart;
import model.bean.ItemCart;
import model.bean.Product;
import model.bo.Cart_BO;
import model.bo.Product_BO;
import org.apache.tomcat.jni.SSLContext;

/**
 *
 * @author QUYHOANG
 */
@WebServlet(name = "Cart_Servlet", urlPatterns = {"/Cart_Servlet"})
public class Cart_Servlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        Product_BO bo = new Product_BO();
        Cart_BO cart_BO = new Cart_BO();
        String url = "";
        HttpSession session = request.getSession();
        int id_account = Integer.parseInt(session.getAttribute("id_account").toString());
        if (request.getParameter("showcart") != null) {
            String st = request.getParameter("showcart");
            System.out.println(st);
            getPageCart(request, response);

        }

        if (request.getParameter("btn_cong_tru") != null) {
            System.out.println("co nut cong");
            String id_product = request.getParameter("btn_cong_tru");
            System.out.println("ID_PRODUCT: " + id_product);
            String quantity = request.getParameter("quantity_" + id_product);
            int quantity2 = Integer.parseInt(quantity);
            System.out.println("QUANTITY: " + quantity2);
            String name_size = request.getParameter("name_size");
            Cart cart = new Cart(id_account, id_product, quantity2, name_size);
            cart_BO.updateCart(cart);

            getPageCart(request, response);
        }

        if (request.getParameter("btn_add_item_cart") != null) {
            ArrayList<String> list_id = cart_BO.getListID();

            String id_product = request.getParameter("id_product");
             String name_size = request.getParameter("selected_size");
             boolean check = cart_BO.checkAvailableItemInCart(id_account, id_product, name_size);
            if (check==true) {
                int quantity_db = cart_BO.getQuantity_byID(id_account, id_product);
//                System.out.println("So luong trong DB la " + quantity_db);
                String quantity = request.getParameter("quantity");
                int quantity2 = Integer.parseInt(quantity) + quantity_db; //Số lượng sản phẩm mới
//                System.out.println("So luong moi la " + quantity2);
//                String name_size = request.getParameter("selected_size");
                Cart cart = new Cart(id_account, id_product, quantity2, name_size);
                cart_BO.updateCart(cart);
                getPageCart(request, response);
            } else {
                String quantity = request.getParameter("quantity");
                int quantity2 = Integer.parseInt(quantity);
                Cart cart = new Cart(id_account, id_product, quantity2, name_size);
                cart_BO.addItemCart_BO(cart);
                getPageCart(request, response);
            }

        }

        if (request.getParameter("btn_del_cart") != null) {
            String id_product = request.getParameter("btn_del_cart");
            String size = request.getParameter("size");
            System.out.println("Size: " + size);
            String name_size = request.getParameter("name_size_"+id_product+"_"+size);
              System.out.println("Name_size: " + name_size);
            cart_BO.deleteItemCart(id_account, id_product, name_size);
            getPageCart(request, response);
        }

    }

    public void getPageCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart_BO cart_BO = new Cart_BO();
        String url = "./cart_view.jsp";
        HttpSession session = request.getSession();
        int id_account = Integer.parseInt(session.getAttribute("id_account").toString());
        System.out.println("co " + id_account);
        ArrayList<ItemCart> listItemCart = new ArrayList<ItemCart>();
        listItemCart = cart_BO.getListCart(id_account);
        request.setAttribute("listItemCart", listItemCart);

        request.getRequestDispatcher(url).forward(request, response);
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
