package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Product;

import model.bean.ProductView;
import model.bean.Product_Category;
import model.bean.Product_size;
import model.bo.ProductView_BO;
import model.bo.Product_BO;

/**
 * Servlet implementation class Product_Servlet
 */
@WebServlet("/ProductView_Servlet")
public class ProductView_Servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        ProductView_BO bo = new ProductView_BO();
        Product_BO product_BO = new Product_BO();
        if (request.getParameter("function") != null) {
            url = "./viewProduct.jsp";
            ArrayList<ProductView> listProduct = new ArrayList<ProductView>();
            listProduct = bo.getProductView_BO();
            request.setAttribute("listProduct", listProduct);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

        }

        if (request.getParameter("id") != null) {
            url = "./detailProduct.jsp";
            List<String> list_TH_Giay = product_BO.getListThuongHIeu(1);
            List<String> list_TH_Vi = product_BO.getListThuongHIeu(2);
            List<String> list_TH_Tui = product_BO.getListThuongHIeu(3);
            List<String> list_XX = product_BO.getListXuatXu();
            request.setAttribute("list_TH", list_TH_Giay);
            request.setAttribute("list_TH_Vi", list_TH_Vi);
            request.setAttribute("list_TH_Tui", list_TH_Tui);
            request.setAttribute("list_XX", list_XX);
            Product product = bo.getProductById(request.getParameter("id"));
            Product_size product_size = bo.getProductSizeById(request.getParameter("id"));
            List<String> list_sizes = bo.getSizesForProduct(request.getParameter("id"));
            request.setAttribute("product", product);
            request.setAttribute("productsize", product_size);
            request.setAttribute("list_sizes", list_sizes);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

        Product_BO bo_product = new Product_BO();
        if (request.getParameter("btn_add") != null) {
            url = "./form_add_product.jsp";
            ArrayList<Product_Category> listCategory = new ArrayList<Product_Category>();
            listCategory = bo_product.getAllCategory();
            request.setAttribute("listCategory", listCategory);
            request.getRequestDispatcher(url).forward(request, response);

        }

        if (request.getParameter("brand") != null || request.getParameter("origin") != null || request.getParameter("price") != null) {
            url = "./all_product.jsp";
            List<String> list_TH_Giay = product_BO.getListThuongHIeu(1);
            List<String> list_TH_Vi = product_BO.getListThuongHIeu(2);
            List<String> list_TH_Tui = product_BO.getListThuongHIeu(3);
            List<String> list_XX = product_BO.getListXuatXu();
            request.setAttribute("list_TH", list_TH_Giay);
            request.setAttribute("list_TH_Vi", list_TH_Vi);
            request.setAttribute("list_TH_Tui", list_TH_Tui);
            request.setAttribute("list_XX", list_XX);
            String listOrigin = request.getParameter("origin");
            String listCategory = request.getParameter("brand");
            String listPrice = request.getParameter("price");
            ArrayList<Product> products = product_BO.getListProductSearch(listCategory, listOrigin);
            request.setAttribute("list_product", products);
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

}
