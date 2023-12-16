package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Account;
import model.bean.Detail_account;
import model.bean.Product;
import model.bo.Account_BO;
import model.bo.Product_BO;
import model.dao.Account_DAO;

/**
 * Servlet implementation class Account_Servlet
 */
@WebServlet("/Account_Servlet")
public class Account_Servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Account_BO account_BO = new Account_BO();
        Product_BO product_BO = new Product_BO();
        String indexPage = request.getParameter("index");

        int recordsPerPage = 4;
        int count = account_BO.getTotal_BO();
        int end = count / recordsPerPage;
        if (count % recordsPerPage != 0) {
            end++;
        }

        if (indexPage != null) {
            int start = Integer.parseInt(indexPage);
            if (start > 0) {
                List<String> list_TH_Giay = product_BO.getListThuongHIeu(1);
                List<String> list_TH_Vi = product_BO.getListThuongHIeu(2);
                List<String> list_TH_Tui = product_BO.getListThuongHIeu(3);
                List<String> list_XX = product_BO.getListXuatXu();
                request.setAttribute("list_TH", list_TH_Giay);
                request.setAttribute("list_TH_Vi", list_TH_Vi);
                request.setAttribute("list_TH_Tui", list_TH_Tui);
                request.setAttribute("list_XX", list_XX);
                ArrayList<Product> list_Product = account_BO.getListProductPaging_BO(start, recordsPerPage, "1");
                ArrayList<Product> list_Product_wallet = account_BO.getListProductPaging_BO(start, recordsPerPage, "2");
                ArrayList<Product> list_Product_bag = account_BO.getListProductPaging_BO(start, recordsPerPage, "3");
                request.setAttribute("list_product", list_Product);
                request.setAttribute("list_product_wallet", list_Product_wallet);
                request.setAttribute("list_product_bag", list_Product_bag);
                request.setAttribute("endP", end);
                request.setAttribute("indexpage", indexPage);
                RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
                rd.forward(request, response);
            } else {
                recordsPerPage = count;
                end = count / recordsPerPage;
                if (count % recordsPerPage != 0) {
                    end++;
                }
                if (start == 0) {
                    ArrayList<Product> list_Product = account_BO.getListProductPaging_BO(1, recordsPerPage, "1");
                    request.setAttribute("list_product", list_Product);
                    List<String> list_TH_Giay = product_BO.getListThuongHIeu(1);
                    List<String> list_TH_Vi = product_BO.getListThuongHIeu(2);
                    List<String> list_TH_Tui = product_BO.getListThuongHIeu(3);
                    List<String> list_XX = product_BO.getListXuatXu();
                    request.setAttribute("list_TH", list_TH_Giay);
                    request.setAttribute("list_TH_Vi", list_TH_Vi);
                    request.setAttribute("list_TH_Tui", list_TH_Tui);
                    request.setAttribute("list_XX", list_XX);

                }
                if (start == -1) {
                    ArrayList<Product> list_Product_wallet = account_BO.getListProductPaging_BO(1, recordsPerPage, "2");
                    request.setAttribute("list_product", list_Product_wallet);
                    List<String> list_TH_Giay = product_BO.getListThuongHIeu(1);
                    List<String> list_TH_Vi = product_BO.getListThuongHIeu(2);
                    List<String> list_TH_Tui = product_BO.getListThuongHIeu(3);
                    List<String> list_XX = product_BO.getListXuatXu();
                    request.setAttribute("list_TH", list_TH_Giay);
                    request.setAttribute("list_TH_Vi", list_TH_Vi);
                    request.setAttribute("list_TH_Tui", list_TH_Tui);
                    request.setAttribute("list_XX", list_XX);
                }
                if (start == -2) {
                    ArrayList<Product> list_Product_bag = account_BO.getListProductPaging_BO(1, recordsPerPage, "3");
                    request.setAttribute("list_product", list_Product_bag);
                    List<String> list_TH_Giay = product_BO.getListThuongHIeu(1);
                    List<String> list_TH_Vi = product_BO.getListThuongHIeu(2);
                    List<String> list_TH_Tui = product_BO.getListThuongHIeu(3);
                    List<String> list_XX = product_BO.getListXuatXu();
                    request.setAttribute("list_TH", list_TH_Giay);
                    request.setAttribute("list_TH_Vi", list_TH_Vi);
                    request.setAttribute("list_TH_Tui", list_TH_Tui);
                    request.setAttribute("list_XX", list_XX);
                }
                request.setAttribute("endP", end);
                request.setAttribute("indexpage", indexPage);
                RequestDispatcher rd = request.getRequestDispatcher("all_product.jsp");
                rd.forward(request, response);
            }
        } else {
            String url = "";
            if (indexPage == null) {
                indexPage = "1";
            }
            int start = Integer.parseInt(indexPage);
            int endP = start + 5;
            if (request.getParameter("btn_login") != null && request.getParameter("btn_login").equals("Login")) {
                String username = request.getParameter("txt_username");
                String password = request.getParameter("txt_password");
                int id_account = account_BO.get_id_by_username(username);
                HttpSession session = request.getSession();
                session.setAttribute("id_account", id_account);

                ArrayList<Account> list_Account = account_BO.getListAccount_BO();
                ArrayList<Detail_account> list_detail_Account = account_BO.getListDetailAccount_BO();

                int check = 0;
                for (Account account : list_Account) {
                    if (account.getUsername().equals(username) && account.getPassword().equals(password) && account_BO.get_Role_By_Id(account.getId()) == 1) {
                        check = 1;
                    } else if (account.getUsername().equals(username) && account.getPassword().equals(password) && account_BO.get_Role_By_Id(account.getId()) == 0) {
                        check = 2;
                    }
                }

                if (check == 1) {
                    url = "./header.jsp";
                    request.getRequestDispatcher(url).forward(request, response);
                } else if (check == 2) {
                    List<String> list_TH_Giay = product_BO.getListThuongHIeu(1);
                    List<String> list_TH_Vi = product_BO.getListThuongHIeu(2);
                    List<String> list_TH_Tui = product_BO.getListThuongHIeu(3);
                    List<String> list_XX = product_BO.getListXuatXu();
                    ArrayList<Product> list_Product = account_BO.getListProductPaging_BO(start, recordsPerPage, "1");
                    ArrayList<Product> list_Product_wallet = account_BO.getListProductPaging_BO(start, recordsPerPage, "2");
                    ArrayList<Product> list_Product_bag = account_BO.getListProductPaging_BO(start, recordsPerPage, "3");
                    request.setAttribute("list_TH", list_TH_Giay);
                    request.setAttribute("list_TH_Vi", list_TH_Vi);
                    request.setAttribute("list_TH_Tui", list_TH_Tui);
                    request.setAttribute("list_XX", list_XX);
                    request.setAttribute("list_product", list_Product);
                    request.setAttribute("list_product_wallet", list_Product_wallet);
                    request.setAttribute("list_product_bag", list_Product_bag);
                    request.setAttribute("endP", end);
                    request.setAttribute("indexpage", indexPage);
                    RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
                    rd.forward(request, response);
                } else {
                    url = "./index.jsp";
                    String message = "Tài khoản hoặc mật khẩu không chính xác";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher(url).forward(request, response);
                }

            }

            if (request.getParameter("btn_register") != null && request.getParameter("btn_register").equals("Register")) {

                url = "./register.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            }

            if (request.getParameter("btn_create_account") != null) {
                ArrayList<String> list_username = account_BO.getListUser_BO();
                String username = (String) request.getParameter("txt_username");
                String password = (String) request.getParameter("txt_password");
                if (!list_username.contains(username)) {
                    Account account = new Account(username, password);
                    String name = request.getParameter("txt_name");
                    String address = request.getParameter("txt_address");
                    String phone_number = request.getParameter("txt_phone");
                    String email = request.getParameter("txt_email");
                    int role = 1;

                    Detail_account detail_account = new Detail_account(name, address, phone_number, email, role);
                    try {
                        account_BO.addAccount_BO(account, detail_account);
                        url = "./main.jsp";
                        System.out.println("ko lỗi");
                        request.getRequestDispatcher(url).forward(request, response);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    String message = "Đã tồn tại tài khoản này rồi";
                    request.setAttribute("message", message);
                    url = "./register.jsp";
                    request.getRequestDispatcher(url).forward(request, response);
                }

            }
        }

    }

}
