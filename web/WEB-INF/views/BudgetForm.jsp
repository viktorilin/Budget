<%--
  Created by IntelliJ IDEA.
  User: ilost
  Date: 25.06.2017
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

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
    <%--<c:if test="${budget != null}">--%>
        <form:form action="/budget/insert" method="POST" modelAttribute="budget">
        <%--</c:if>--%>
        <%--<c:if test="${budget == null}">--%>
        <%--<f action="/budget/insert" method="POST">--%>
            <%--</c:if>--%>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <%--<c:if test="${budget != null}">--%>
                            <%--Edit Budget--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${budget == null}">--%>
                            Add New Budget
                        <%--</c:if>--%>
                    </h2>
                </caption>
                <%--<c:if test="${budget != null}">--%>
                    <%--<input type="hidden" name="id" value="<c:out value='${budget.id}' />" />--%>
                <%--</c:if>--%>
                <tr>
                    <th>Creation Date: </th>
                    <td><form:input path="creationDate"/></td>
                </tr>
                <tr>
                    <th>Description: </th>
                    <td><form:input path="description"/></td>
                </tr>
                <tr>
                    <th>Name: </th>
                    <td><form:input path="name"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form:form>
</div>
</body>
</html>
