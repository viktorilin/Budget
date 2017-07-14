<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sanya
  Date: 10.07.2017
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Subactions</title>
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
                            href="/budget/${action.budgetUser.budget.id}/actions">All actions</a></li>
                    <li role="presentation" <c:if test="${type.equals(\"my\")}">class="active"</c:if>><a
                            href="/budgetuser/${budget_user_id}/actions">My actions</a></li>
                </ul>
            </div>
        </div>

        <div class="col-md-7">
            <h1>${action.subCategory.category.name} / ${action.subCategory.name} / ${action.createDate}</h1>
            <table class="table table-bordered sub-action-table">
                <tr class="bg-primary" id="table-header">
                    <th>Description</th>
                    <th class="amount text-center" colspan="2">Amount</th>
                </tr>
                
                <c:choose>
                    <c:when test="${action.getBudgetUser().getUser().getId() == user_id}">
                        <c:forEach var="subaction" items="${action.subActions}">
                            <tr>
                                <td>${subaction.description}</td>
                                <td class="text-center">${subaction.amount}</td>
                                <td class="text-center"><a href="/action/${action_id}/subaction/${subaction.id}/delete" class="btn-primary btn btn-xs delete-item">x</a></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="subaction" items="${action.subActions}">
                            <tr>
                                <td>${subaction.description}</td>
                                <td class="text-center">${subaction.amount}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

                <c:if test="${action.getBudgetUser().getUser().getId() == user_id}">
                    <tr id="row-add-subact">
                        <td colspan="3" class="text-center form-add-row">
                            <div class="dropdown" id="dropDownForm">
                                <a id="dLabel" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                    Add subaction...
                                </a>
                                <div class="dropdown-menu panel panel-default form-add-subaction">
                                    <div class="panel-heading">Add subaction</div>
                                    <div class="panel-body">
                                        <form class="addsubaction-form" id="form-add-subaction">
                                            <input type="hidden" name="action_id" value="${action_id}">
                                            <div class="form-group">
                                                <label for="recipient-name" class="control-label">Description:</label>
                                                <input type="text" class="form-control" name="description" id="recipient-name">
                                                <span class="help-block"></span>
                                            </div>
                                            <div class="form-group">
                                                <label for="amount" class="control-label">Amount:</label>
                                                <input type="text" name="amount" id="amount" class="form-control">
                                                <span class="help-block"></span>
                                            </div>
                                            <button type="submit" id="butnSubmit" data-loading-text="Loading..." class="btn btn-primary">Add subaction</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:if>
            </table>

        </div>
    </div>
</div>

<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<c:if test="${action.getBudgetUser().getUser().getId() == user_id}">
    <script src="/resources/js/subactions.js"></script>
</c:if>
</body>
</html>
