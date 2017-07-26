<%--
  Created by IntelliJ IDEA.
  User: ilost
  Date: 25.06.2017
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Budget Application</title>
</head>
<body>
<center>
    <h1>Budget Management</h1>
    <h2>
        <a href="/budget/new">Add New Budget</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/budget/list">List All Budgets</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Books</h2></caption>
        <tr>
            <th>ID</th>
            <th>Creation Date</th>
            <th>Description</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="budget" items="${listBudgets}">
            <tr>
                <td><c:out value="${budget.id}" /></td>
                <td><c:out value="${budget.creationDate}" /></td>
                <td><c:out value="${budget.description}" /></td>
                <td><c:out value="${budget.name}" /></td>
                <td>
                    <a href="/budget/edit/<c:out value='${budget.id}' />">Edit</a>
                    <a href="/budget/delete/<c:out value='${budget.id}'/>">Delete</a>
                    <a href="/budget/users/<c:out value='${budget.id}'/>">Users</a>
                    <a href="/budget/addUsers/<c:out value='${budget.id}'/>">add Users</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
