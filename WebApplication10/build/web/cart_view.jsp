<%-- 
    Document   : cart
    Created on : Dec 15, 2023, 8:16:24 PM
    Author     : QUYHOANG
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="model.bean.ItemCart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Cart"%>
<%@page import="model.bean.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>JSP Page</title>
        <style>
            table {
                border-collapse: collapse;
                margin-top: 10px;
            }

            tbody tr {

                border-bottom: 2px solid #000; /* Tạo viền dưới cho mỗi hàng */
            }
            th, td {
                text-align: center;
            }

            .btn_add{

                float: right;
                padding: 8px;
                background-color: red;

            }

            h2{
                /*background-color: #ff656c;*/
            }
        </style>


    </head>
    <body>
        <h1>Giỏ hàng</h1>
        <%
            ArrayList<ItemCart> listItemCart = (ArrayList<ItemCart>) request.getAttribute("listItemCart");
        %>

        <!--<form action="Cart_Servlet" method="POST">-->
            <table width="100%" >
                <thead>
                <th>Tên sản phẩm</th>
                <th>Hình ảnh</th>
                <th>Số lượng</th>
                <th>Đơn giá</th>
                <th>Kích thước</th>
                <th>Giá tiền</th>
                <th>Lựa chọn</th>
                </thead>
                <tbody>
                    <%
                        int index = 0;
                        BigDecimal price;
                        BigDecimal total_price = BigDecimal.ZERO;
                        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(java.util.Locale.forLanguageTag("vi-VN"));
                        for (ItemCart itemCart : listItemCart) {


                    %>
                    <tr>
                        <form action="Cart_Servlet" method="POST">
                        <td><%=itemCart.getName_product()%></td>
                        <td>
                            <%
                                byte[] image_data = itemCart.getImage();
                                String base64Image = java.util.Base64.getEncoder().encodeToString(image_data);
                            %>

                            <img alt="product_image" id="product_image" src="data:image/jpeg;base64, <%= base64Image%>" width="100" height="100">
                        </td>
                        <td>
                            <button type="submit" name="btn_cong_tru" onclick="decreaseQuantity(<%= index%>)" value="<%=itemCart.getId_product()%>">-</button>
                            <input type="text" name="quantity_<%= itemCart.getId_product()%>" value="<%=itemCart.getQuantity()%>" id="quantity_<%= index%>" size="5" onchange="updateTotal(<%= index%>)">
                            <button type="submit" name="btn_cong_tru" value="<%=itemCart.getId_product()%>" onclick="increaseQuantity(<%= index%>)">+</button>   
                        </td>
                        <%
                            price = itemCart.getPrice();

                            String formattedPrice = currencyFormat.format(price);
                        %>


                        <td id="price_<%= index%>"><%= formattedPrice%></td>
                        <%
                            BigDecimal quantity = new BigDecimal(itemCart.getQuantity());
                            BigDecimal tt = price.multiply(quantity);
                            String formattedPrice2 = currencyFormat.format(tt);
                        %>
                        <td><%=itemCart.getName_size()%></td>
                <input type="hidden" name="size" value="<%=itemCart.getName_size()%>">
                <input type="hidden" name="name_size_<%= itemCart.getId_product()%>_<%= itemCart.getName_size()%>" value="<%=itemCart.getName_size()%>">
                <td> <input type="text" name="tt" value="<%=formattedPrice2%>" id="tt_<%= index%>" size="5" ></td>

                <td><button name="btn_del_cart" value="<%= itemCart.getId_product()%>">Xóa</button></td>
                 </form>
                </tr>
                
                <%
                        index++;
                        total_price = total_price.add(tt);

                    }
                    String formattedToTalPrice = currencyFormat.format(total_price);
                %>

                </tbody>
            </table>
            <h2>Tổng tiền: <input type="type" id"" value="<%=formattedToTalPrice%>"></h2>
            <button>Thanh toán</button>
            <script>
                function decreaseQuantity(rowId) {
                    var inputQuantity = document.getElementById('quantity_' + rowId);
                    var currentQuantity = parseInt(inputQuantity.value);
                    if (currentQuantity > 1) {
                        inputQuantity.value = currentQuantity - 1;
                        updateTotal(rowId);
                    }
                }

                function increaseQuantity(rowId) {
                    var inputQuantity = document.getElementById('quantity_' + rowId);
                    var currentQuantity = parseInt(inputQuantity.value);
                    inputQuantity.value = currentQuantity + 1;
                    updateTotal(rowId);
                }

                function updateTotal(rowId) {
                    var inputQuantity = document.getElementById('quantity_' + rowId);
                    var quantity = parseInt(inputQuantity.value);

                    var priceText = document.getElementById('price_' + rowId).innerText;
                    var price = parseInt(priceText.replace(/\D/g, '')); // Loại bỏ mọi ký tự không phải là số
//                    alert(price);
                    var total = quantity * price;

                    var currencyFormat = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'});
                    var formattedTotal = currencyFormat.format(total);
//                    alert(formattedTotal);
                    document.getElementById('tt_' + rowId).value = formattedTotal;

                }




            </script>
<!--        </form>-->




    </body>
</html>
