<%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 24/04/2017
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="h" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>EDUE</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/style.css">
    <script src="../../js/jquery-3.2.1.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</head>
<body>

    <h1>Hi ${profNameAndSubject.professorName} , proffessor of ${profNameAndSubject.subjectName} </h1>
    <form id="logout" action="/logout" method="post" >
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input name="submit" type="submit" value="Logout" />
    </form>


    <ul class="nav nav-tabs">
        <c:forEach items="${profClasses}" var="profClass">
            <li><a href="#${profClass.name}" data-toggle="tab">${profClass.name}</a></li>
        </c:forEach>
    </ul>

    <div id="myTabContent" class="tab-content">
        <c:forEach items="${profClasses}" var="profClass" varStatus="loop">

            <div class="tab-pane fade <c:if test="${loop.count eq '1'}">active</c:if> in" id="${profClass.name}">
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Id Student</th>
                            <th>Name</th>
                            <th>Grades</th>
                            <th>Add Grade</th>
                            <th>Average</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${profClass.students}" var="student" varStatus="loop">
                            <tr
                                        <c:choose>
                                            <c:when test="${student.average lt '5'}">
                                                 class="danger"
                                            </c:when>
                                            <c:otherwise>
                                                class="success"
                                            </c:otherwise>
                                        </c:choose>
                            >
                                <td>${loop.count}</td>
                                <td>${student.name}</td>
                                <td>
                                    <c:forEach items="${student.grades}" var="gradeObject" varStatus="innerloop">
                                        <span><c:if test="${innerloop.index ne '0'}">,</c:if>${gradeObject.grade}</span>
                                    </c:forEach>
                                </td>
                                <td>
                                    <p>
                                        <h:form action="/professor" method="post">
                                            <input name="studentGrade" />
                                            <input type="hidden" name="subjectId" type="hidden" value="${profNameAndSubject.subjectId}"/>
                                            <input type="hidden" name="studentId" type="hidden" value="${student.id}"/>

                                            <input type="submit">
                                        </h:form>
                                        <span class="glyphicon glyphicon-plus">

                                        </span>
                                    </p>
                                </td>
                                <td>${student.average}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:forEach>
    </div>
</body>
</html>
