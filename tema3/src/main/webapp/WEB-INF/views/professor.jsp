<%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 24/04/2017
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>EDUE</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/style.css">
    <script src="../../js/jquery-3.2.1.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</head>
<body>
<ul class="nav nav-tabs">
    <c:forEach items="${profClasses}" var="profClass">
        <li><a href="#home" data-toggle="tab">${profClass.name}</a></li>
    </c:forEach>
</ul>
<div id="myTabContent" class="tab-content">
        <div class="tab-pane fade active in" id="${profClass.name}">
            <table class="table table-striped table-hover ">
                <thead>
                    <tr>
                        <th>Id Elev</th>
                        <th>Nume</th>
                        <th>Note</th>
                        <th>Adauga Nota</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td><p><span class="glyphicon glyphicon-plus"></span></p></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td><p><span class="glyphicon glyphicon-plus"></span></p></td>
                    </tr>
                    <tr class="info">
                        <td>3</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td><p><span class="glyphicon glyphicon-plus"></span></p></td>
                    </tr>
                    <tr class="success">
                        <td>4</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td><p><span class="glyphicon glyphicon-plus"></span></p></td>
                    </tr>
                    <tr class="danger">
                        <td>5</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td><p><span class="glyphicon glyphicon-plus"></span></p></td>
                    </tr>
                    <tr class="warning">
                        <td>6</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td><p><span class="glyphicon glyphicon-plus"></span></p></td>
                    </tr>
                    <tr class="active">
                        <td>7</td>
                        <td>Column content</td>
                        <td>Column content</td>
                        <td><p><span class="glyphicon glyphicon-plus"></span></p></td>
                    </tr>
                </tbody>
            </table>
        </div>

</div>

</body>
</html>
