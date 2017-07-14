<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>


<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-primary panel-signin">
                <div class="panel-heading">Add action</div>
                <div class="panel-body">
                    <form:form class="form-horizontal"  action="/addaction/submit" method="POST" id="addActionForm" modelAttribute="subaction">
                        <input type="hidden" name="budget_user" value="${budget_user_id}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Category</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="category" id="categories">
                                    <option value="0">Select category</option>
                                    <c:forEach items="${categories}" var="category">
                                        <option value="${category.id}">${category.name}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Subcategory</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="subcategory" id="subcategories" disabled></select>
                                <span class="help-block"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <div class="panel panel-primary subaction-pabel">
                                    <div class="panel-heading">Subaction</div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">Description</label>
                                            <div class="col-sm-10">
                                                <form:input path="description" class="form-control" />
                                                <span class="help-block"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">Amount</label>
                                            <div class="col-sm-10">
                                                <form:input path="amount" class="form-control" />
                                                <span class="help-block"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-primary">Add</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>

    <script src="/resources/js/jquery.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/addAction.js"></script>

</div>
</body>
</html>
