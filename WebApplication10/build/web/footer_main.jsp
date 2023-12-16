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
        <div id="footer">
            <ul class="pagination">
                <c:forEach begin="1" end="${endP}" var="i">
                    <li class="page-item"><a class="page-link" href="Account_Servlet?index=${i}">${i}</a></li>
                    </c:forEach>
            </ul>
        </div>
    </body>
</html>