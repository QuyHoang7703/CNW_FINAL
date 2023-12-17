<%-- 
    Document   : list_bill
    Created on : Dec 17, 2023, 9:01:19 PM
    Author     : QUYHOANG
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.BillView"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
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
            padding: 5px;
        }

        .btn_add{

            float: right;
            padding: 8px;
            background-color: red;

        }

        h2{
            background-color: ff656c;
        }
    </style>
    <body>
        <%
            ArrayList<BillView> list = (ArrayList<BillView>) request.getAttribute("list");
//            if(list!=null){
//            out.println(""+list.size());
//            }
        %>
        <jsp:include page="./header.jsp"></jsp:include>
            <form action="ProductView_Servlet" method="post">
              
                <h2>Danh sách hóa đơn</h2>
                <table width="100%" >
                    <thead>
                    <th>Mã khách hàng</th>
                    <th>Tên khách hàng</th>
                    <th>Mã hóa đơn</th>
                    <th>Tổng tiền</th>
                    <th>Ngày đặt hàng</th>
                   
                    </thead>

                    <tbody>
                    <%
                          NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(java.util.Locale.forLanguageTag("vi-VN"));
                        for (BillView billView : list) {
                    %>
                    <%
                            BigDecimal price = billView.getValue_bill();

                            String formattedPrice = currencyFormat.format(price);
                        %>
                    <tr>
                        <td><%=billView.getId()%></td>
                        <td><%=billView.getName_customer()%></td>
                        <td><%=billView.getId_bill()%></td>
                        <td><%=formattedPrice%></td>
                        <td><%=billView.getDate_order()%></td>
                      
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </form>
    </body>
</html>
