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
        <div id="wrapper">
            <jsp:include page="header_main.jsp" />
            <div id="content">
                <div class="slider">
                    <div class="list">
                        <div class="item">
                            <img src="./assets/image/logo.png" alt="" />
                        </div>
                        <div class="item">
                            <img src="./assets/image/back1.png" alt="" />
                        </div>
                        <div class="item">
                            <img src="./assets/image/back2.png" alt="" />
                        </div>
                        <div class="item">
                            <img src="./assets/image/back3.png" alt="" />
                        </div>
                        <div class="item">
                            <img src="./assets/image/back4.png" alt="" />
                        </div>
                    </div>
                    <div class="buttons">
                        <button id="prev"><</button>
                        <button id="next">></button>
                    </div>
                    <ul class="dots">
                        <li class="active"></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                    </ul>
                </div>
                <div class="headline">
                    <h1 style="margin-top: 10px; text-align: center;">GIÀY</h1>
                    <a href="Account_Servlet?index=0">Xem tất cả  ></a>
                </div>
                <ul class="products">
                    <c:forEach items="${list_product}" var="product">
                        <li>
                            <div class="">
                                <div class="product-top">
                                    <a href="ProductView_Servlet?id=${product.id}" class="product-thumb">
                                        <img src="data:image/jpeg;base64,${product.img}" alt="Product Image"/>
                                    </a>
                                    <a href="" class="but-now">THÊM VÀO GIỎ HÀNG</a>
                                </div>
                                <div class="product-info">
                                    <a href="ProductView_Servlet?id=${product.id}" class="product-name" style="color: black; font-size: 20px; padding: 3px 0px;">${product.name}</a>
                                    <div class="product-price" style="color: red; padding: 3px 0px;">${product.price}</div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <div class="headline">
                    <h1 style="margin-top: 10px; text-align: center;">VÍ</h1>
                    <a href="Account_Servlet?index=-1">Xem tất cả  ></a>
                </div>
                <ul class="products">
                    <c:forEach items="${list_product_wallet}" var="product">
                        <li>
                            <div class="">
                                <div class="product-top">
                                    <a href="ProductView_Servlet?id=${product.id}" class="product-thumb">
                                        <img src="data:image/jpeg;base64,${product.img}" alt="Product Image"/>
                                    </a>
                                    <a href="" class="but-now">THÊM VÀO GIỎ HÀNG</a>
                                </div>
                                <div class="product-info">
                                    <a href="ProductView_Servlet?id=${product.id}" class="product-name" style="color: black; font-size: 20px; padding: 3px 0px;">${product.name}</a>
                                    <div class="product-price" style="color: red; padding: 3px 0px;">${product.price}</div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <div class="headline">
                    <h1 style="margin-top: 10px; text-align: center;">TÚI XÁCH</h1>
                    <a href="Account_Servlet?index=-2">Xem tất cả  ></a>
                </div>
                <ul class="products">
                    <c:forEach items="${list_product_bag}" var="product">
                        <li>
                            <div class="">
                                <div class="product-top">
                                    <a href="ProductView_Servlet?id=${product.id}" class="product-thumb">
                                        <img src="data:image/jpeg;base64,${product.img}" alt="Product Image"/>
                                    </a>
                                    <a href="" class="but-now">THÊM VÀO GIỎ HÀNG</a>
                                </div>
                                <div class="product-info">
                                    <a href="ProductView_Servlet?id=${product.id}" class="product-name" style="color: black; font-size: 20px; padding: 3px 0px;">${product.name}</a>
                                    <div class="product-price" style="color: red; padding: 3px 0px;">${product.price}</div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <jsp:include page="footer_main.jsp" />
        </div>
        <script src="./assets/js/app.js"></script>
    </body>
</html>