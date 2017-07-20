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
                    <li role="presentation"
                        <c:if test="${type.equals(\"all\")}">class="active"</c:if> ><a
                            href="/budget/${budget_id}/actions">All actions</a></li>
                    <li role="presentation" <c:if test="${type.equals(\"my\")}">class="active"</c:if>><a
                            href="/budgetuser/${budget_user.id}/actions">My actions</a></li>
                </ul>
            </div>
        </div>
        <div class="col-md-9">
            <h3 class="text-center">
                <c:choose>
                    <c:when test="${type.equals(\"all\")}">All actions</c:when>
                    <c:when test="${type.equals(\"my\")}">My actions</c:when>
                </c:choose>
            </h3>


            <table class="table table-bordered">
                <tr>
                    <th>
                        <div class="dropdown">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                Category
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <c:forEach items="${categories}" var="category">
                                    <c:if test="${type.equals(\"all\")}">
                                        <li>
                                            <a href="/budget/${budget_id}/actions/category/${category.id}">${category.name}</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${type.equals(\"my\")}">
                                        <li>
                                            <a href="/budgetuser/${budget_user.id}/actions/category/${category.id}">${category.name}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                                <li role="separator" class="divider"></li>
                                <c:if test="${type.equals(\"all\")}">
                                    <li><a href="/budget/${budget_id}/actions">All actions</a></li>
                                </c:if>
                                <c:if test="${type.equals(\"my\")}">
                                    <li><a href="/budgetuser/${budget_user.id}/actions">All my actions</a></li>
                                </c:if>

                            </ul>
                        </div>
                    </th>
                    <th class="text-center">Subcat</th>
                    <th class="text-center">User</th>
                    <th colspan="3">Date</th>
                </tr>
                <c:forEach var="action" items="${actions}">
                    <tr <c:if test="${type.equals(\"all\")}">
                        <c:if test="${action.budgetUser.id == budget_user.id}">class="warning"</c:if>
                    </c:if>>
                        <td>${action.subCategory.category.name}</td>
                        <td class="text-center">${action.subCategory.name}</td>
                        <td class="text-center">${action.budgetUser.user.nickName}</td>
                        <td>${action.createDate}</td>
                        <td class="subaction_link"><a href="/action/${action.id}/subactions"
                                                      class="btn btn-primary btn-xs">Subactions <span
                                class="badge">${action.subActions.size()}</span></a></td>
                        <td class="text-center">

                            <c:if test="${type.equals(\"all\")}">
                                <c:choose>
                                    <c:when test="${action.budgetUser.id == budget_user.id}">
                                        <a href="/action/${action.id}/delete"
                                           class="btn btn-primary btn-xs delete-item">x</a>
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
                        <a href="<c:url value="/budget/${budget_user.id}/addaction"/> " class="btn btn-primary">+ Add
                            action</a>
                    </th>
                </tr>
            </table>



            <ul class="pagination">
                <c:set var="firstPage" value="1"/>
                <c:set var="lastPage"  value="${pages}"/>
                <li>
                    <c:if test="${type.equals(\"all\")&&isCategory.equals(\"false\")}"><a href="/budget/${budget_id}/actions" ><span aria-hidden="true">&laquo;</span></a></c:if>
                    <c:if test="${type.equals(\"my\")&&isCategory.equals(\"false\")}"><a href="/budgetuser/${budget_user.id}/actions" ><span aria-hidden="true">&laquo;</span></a></c:if>
                    <c:if test="${type.equals(\"all\")&&isCategory.equals(\"true\")}"> <a href="/budget/${budget_id}/actions/category/${category_id}"<span aria-hidden="true">&laquo;</span></a></c:if>
                    <c:if test="${type.equals(\"my\")&&isCategory.equals(\"true\")}"><a href="/budgetuser/${budget_user.id}/actions/category/${category_id}" ><span aria-hidden="true">&laquo;</span></a></c:if>
                </li>

                <c:forEach begin="1" end="${pages}"  var="selectPage">
                    <li>
                        <c:if test="${type.equals(\"all\")&&isCategory.equals(\"false\")}"><a href="/budget/${budget_id}/actions?page=${selectPage}" >${selectPage}</a></c:if>
                        <c:if test="${type.equals(\"my\")&&isCategory.equals(\"false\")}"><a href="/budgetuser/${budget_user.id}/actions?page=${selectPage}" >${selectPage}</a></c:if>
                        <c:if test="${type.equals(\"all\")&&isCategory.equals(\"true\")}"> <a href="/budget/${budget_id}/actions/category/${category_id}?page=${selectPage}">${selectPage}</a></c:if>
                        <c:if test="${type.equals(\"my\")&&isCategory.equals(\"true\")}"><a href="/budgetuser/${budget_user.id}/actions/category/${category_id}?page=${selectPage}" >${selectPage}</a></c:if>
                    </li>
                </c:forEach>
                <li>
                    <c:if test="${type.equals(\"all\")&&isCategory.equals(\"false\")}"><a href="/budget/${budget_id}/actions?page=${pages}" > <span aria-hidden="true">&raquo;</span></a></c:if>
                    <c:if test="${type.equals(\"my\")&&isCategory.equals(\"false\")}"><a href="/budgetuser/${budget_user.id}/actions?page=${pages}" > <span aria-hidden="true">&raquo;</span></a></c:if>
                    <c:if test="${type.equals(\"all\")&&isCategory.equals(\"true\")}"> <a href="/budget/${budget_id}/actions/category/${category_id}?page=${pages}"> <span aria-hidden="true">&raquo;</span></a></c:if>
                    <c:if test="${type.equals(\"my\")&&isCategory.equals(\"true\")}"><a href="/budgetuser/${budget_user.id}/actions/category/${category_id}?page=${pages}" > <span aria-hidden="true">&raquo;</span></a></c:if>
                </li>
            </ul>
        </div>
    </div>
</div>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/main.js"></script>

</body>
</html>
