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
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-3">
            <div class="panel left-navbar">
                <ul class="nav nav-pills nav-stacked ">
                    <li role="presentation" <c:if test="${type.equals(\"all\")}" >class="active"</c:if> ><a href="/budget/${budget_id}/actions">All actions</a></li>
                    <li role="presentation" <c:if test="${type.equals(\"my\")}" >class="active"</c:if>><a href="/budgetuser/${budget_user.id}/actions">My actions</a></li>
                </ul>
            </div>
        </div>
        <div class="col-md-9">
            <h3 class="text-center">
                <c:choose>
                    <c:when test="${type.equals(\"all\")}">All actions</c:when>
                    <c:otherwise>My actions</c:otherwise>
                </c:choose>
            </h3>

            <table class="table table-bordered">
                <tr>
                    <th>Category</th>
                    <th class="text-center">Subcat</th>
                    <th class="text-center">User</th>
                    <th colspan="3">Date</th>
                </tr>

                <c:forEach var="action" items="${actions}">
                    <tr <c:if test="${type.equals(\"all\")}">
                            <c:if test="${action.budgetUser.id == budget_user.id}" >class="warning"</c:if>
                        </c:if>>
                        <td>${action.subCategory.category.name}</td>
                        <td class="text-center">${action.subCategory.name}</td>
                        <td class="text-center">${action.budgetUser.user.nickName}</td>
                        <td>${action.createDate}</td>
                        <td class="subaction_link"><a href="/action/${action.id}/subactions" class="btn btn-primary btn-xs">Subactions <span class="badge">${action.subActions.size()}</span></a></td>
                        <td class="text-center">

                            <c:if test="${type.equals(\"all\")}">
                                <c:choose>
                                    <c:when test="${action.budgetUser.id == budget_user.id}">
                                        <a href="/action/${action.id}/delete" class="btn btn-primary btn-xs delete-item">x</a>
                                    </c:when>
                                </c:choose>
                            </c:if>
                            <c:if test="${type.equals(\"my\")}">
                                <a href="/action/${action.id}/delete" class="btn btn-primary btn-xs delete-item">x</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <th colspan="6" class="text-center">
                        <a href="<c:url value="/budget/${budget_user.id}/addaction"/> " class="btn btn-primary">+ Add action</a>
                    </th>
                </tr>
            </table>
        </div>
    </div>
</div>

<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/main.js"></script>

</body>
</html>
