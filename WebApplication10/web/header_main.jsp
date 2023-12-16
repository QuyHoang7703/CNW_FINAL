<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
        <link rel="stylesheet" href="./assets/css/header.css">
        <link rel="stylesheet" href="./assets/css/content.css">
        <link rel="stylesheet" href="./assets/css/footer.css">
        <link rel="stylesheet" href="./assets/css/banner.css">
        <title>Home</title>

    </head>
    <body>
        
            <div id="header">
                <nav class="container">
                    <a href="" id="logo">
                        <img style="border-radius: 50%; border: 2px solid red; width: 40px; height: 40px; object-fit: cover; margin-top: 10px"  src="./assets/image/logo.png" alt="Logo">
                    </a>
                    <form action="Search_Servlet" method="post">
                        <input type="text" name="txt_search" placeholder="Search..." />
                        <button><i class="fa fa-search"></i></button>
                    </form>
                    <ul id="main-menu">
                        <li><a href="">GIÁ CẢ</a>
                            <ul class="sub-menu">
                                <li><a href="ProductView_Servlet?price=1">< 100.000 VNĐ</a></li>
                                <li><a href="ProductView_Servlet?price=2">100.000 - 500.000</a></li>
                                <li><a href="ProductView_Servlet?price=3">> 500.000 VNĐ</a></li>
                            </ul></li>
                        <li><a href="">GIÀY</a>
                            <ul class="sub-menu">
                                <c:forEach items="${list_TH}" var="TH">
                                    <li><a href="ProductView_Servlet?brand=${TH}">${TH}</a></li>
                                    </c:forEach>
                            </ul>
                        </li>
                        <li><a href="">TÚI XÁCH</a>
                            <ul class="sub-menu">
                                <c:forEach items="${list_TH_Tui}" var="TH">
                                    <li><a href="ProductView_Servlet?brand=${TH}">${TH}</a></li>
                                    </c:forEach>
                            </ul></li>
                        <li><a href="">VÍ</a>
                            <ul class="sub-menu">
                                <c:forEach items="${list_TH_Vi}" var="TH">
                                    <li><a href="ProductView_Servlet?brand=${TH}">${TH}</a></li>
                                    </c:forEach>
                            </ul></li>
                        <li><a href="">XUẤT XỨ</a>
                            <ul class="sub-menu">
                                <c:forEach items="${list_XX}" var="TH">
                                    <li><a href="ProductView_Servlet?origin=${TH}">${TH}</a></li>
                                    </c:forEach>
                            </ul></li>
                        <li><a href="">
                                <i style="font-size: 15px;" class="fa-solid fa-user"></i>
                            </a></li>
                        <li><a href="Cart_Servlet?showcart=12">

                                <i style="font-size: 15px;" class="fa-solid fa-cart-plus"></i>
                            </a></li>
                    </ul>
                </nav>
            </div>
       

    </body>
</html>