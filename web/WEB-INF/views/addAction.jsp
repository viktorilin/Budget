<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sanya
  Date: 28.06.2017
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add action</title>
</head>
<body>


    <h1 style="text-align:center;">Add action</h1>

    <form action="/action/add" method="post">
        <input type="hidden" name="budget_user" value="${budget_user}">
        <table border="1" style="margin:0 auto; width:300px;">
            <tr>
                <td>Title: </td>
                <td>
                    <input type="text" name="title">
                </td>
            </tr>
            <tr>
                <td>Category: </td>
                <td>
                    <select name="category" >
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th colspan="2">
                    <input type="submit" value="Add"/>
                </th>
            </tr>
        </table>
    </form>

</body>
</html>
