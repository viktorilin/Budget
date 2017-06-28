<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sanya
  Date: 28.06.2017
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Actions</title>
</head>
<body>

<h1 style="text-align: center;">My actions</h1>

<table border="1" style="margin:0 auto; width:300px;">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th colspan="2">Category</th>
    </tr>

    <c:forEach var="action" items="${actions}">
        <tr>
            <td>${action.id}</td>
            <td>${action.type}</td>
            <td>${action.getCategory().getName()}</td>
            <td><a href="delete/${action.id}/budget/${action.getBudgetUser().getId()}">x</a></td>
        </tr>

    </c:forEach>
    <tr>
        <th colspan="3">
            <a href="<c:url value="add/${budget_user}"/> ">add action</a>
        </th>
    </tr>
</table>


</body>
</html>
