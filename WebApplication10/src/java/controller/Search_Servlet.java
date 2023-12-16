/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Product;
import model.bo.Product_BO;

@WebServlet(name = "Search_Servlet", urlPatterns = {"/Search_Servlet"})
public class Search_Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Product_BO bo = new Product_BO();

        if (request.getParameter("txt_search") != null) {
            String txt_search = request.getParameter("txt_search");
            ArrayList<Product> list_Product = bo.get_List_Product_By_Name(txt_search);
            request.setAttribute("list_product", list_Product);
            request.getRequestDispatcher("all_product.jsp").forward(request, response);
        }
    }

}
