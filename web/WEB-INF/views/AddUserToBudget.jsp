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

<div align="center">
    <form:form action="/budget/insertAll/budgetUser" method="POST" modelAttribute="budgetUser">
        <form:select path="userId">
            <form:option value="NONE" label="--- Select ---"/>
            <form:options items="${userList}" itemValue="id" itemLabel="nickName"></form:options>
        </form:select>
        <form:hidden path="budgetId"/>

        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Save"/>
            </td>
        </tr>

    </form:form>
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>ID</th>
                <th>Nick Name</th>
                <th>First Name</th>
                <th>Second Name</th>
                <th>Email</th>
                <th>image</th>
            </tr>
            <c:forEach var="budgetUsers" items="${listBudgetUsers}">
                <tr>
                    <td><c:out value="${budgetUsers.user.id}"/></td>
                    <td><c:out value="${budgetUsers.user.nickName}"/></td>
                    <td><c:out value="${budgetUsers.user.firstName}"/></td>
                    <td><c:out value="${budgetUsers.user.secondName}"/></td>
                    <td><c:out value="${budgetUsers.user.email}"/></td>
                        <%--<td><c:out value="${budgetUsers.role}" /></td>--%>
                    <td><c:out value="${budgetUsers.user.image}"/></td>
                    <td>
                        <a href="/budget/adminedBudget/<c:out value='${budgetUsers.user.id}' />">Adminbudget</a>
                    </td>
                </tr>

            </c:forEach>
        </table>
</div>
</body>

</html>
