<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <form:select path="user">
    <form:options items="${userList.nickName}" />
    </form:select>
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <c:forEach var="budgetUsers" items="${listBudgetUsers}">
            <tr>
                <td><c:out value="${budgetUsers.id}"/></td>
                <td><c:out value="${budgetUsers.nickName}"/></td>
                <td><c:out value="${budgetUsers.firstName}"/></td>
                <td><c:out value="${budgetUsers.secondName}"/></td>
                <td><c:out value="${budgetUsers.email}"/></td>
                    <%--<td><c:out value="${budgetUsers.role}" /></td>--%>
                <td><c:out value="${budgetUsers.image}"/></td>
                <td>
                    <a href="/budget/adminedBudget/<c:out value='${budgetUsers.id}' />">Adminbudget</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
