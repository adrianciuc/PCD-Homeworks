<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 24/04/2017
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EDUE</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/style.css">
    <script src="../../js/jquery-3.2.1.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</head>
<body>
<form id="logout" action="/logout" method="post" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input name="submit" type="submit" value="Logout" />
</form>
<table class="table table-striped table-hover ">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Class</th>
        <th>Subject</th>
        <th>Grade</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${students}" var="student" varStatus="loop">

            <tr>
                <td>${loop.count}</td>
                <td>${student.name}</td>
                <td>${student.className}</td>
                <td>${student.subjectName}</td>
                <td>${student.grade}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
