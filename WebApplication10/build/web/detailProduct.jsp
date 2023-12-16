<%-- 
    Document   : detailProduct
    Created on : 11 thg 12, 2023, 14:36:59
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="model.bean.Product_size"%>
<%@page import="model.bean.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./assets/css/header.css">
        <link rel="stylesheet" href="./assets/css/content.css">
        <link rel="stylesheet" href="./assets/css/footer.css">
        <link rel="stylesheet" href="./assets/css/banner.css">
        <link rel="stylesheet" href="./assets/css/detailProduct.css">

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">



    </head>
    <body>
        <jsp:include page="header_main.jsp" />
        <%
            Product product = (Product) request.getAttribute("product");
            Product_size product_size = (Product_size) request.getAttribute("productsize");
            //  List<String> sizesList = (List<String>) request.getAttribute("list_sizes");
%>


        <form action="Cart_Servlet" method="post">
            <div class="product-container">
                <img class="product-image" src="data:image/jpeg;base64,<%= product.getImg()%>" alt="Product Image">
                <div class="product-details">
                    <h2><%= product.getName()%></h2>
                    <input type="hidden" name="id_product" value="<%=product.getId()%>">
                    <div class="info">
                        <p>Mô Tả: <%= product.getDescription()%></p>
                    </div>

                    <div class="info">
                        <p>Thương Hiệu: <%= product.getBrand()%></p>
                    </div>

                    <div class="info">
                        <p style="color: #ff529c; font-size: 28px"><%= product.getPrice()%> VNĐ</p>
                    </div>

                    <div class="info">
                        <p>Xuất xứ: <%= product.getOrigin()%></p>
                    </div>

                    <div class="size">
                        <c:forEach var="size" items="${list_sizes}">
                            <button 
                                style="border-radius: 50px; " 
                                type="button" 
                                class="btn btn-success btn-circle btn-xl"
                                onclick="setSizeValue('${size}')"
                                >
                                ${size}
                            </button>
                            <input type="hidden" name="selected_size" id="selected_size" value="">
                        </c:forEach>
                    </div>
                    <div class="info" style="display: flex; align-items: center; margin-top: 10px">
                        <p style="margin-right: 10px">Số lượng :</p>
                        <button type="button" onclick="decreaseQuantity()" style="border-radius: 60px; width: 30px; height: 30px">-</button>
                        <input id="quantity" type="text" name="quantity" value="1">
                        <button type="button" onclick="increaseQuantity()" style="border-radius: 60px; width: 30px; height: 30px">+</button>
                    </div>

                   
                </div>
                <button class="product-button" name="btn_add_item_cart" value="<%=product.getId()%>"style="margin-top: 25px; width: 200px">Thêm vào giỏ hàng</button>
            </div>

        </div>
    </form>




    <script>
        function setSizeValue(size) {
            document.getElementById('selected_size').value = size;
        }
        function decreaseQuantity() {
            var inputQuantity = document.getElementById('quantity');
            var currentQuantity = parseInt(inputQuantity.value);
            if (currentQuantity > 1) {
                inputQuantity.value = currentQuantity - 1;
            }
        }

        function increaseQuantity() {
            var inputQuantity = document.getElementById('quantity');
            var currentQuantity = parseInt(inputQuantity.value);
            var maxQuantity = <%= product_size.getQuantity()%>;
            if (currentQuantity < maxQuantity) {
                inputQuantity.value = currentQuantity + 1;
            }
        }

    </script>
    <jsp:include page="footer_main.jsp" />

</body>
</html>
