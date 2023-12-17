<%-- 
    Document   : order
    Created on : Dec 16, 2023, 9:48:40 PM
    Author     : QUYHOANG
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.ItemCart"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="model.bean.Detail_account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            form {
                width: 700px;
                margin: 0 auto;
            }
            .detail_order {
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
        <%
            Detail_account detail_account = (Detail_account) request.getAttribute("detail_account");
            ArrayList<ItemCart> listItemCart = (ArrayList<ItemCart>) session.getAttribute("listItemCart");
            if(listItemCart.size()>0){
                out.println("co phan tu: "+listItemCart.size());
            }
        %>
        <form action="Order_Servlet" method="POST">
            <table width="100%">
                <caption>
                    <h2>Thông tin hóa đơn</h2>
                </caption>
                <tr>
                    <td>Mã khách hàng</td>
                    <td><input type="text" name="" id="" value="<%=detail_account.getId()%>"/></td>
                </tr>
                <tr>
                    <td>Tên khách hàng</td>
                    <td><input type="text" name="" id="" value="<%=detail_account.getName()%>"/></td>
                </tr>
                <tr>
                    <td>Địa chỉ</td>
                    <td><input type="text" name="" id="" value="<%=detail_account.getAddress()%>"/></td>
                </tr>
                <tr>
                    <td>Số điện thoại</td>
                    <td><input type="text" name="" id="" value="<%=detail_account.getNumber_phone()%>"/></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="text" name="" id="" value="<%=detail_account.getEmail()%>"/></td>
                </tr>
            </table>
            <h2>Chi tiết hóa đơn</h2>
               <table width="100%" class="detail_order">
                <thead>
                <th>Tên sản phẩm</th>
                <th>Hình ảnh</th>
                <th>Số lượng</th>
                <th>Đơn giá</th>
                <th>Kích thước</th>
                <th>Giá tiền</th>

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
                     
                        <td><%=itemCart.getName_product()%></td>
                        <td>
                            <%
                                byte[] image_data = itemCart.getImage();
                                String base64Image = java.util.Base64.getEncoder().encodeToString(image_data);
                            %>

                            <img alt="product_image" id="product_image" src="data:image/jpeg;base64, <%= base64Image%>" width="100" height="100">
                        </td>
                        <td>
                           
                            <input type="text" name="quantity_<%= itemCart.getId_product()%>" value="<%=itemCart.getQuantity()%>" id="quantity_<%= index%>" size="5" readonly>
                           
                        </td>
                        <%
                            price = itemCart.getPrice();

                            String formattedPrice = currencyFormat.format(price);
                        %>



                        <td> <input type="type" id"" value="<%=formattedPrice%>" readonly size="5"></td>
                            <%
                                BigDecimal quantity = new BigDecimal(itemCart.getQuantity());
                                BigDecimal tt = price.multiply(quantity);
                                String formattedPrice2 = currencyFormat.format(tt);
                            %>
                        <td><%=itemCart.getName_size()%></td>
                <input type="hidden" name="size_<%= itemCart.getId_product()%>" value="<%=itemCart.getName_size()%>">

                <td> <input type="text" name="tt" value="<%=formattedPrice2%>" id="tt_<%= index%>" size="5" ></td>


            
                </tr>

                <%
                        index++;
                        total_price = total_price.add(tt);

                    }
                    String formattedToTalPrice = currencyFormat.format(total_price);
                %>

                </tbody>
            </table>
            <h2>Tổng tiền: <input type="type" id""  value="<%=formattedToTalPrice%>" readonly></h2>
            <input type="hidden" id""  name="total_price" value="<%=total_price%>" readonly>

            <button name="btn_payment" value="pay" id="btnThanhToan">Thanh toán</button>
             <button name="btn_back" value="back" id="btnBack">Quay lại</button>

        </form>
    </body>
</html>
