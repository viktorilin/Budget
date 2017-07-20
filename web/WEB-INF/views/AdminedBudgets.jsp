<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.07.2017
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Creation Date</th>
        </tr>
        <c:forEach var="adminedBudgets" items="${listBudgetAdmined}">
            <tr>
                <td><c:out value="${adminedBudgets.id}"/></td>
                <td><c:out value="${adminedBudgets.name}"/></td>
                <td><c:out value="${adminedBudgets.description}"/></td>
                <td><c:out value="${adminedBudgets.creationDate}"/></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
