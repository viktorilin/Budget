<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Budget Registration Form</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/style.css">
</head>

<body>
<div class="container">
    <div class="col-md-4 col-md-offset-4">
        <div class="panel panel-primary panel-signin">
            <div class="panel-heading">Registration</div>
            <div class="panel-body">
                <form:form method="POST" action="/user/do_registration" modelAttribute="userDto">
                    <div class="form-group">
                        <label for="nickName">Nick name</label>
                        <form:input type="text" class="form-control" id="nickName" placeholder="Nick name"
                                    path="nickName"/>
                        <form:errors path="nickName"/>
                    </div>
                    <div class="form-group">
                        <label for="firstName">First name</label>
                        <form:input type="text" class="form-control" id="firstName" placeholder="First name"
                                    path="firstName"/>
                        <form:errors path="firstName"/>
                    </div>
                    <div class="form-group">
                        <label for="secondName">Second name</label>
                        <form:input type="text" class="form-control" id="secondName" placeholder="Second name"
                                    path="secondName"/>
                        <form:errors path="secondName"/>
                    </div>
                    <div class="form-group">
                        <label for="email">Email address</label>
                        <form:input type="email" class="form-control" id="email" placeholder="Email" path="email"/>
                        <form:errors path="email"/>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <form:input type="password" class="form-control" id="password" placeholder="Password"
                                    path="password"/>
                        <form:errors path="password"/>
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form:form>
            </div>
        </div>
    </div>
</div>


<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/main.js"></script>
</body>
</html>